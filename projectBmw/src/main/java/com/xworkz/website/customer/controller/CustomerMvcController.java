package com.xworkz.website.customer.controller;



import com.xworkz.website.admin.controller.MvcController;
import com.xworkz.website.customer.service.CustomerServiceIntf;
import com.xworkz.website.dto.BikeDto;
import com.xworkz.website.dto.BranchDto;
import com.xworkz.website.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerMvcController {

    @Autowired
    CustomerServiceIntf service;

    @Autowired
    MvcController admin;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("customerLogin")
    public String customerLogin(@RequestParam String email, String password, RedirectAttributes redirectAttributes){
        boolean isValid=validateCustomer(email, password,redirectAttributes);
        if(isValid){
            return "customer/customerHomePage";
        }
        return "redirect:/customer/loginFailure";

    }

    @GetMapping("getBranchList")
    public String getBranchList(Model model){
      List<BranchDto> branchDtoList=  admin.getBranchList();
      model.addAttribute("branchList",branchDtoList);
      return "customer/branchDisplay";
    }
    @GetMapping("getBikeList")
    public String getBikeList(Model model){
      List<BikeDto> bikeDtoList=  admin.getBikeList();
      model.addAttribute("bikeList",bikeDtoList);
      return "customer/bikeDisplay";
    }

    @GetMapping("downloadBikeImage")
    public void getImage(HttpServletResponse response, @RequestParam String picture, @RequestParam String model){
        admin.downloadBikeImage(response, picture, model);
    }
    @GetMapping("downloadBranchImage")
    public void getImage(HttpServletResponse response, @RequestParam String picture){
        admin.downloadBranchImage(response, picture);
    }

    private boolean validateCustomer(String email, String password, RedirectAttributes redirectAttributes) {
        boolean isValid=true;
        if(email==null || email.trim().isEmpty()  ){
            System.out.println("email is empty");
            isValid=false;
            redirectAttributes.addFlashAttribute("emptyEmail", "*Email field can not be empty");
        }
        if( password==null ||password.trim().isEmpty() ){
            System.out.println("password is empty");
            isValid=false;
            redirectAttributes.addFlashAttribute("emptyPassword","*Password field can not be empty");
            return  isValid;
        }
        CustomerDto dto=service.getCustomerByEmail(email);
        if(dto==null){
            System.out.println("dto is null");
            isValid=false;
            redirectAttributes.addFlashAttribute("invalidEmail","*User is not registered.");
            return isValid;
        }else{
            if(!passwordEncoder.matches(password, dto.getPassword())){
                System.out.println("password won't match");
                isValid=false;
                redirectAttributes.addFlashAttribute("invalidPassword","*Invalid password");
            }
        }

    return isValid;
    }

    @GetMapping("loginFailure")
    public String loginFailure(){
        return "customer/customerLogin";
    }
}
