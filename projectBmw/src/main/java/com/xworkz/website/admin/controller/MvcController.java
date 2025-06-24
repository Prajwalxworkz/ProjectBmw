package com.xworkz.website.admin.controller;

import com.sun.deploy.net.HttpResponse;
import com.xworkz.website.admin.constants.Schedule;
import com.xworkz.website.admin.service.AdminServiceIntf;
import com.xworkz.website.dto.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("admin")
public class MvcController {

    @Autowired
    AdminServiceIntf service;

    @PostMapping("otpRequest")
    public String handleOtpRequest(String email, Model model){
        System.out.println("handleOtpRequest in controller start");
       boolean result= service.handleOtpRequest(email);
       if(result) {
           System.out.println("handleOtpRequest in controller end1");
           return "admin/loginWithOtp";
       }else{
           model.addAttribute("errorMsg", "Invalid email");
           System.out.println("handleOtpRequest in controller end2");
           return "admin/adminLogin";
       }
    }

    @PostMapping("verifyOtp")
    public String verifyOtpAndLogin(Integer otp){
        System.out.println("verifyOtpAndLogin in controller start");
        boolean result= service.verifyOtpAndLogin(otp);
        System.out.println("result= "+result);
        if (result==true){
            System.out.println("verifyOtpAndLogin in controller end1");
            return "redirect:/admin/success";
        }else {
            System.out.println("verifyOtpAndLogin in controller end2");
            return "admin/adminLogin";
        }
    }

    @PostMapping("addBike")
    public String addBike(BikeDto bikeDto, Model model) {
        System.out.println("addBike in controller start");

        String basePath = "C:\\Users\\Legion\\Documents\\project_image_uploads\\bike_images\\";
        String modelName = bikeDto.getModelName();
        Path modelDir = Paths.get(basePath + modelName);

        try {
            if (!Files.exists(modelDir)) {
                Files.createDirectories(modelDir);
            }

            // Save and set image names in DTO
            saveImage(bikeDto.getMultipartFile1(), modelDir);
            saveImage(bikeDto.getMultipartFile2(), modelDir);
            saveImage(bikeDto.getMultipartFile3(), modelDir);
            saveImage(bikeDto.getMultipartFile4(), modelDir);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("addBike in controller end1");
            model.addAttribute("addBikeErrorMsg", "Failed to save bike images.");
            return "admin/addBike";
        }

        boolean result = service.saveBike(bikeDto);
        if (result) {
            System.out.println("addBike in controller end2");
            return "admin/adminHome";
        } else {
            System.out.println("addBike in controller end3");
            model.addAttribute("addBikeErrorMsg", "Error while adding the bike!!");
            return "admin/addBike";
        }
    }
    private void saveImage(MultipartFile file, Path dir) throws IOException {
        if (file != null && !file.isEmpty()) {
            System.out.println("file name: "+file.getOriginalFilename());
            Path filePath = dir.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
        }
    }

    @GetMapping("redirectAddBranch")
    public String getAddBranch(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new BranchDto());
        }
        return "admin/addBranch";
    }


    @PostMapping("addBranch")
    public String addBranch(BranchDto branchDto, RedirectAttributes redirectAttrs) {
        System.out.println("addBranch in controller start");
        if (!validateBranchForm(branchDto, redirectAttrs)) {
            redirectAttrs.addFlashAttribute("dto", branchDto);
            return "redirect:/admin/redirectAddBranch"; // REDIRECT instead of forward
        }

        boolean result = service.saveBranch(branchDto);
        if (result) {
            return "redirect:/admin/success";
        } else {
            redirectAttrs.addFlashAttribute("addBranchErrorMsg", "Error while adding the branch!!");
            return "redirect:/admin/redirectAddBranch";
        }
    }


    @GetMapping("bikeBranchList")
    public String getBikeBranchList(Model model, String name){
        System.out.println("getBikeBranchList in controller start");
        List<BranchDto> branchDtoList=service.getAllBranches();
        List<BikeDto> bikeDtoList=service.getAllBikes();
//        List<String> branchNameList=service.getAllBranchNames();
//        List<String> bikeNameList=service.getAllBikeNames();
        model.addAttribute("branchList",branchDtoList);
        model.addAttribute("bikeList",bikeDtoList);
        System.out.println("getBikeBranchList in controller end");
        if(name!=null){
            if(name.equals("branch")) return "admin/branchDisplay";
            else return "admin/bikeDisplay";
        }else {
            return "redirect:/admin/bikeBranchList";
        }
    }

    @PostMapping("/bikeToBranch")
    public String assignBikeToBranch(BikeToBranchDto dto, Model model) {
        boolean result = service.assignBikeToBranch(dto);
        if (result) {
            return "redirect:/admin/success";
        } else {
            return "admin/addBikeToBranch";
        }
    }

    @GetMapping("/success")
    public String adminHome(){
        return "admin/adminHome";

    }

    @GetMapping("customerReg")
    public String getCustomerReg(Model model) {
        if (!model.containsAttribute("customer")) {
            model.addAttribute("customer", new CustomerDto());
        }

        model.addAttribute("branchList", service.getAllBranches());
        model.addAttribute("bikeList", service.getAllBikes());
        model.addAttribute("scheduleList", Arrays.asList(Schedule.values()));

        return "admin/customerRegistration";
    }


    @PostMapping("addCustomer")
    public String addCustomer(@ModelAttribute CustomerDto dto, RedirectAttributes redirectAttrs) {

        if (!validateCustomerForm(dto, redirectAttrs)) {
            redirectAttrs.addFlashAttribute("customer", dto);
            return "redirect:/admin/customerReg";
        }

        boolean isSaved = service.saveCustomer(dto);
        if (!isSaved) {
            redirectAttrs.addFlashAttribute("saveErrorMsg", "Failed to save customer. Please try again.");
            redirectAttrs.addFlashAttribute("customer", dto);
            return "redirect:/admin/customerReg";
        }

        return "redirect:/admin/success";
    }


    @GetMapping("customersList")
    public String getAllCustomerDetails(Model model){
        List<CustomerDto>customerDtoList=service.getAllCustomerDetails();
        model.addAttribute("customerList",customerDtoList);
        return "admin/customersTable";
    }

    @GetMapping("filter")
    public String filter(@RequestParam String sort, Model model){
        List<CustomerDto> customerDtoList=service.getCustomerByFilter(sort);
        model.addAttribute("customerList", customerDtoList);
        return "admin/customersTable";
    }
    @GetMapping("edit")
    public String scheduleUpdate(@RequestParam Long id, Model model){
        CustomerDto customerDto=service.getCustomerById(id);
        model.addAttribute("customer",customerDto);
        return "admin/followUpTable";
    }

    @GetMapping("view")
    public String getFollowUpDetails(@RequestParam Long id, Model model){
        List<FollowUpDto> followUpDtoList=service.getFollowUpDetailsByCustomerId(id);
        CustomerDto customerDto=service.getCustomerById(id);
        if(followUpDtoList!=null){
            model.addAttribute("customer",customerDto);
            model.addAttribute("followUpDto", followUpDtoList);
            return "admin/viewFullFollowUpDetails";
        }else{
            model.addAttribute("getFollowUpDetailsErrorMsg", "Follow-up not yet done!! or Scheduling is over");
            return "admin/viewFullFollowUpDetails";
        }
    }

    @PostMapping("followUp")
    public String saveFollowUp(FollowUpDto dto, Model model){
     boolean result=service.saveFollowUp(dto);
     if(result){
         model.addAttribute("followUpSuccessMsg", "Follow-up update successful!!" );
         return "redirect:/admin/customersList";
     }else{
         model.addAttribute("followUpErrorMsg", "Follow-up update failed!!" );
         return "admin/followUpTable";
     }
    }

    @GetMapping("download")
    public void downloadImage(HttpServletResponse response, @RequestParam String picture,@RequestParam String model) {
        File file;
      if(picture!=null && model!=null) {
          file = new File("C:\\Users\\Legion\\Documents\\project_image_uploads\\bike_images\\"+model+"\\"+picture);
      }else{
          file = new File("C:\\Users\\Legion\\Documents\\project_image_uploads\\showroom_images\\"+picture);
      }

        String contentType = getContentTypeFromExtension(picture);
        response.setContentType(contentType);

        try {
            InputStream input = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream output = response.getOutputStream();
            IOUtils.copy(input, output);
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getContentTypeFromExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            case "bmp":
                return "image/bmp";
            default:
                return "application/octet-stream"; // Fallback if unknown
        }
    }

    private boolean validateBranchForm(BranchDto dto, RedirectAttributes redirectAttrs){
        boolean isValid=true;

        if(dto.getBranchName()==null || dto.getBranchName().trim().isEmpty()){
            redirectAttrs.addFlashAttribute("branchNameErrorMsg", "* Name field can not be null or empty ");
            isValid=false;
        }else if (!dto.getBranchName().matches("^[A-Za-z ]{3,}")){
            redirectAttrs.addFlashAttribute("branchNameErrorMsg","* It must have at least 3 characters </br> * It must contain only letters and spaces");
            isValid=false;
        }

        if(dto.getLocation()==null || dto.getLocation().trim().isEmpty()){
            redirectAttrs.addFlashAttribute("locationErrorMsg", "* Location field can not be null or empty");
            isValid=false;
        }

        if(dto.getCity()==null || dto.getCity().trim().isEmpty()){
            redirectAttrs.addFlashAttribute("cityErrorMsg", "* City field can not be null or empty");
            isValid=false;
        }

        if (dto.getPinCode() == null) {
            redirectAttrs.addFlashAttribute("pinCodeErrorMsg", "* Pin-code can not be null");
            isValid = false;
        } else if (!dto.getPinCode().matches("\\d{6}")) {
            redirectAttrs.addFlashAttribute("pinCodeErrorMsg", "* Pin-code must be a 6-digit number");
            isValid = false;
        }


        Path path= Paths.get("C:\\Users\\Legion\\Documents\\project_image_uploads\\showroom_images\\"+dto.getMultipartFile().getOriginalFilename());
        try{
            Files.write(path,dto.getMultipartFile().getBytes());
        } catch (IOException e){
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("branchPicErrorMsg", "Failed to save branch image.");
            System.out.println("addBranch in controller end1 fail (Failed to save branch image.)");
            isValid=false;
        }

        return isValid;
    }

    private boolean validateName(String name, RedirectAttributes redirectAttrs){
        if(name==null || name.trim().isEmpty()){
            redirectAttrs.addFlashAttribute("nameErrorMsg","* Name field can not be null or empty");
            return false;
        } else if (!name.matches("^[A-Za-z ]{3,}")){
            redirectAttrs.addFlashAttribute("nameErrorMsg","* It must have at least 3 characters </br> * It must contain only letters and spaces");
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email, RedirectAttributes redirectAttrs){
        if(email==null || email.trim().isEmpty()){
            redirectAttrs.addFlashAttribute("emailErrorMsg","* Email field can not be null or empty");
            return false;
        }else if(!email.matches("^[a-zA-Z0-9_.%+-]+@gmail\\.com$")){
            redirectAttrs.addFlashAttribute("emailErrorMsg","* Domain should be gmail.com</br> * No spaces in between and only '_', '.', '%', '+', and '-' are allowed.");
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(Long phNumber, RedirectAttributes redirectAttrs){
        if(phNumber==null){
            redirectAttrs.addFlashAttribute("phNumberErrorMsg","* Phone number field can not be null or empty");
            return false;
        }else if( !phNumber.toString().matches("^[6-9]\\d{9}")){
            redirectAttrs.addFlashAttribute("phNumberErrorMsg","* Enter a valid 10 digits number");
            return false;
        }
        return true;
    }

    private boolean validateDob(LocalDate dob, RedirectAttributes redirectAttrs){
        if(dob == null){
            redirectAttrs.addFlashAttribute("dobErrorMsg", "Date of Birth cannot be null");
            return false;
        }

        LocalDate today = LocalDate.now();
        LocalDate minAllowedDob = today.minusYears(18);
        System.out.println("minAllowedDob" +minAllowedDob);

        if(dob.isAfter(minAllowedDob)){
            redirectAttrs.addFlashAttribute("dobErrorMsg", "Age must be 18 years or older");
            return false;
        }

        return true;
    }

    private boolean validateAddress(String address, RedirectAttributes redirectAttrs){
        if(address==null || address.trim().isEmpty()){
            redirectAttrs.addFlashAttribute("addressErrorMsg","Address can not be empty");
            return false;
        }
        return true;
    }
    private boolean validateDl(String dlNumber, RedirectAttributes redirectAttrs) {
        if(dlNumber == null || dlNumber.trim().isEmpty()) {
            redirectAttrs.addFlashAttribute("dlErrorMsg", "Driving License number cannot be empty");
            return false;
        }

        String dlPattern = "^[A-Z]{2}[0-9]{2}[0-9]{4}[0-9]{7}$";

        if(!dlNumber.matches(dlPattern)) {
            redirectAttrs.addFlashAttribute("dlErrorMsg", "Invalid Driving License number format (e.g., KA0120231234567)");
            return false;
        }

        return true;
    }

    private boolean validateCustomerForm(CustomerDto dto, RedirectAttributes redirectAttrs) {
        boolean isValid = true;

        if(!validateName(dto.getFullName(),redirectAttrs)) isValid=false;


        if(!validateEmail(dto.getEmail(),redirectAttrs)) isValid=false;


        if(!validateDob(dto.getDto(),redirectAttrs)) isValid=false;


        if(!validateDl(dto.getDrivingLicense(),redirectAttrs)) isValid=false;


        if(!validatePhoneNumber(dto.getPhoneNumber(),redirectAttrs)) isValid=false;


        if(!validateAddress(dto.getAddress(),redirectAttrs)) isValid=false;


        if (dto.getSchedule() == null) {
            redirectAttrs.addFlashAttribute("scheduleErrorMsg", "Please select a schedule option");
            isValid = false;
        }

        if (dto.getModelName() == null || dto.getModelName().trim().isEmpty() || dto.getModelName().equalsIgnoreCase("Select Branch")) {
            redirectAttrs.addFlashAttribute("modelNameErrorMsg", "Please select a model name");
            isValid = false;
        }

        if (dto.getBranchName() == null || dto.getBranchName().trim().isEmpty() || dto.getBranchName().equalsIgnoreCase("Select Bike")) {
            redirectAttrs.addFlashAttribute("branchNameErrorMsg", "Please select a branch name");
            isValid = false;
        }

        if (dto.getDateAndTime() == null) {
            redirectAttrs.addFlashAttribute("dateTimeErrorMsg", "Date and Time must be selected");
            isValid = false;
        }

        return isValid;
    }



}
