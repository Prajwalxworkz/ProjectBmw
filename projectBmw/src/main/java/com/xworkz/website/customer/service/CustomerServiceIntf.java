package com.xworkz.website.customer.service;

import com.xworkz.website.dto.CustomerDto;

public interface CustomerServiceIntf {
    CustomerDto getCustomerByEmail(String email);
}
