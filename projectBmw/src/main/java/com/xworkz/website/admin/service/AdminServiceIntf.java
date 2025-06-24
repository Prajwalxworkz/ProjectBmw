package com.xworkz.website.admin.service;

import com.xworkz.website.dto.*;

import java.util.List;

public interface AdminServiceIntf {
   boolean handleOtpRequest(String email);

   boolean verifyOtpAndLogin(Integer otp);

   boolean saveBike(BikeDto bikeDto);

   boolean saveBranch(BranchDto branchDto);

   List<String> getAllBranchNames();

   List<String> getAllBikeNames();

   List<BranchDto> getAllBranches();

   List<BikeDto> getAllBikes();

   boolean assignBikeToBranch(BikeToBranchDto dto);

    boolean saveCustomer(CustomerDto dto);

   List<CustomerDto> getAllCustomerDetails();

   List<CustomerDto> getCustomerByFilter(String sort);

   CustomerDto getCustomerById(Long id);

   boolean saveFollowUp(FollowUpDto dto);

   List<FollowUpDto> getFollowUpDetailsByCustomerId(Long id);
}
