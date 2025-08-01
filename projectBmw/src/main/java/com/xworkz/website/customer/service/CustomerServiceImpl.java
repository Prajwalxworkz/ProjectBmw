package com.xworkz.website.customer.service;

import com.xworkz.website.customer.repo.CustomerRepositoryIntf;
import com.xworkz.website.dto.CustomerDto;
import com.xworkz.website.entity.CustomerEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerServiceIntf{
    @Autowired
    CustomerRepositoryIntf repository;

    @Override
    public CustomerDto getCustomerByEmail(String email) {
       CustomerEntity entity= repository.getCustomerByEmail(email);
       CustomerDto dto=new CustomerDto();
       if(entity==null){
           return  null;
       }
       BeanUtils.copyProperties(entity, dto);
       return dto;
    }
}
