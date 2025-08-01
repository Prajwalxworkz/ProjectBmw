package com.xworkz.website.customer.repo;

import com.xworkz.website.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryIntf{

    @Autowired
    EntityManagerFactory emf;
/*
javax.persistence.NoResultException: No entity found for query
Root cause
javax.persistence.NoResultException: No entity found for query
	org.hibernate.query.internal.AbstractProducedQuery.getSingleResult(AbstractProducedQuery.java:1667)
	com.xworkz.website.customer.repo.CustomerRepositoryImpl.getCustomerByEmail(CustomerRepositoryImpl.java:17)
	com.xworkz.website.customer.service.CustomerServiceImpl.getCustomerByEmail(CustomerServiceImpl.java:17)
	com.xworkz.website.customer.controller.CustomerMvcController.validateCustomer(CustomerMvcController.java:50)
	com.xworkz.website.customer.controller.CustomerMvcController.customerLogin(CustomerMvcController.java:28)
 */
    @Override
    public CustomerEntity getCustomerByEmail(String email) {
        try {
            return (CustomerEntity) emf.createEntityManager().createNamedQuery("getCustomerByEmail").setParameter("email", email).getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
