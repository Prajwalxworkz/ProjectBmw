package com.xworkz.website.admin.service;

import com.xworkz.website.admin.constants.Schedule;
import com.xworkz.website.admin.repo.AdminRepositoryIntf;
import com.xworkz.website.dto.*;
import com.xworkz.website.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class AdminServiceImpl implements AdminServiceIntf{
    @Autowired
    AdminRepositoryIntf repository;

    @Autowired
    HttpSession session;

    @Override
    public boolean handleOtpRequest(String email) {
        session.setAttribute("email",email);
        if(!isValidEmail(email)){
            return false;
        }
        Integer otp=generateOtp();
        setOtp(otp);
        sendOtp(email, otp);
        return true;
    }

    @Override
    public boolean verifyOtpAndLogin(Integer otp) {
        String email=(String)session.getAttribute("email");
       AdminEntity entity= repository.getAdminDetails(email);
       if(entity.getOtp().equals(otp)){
           log.info("valid otp");
           if(LocalDateTime.now().isBefore(entity.getExpiresAt())){
               log.info("successful login");
                return true;
           }else{
               log.info("Time out");
               session.setAttribute("otpExpiredMsg","Time out!!");
           }
       }else{
           log.info("Wrong otp");
           session.setAttribute("invalidOtpMsg","Wrong otp!!");
       }
       return false;

    }

    @Override
    public boolean saveBike(BikeDto bikeDto) {
        bikeDto.setFrontView(bikeDto.getMultipartFile1().getOriginalFilename());
        bikeDto.setRearView(bikeDto.getMultipartFile2().getOriginalFilename());
        bikeDto.setSideView1(bikeDto.getMultipartFile3().getOriginalFilename());
        bikeDto.setSideView2(bikeDto.getMultipartFile4().getOriginalFilename());
        BikeEntity bikeEntity=new BikeEntity();
        BeanUtils.copyProperties(bikeDto, bikeEntity);
        return repository.saveBike(bikeEntity);
    }

    @Override
    public boolean saveBranch(BranchDto branchDto) {
        branchDto.setBranchPicture(branchDto.getMultipartFile().getOriginalFilename());
        BranchEntity branchEntity=new BranchEntity();
        BeanUtils.copyProperties(branchDto, branchEntity);
        return  repository.saveBranch(branchEntity);
    }

    @Override
    public List<String> getAllBranchNames() {
        List<BranchEntity> branchEntityList=repository.getAllBranches();
        List<String> branchNameList=new ArrayList<>();
        for(BranchEntity branch: branchEntityList){
            branchNameList.add(branch.getBranchName());
        }
        System.out.println(branchNameList);
        return branchNameList;
    }

    @Override
    public List<String> getAllBikeNames() {
        List<BikeEntity> bikeEntityList=repository.getAllBikes();
        List<String> bikeNameList=new ArrayList<>();
        for(BikeEntity bike: bikeEntityList){
            bikeNameList.add(bike.getModelName());
        }
        System.out.println(bikeNameList);
        return bikeNameList;
    }

    @Override
    public List<BranchDto> getAllBranches() {
        List<BranchEntity> branchEntityList= repository.getAllBranches();
        List<BranchDto> branchDtoList=new ArrayList<>();
        for(BranchEntity branchEntity: branchEntityList){
            BranchDto branchDto=new BranchDto();
            BeanUtils.copyProperties(branchEntity,branchDto);
            branchDtoList.add(branchDto);
        }
        System.out.println(branchDtoList);
        return branchDtoList;
    }

    @Override
    public List<BikeDto> getAllBikes() {
        List<BikeEntity> bikeEntityList= repository.getAllBikes();
        List<BikeDto> bikeDtoList=new ArrayList<>();
        for(BikeEntity bikeEntity: bikeEntityList){
            BikeDto bikeDto=new BikeDto();
            BeanUtils.copyProperties(bikeEntity,bikeDto);
            bikeDtoList.add(bikeDto);
        }
        System.out.println(bikeDtoList);
        return bikeDtoList;
    }

    @Override
    public boolean assignBikeToBranch(BikeToBranchDto dto) {
        if(validateAssignBikeToBranch(dto)) {
            BikeToBranchEntity entity = new BikeToBranchEntity();
            entity.setBike(repository.findBikeById(dto.getBikeId()));
            entity.setBranch(repository.findBranchById(dto.getBranchId()));
            return repository.saveBikeToBranch(entity);
        }else return false;
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setBikeToBranch(repository.findBikeToBranchById(dto.getBikeId()));
        BeanUtils.copyProperties(dto, entity);
       return repository.saveCustomer(entity);
    }

    @Override
    public List<CustomerDto> getAllCustomerDetails() {
        List<CustomerEntity> customerEntityList=repository.getAllCustomerDetails();
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for(CustomerEntity entity: customerEntityList){
            CustomerDto dto=new CustomerDto();
            BeanUtils.copyProperties(entity,dto);
            dto.setBranchName(entity.getBikeToBranch().getBranch().getBranchName());
            dto.setModelName(entity.getBikeToBranch().getBike().getModelName());
            customerDtoList.add(dto);
        }
        return customerDtoList;
    }

    @Override
    public List<CustomerDto> getCustomerByFilter(String sort) {
        System.out.println("service layer getCustomerByFilter start");
        List<CustomerEntity> customerEntityList=repository.getCustomerByFilter(sort);
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for(CustomerEntity entity: customerEntityList){
            CustomerDto dto=new CustomerDto();
            BeanUtils.copyProperties(entity,dto);
            dto.setBranchName(entity.getBikeToBranch().getBranch().getBranchName());
            dto.setModelName(entity.getBikeToBranch().getBike().getModelName());
            customerDtoList.add(dto);
        }
        System.out.println(customerDtoList);
        System.out.println("service layer getCustomerByFilter end");
        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        CustomerEntity entity=repository.getCustomerById(id);
        CustomerDto dto=new CustomerDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setBranchName(entity.getBikeToBranch().getBranch().getBranchName());
        dto.setModelName(entity.getBikeToBranch().getBike().getModelName());
        return dto;
    }

    @Override
    public boolean saveFollowUp(FollowUpDto dto) {
        FollowUpEntity entity=new FollowUpEntity();
       entity.setCustomerEntity(repository.getCustomerById(dto.getCustomer_id()));
        BeanUtils.copyProperties(dto, entity);
        return repository.saveFollowUp(entity);
    }

    @Override
    public List<FollowUpDto> getFollowUpDetailsByCustomerId(Long id) {
       List<FollowUpEntity> followUpEntityList= repository.getFollowUpDetailsByCustomerId(id);
       List<FollowUpDto> followUpDtoList=new ArrayList<>();
       for(FollowUpEntity entity: followUpEntityList){
           FollowUpDto dto=new FollowUpDto();
           BeanUtils.copyProperties(entity, dto);
           followUpDtoList.add(dto);
       }
       return followUpDtoList;
    }

    private void sendOtp(String email, Integer otp) {
        final String username = "prajwal.xworkz@gmail.com";
        final String password = "uhvk fzkx chqt zweg";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(otp+" is your one-time-password for login");
            message.setText("Hello!!,"
                    + "\n\nUse the code below to continue - it's valid for 2 minutes:\n" +otp );

            System.out.println("Otp : "+otp);


            Transport.send(message);

        } catch (MessagingException e) {
            log.info(e.getMessage());
        }
    }

    private void setOtp(Integer otp) {
        AdminEntity entity=repository.getAdminDetails((String)session.getAttribute("email"));
        System.out.println(entity);
        entity.setOtp(otp);
        LocalDateTime generateAt=LocalDateTime.now();
        LocalDateTime expiresAt=generateAt.plusMinutes(2);
        entity.setGeneratedAt(generateAt);
        entity.setExpiresAt(expiresAt);
        repository.updateAdmin(entity);
    }

    private int generateOtp() {
        int length=5;
        int min=(int) Math.pow(10,length-1);
        int max=(int) Math.pow(10,length)-1;

        Random random=new Random();
        return min+(int) (random.nextDouble() * (max - min + 1));

    }


    public boolean isValidEmail(String email){
        AdminEntity entity=repository.getAdminDetails(email);
        return entity != null;
    }

    private boolean validateAssignBikeToBranch(BikeToBranchDto dto) {
        System.out.println("validateAssignBikeToBranch in service start");
        System.out.println(dto);
        BikeToBranchEntity entity = new BikeToBranchEntity();
        entity.setBike(repository.findBikeById(dto.getBikeId()));
        entity.setBranch(repository.findBranchById(dto.getBranchId()));
        BeanUtils.copyProperties(dto, entity );
        System.out.println(entity.toString());
        System.out.println(entity);
        System.out.println("branch id: "+entity.getBranch().getId());
        System.out.println("model name: "+entity.getBike().getModelName());
        int count = repository.getBikeCountByBranchId(entity.getBranch().getId());
        entity=repository.getBikeToBranchByModelName(entity.getBike().getModelName());
        System.out.println("no. of bikes assigned to the showroom: "+count);
        System.out.println("getBikeToBranchByModelName: "+entity);
       if(count<2){
           if(entity==null){
               System.out.println("validateAssignBikeToBranch in service end1 success");
               return true;
           }else{
               session.setAttribute("AssignBikeToBranchErrorMsg", "The bike is already assigned!!");
               System.out.println("validateAssignBikeToBranch in service end2 fail (The bike is already assigned!!)");
               return false;
           }
       }else{
           System.out.println("validateAssignBikeToBranch in service end3 fail(Showroom has maximum bikes assigned!!)");
           session.setAttribute("AssignBikeToBranchErrorMsg", "Showroom has maximum bikes assigned!!");
           return  false;
       }
    }

}
