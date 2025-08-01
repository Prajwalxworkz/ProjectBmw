package com.xworkz.website.customer.repo;

import com.xworkz.website.entity.CustomerEntity;

public interface CustomerRepositoryIntf {
    CustomerEntity getCustomerByEmail(String email);
}
