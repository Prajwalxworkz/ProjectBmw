package com.xworkz.website.admin.repo;

import com.xworkz.website.dto.CustomerDto;
import com.xworkz.website.entity.*;

import java.util.List;

public interface AdminRepositoryIntf {
    AdminEntity getAdminDetails(String email);

    void updateAdmin(AdminEntity entity);

    boolean saveBike(BikeEntity bikeEntity);

    boolean saveBranch(BranchEntity branchEntity);

    List<BranchEntity> getAllBranches();

    List<BikeEntity> getAllBikes();

    BikeEntity findBikeById(Long bikeId);

    BranchEntity findBranchById(Long branchId);

    boolean saveBikeToBranch(BikeToBranchEntity entity);

    BikeToBranchEntity findBikeToBranchById(Long bikeId);

    boolean saveCustomer(CustomerEntity entity);

    List<CustomerEntity> getAllCustomerDetails();

    List<CustomerEntity> getCustomerByFilter(String sort);

    CustomerEntity getCustomerById(Long id);

    boolean saveFollowUp(FollowUpEntity entity);

    List<FollowUpEntity> getFollowUpDetailsByCustomerId(Long id);

    int getBikeCountByBranchId(Long id);

    BikeToBranchEntity getBikeToBranchByModelName(String modelName);
}
