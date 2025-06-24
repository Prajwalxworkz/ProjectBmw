package com.xworkz.website.admin.repo;

import com.xworkz.website.admin.constants.Schedule;
import com.xworkz.website.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class AdminRepositoryImpl implements AdminRepositoryIntf {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public AdminEntity getAdminDetails(String email) {
       EntityManager em= emf.createEntityManager();
       AdminEntity entity=(AdminEntity) em.createNamedQuery("getAdminDetails").setParameter("email",email).getSingleResult();
       return entity;
    }

    @Override
    public void updateAdmin(AdminEntity entity) {
       EntityManager em= emf.createEntityManager();
       try {
           AdminEntity adminEntity=em.find(AdminEntity.class,entity.getId());
           if(adminEntity!=null) {
               em.getTransaction().begin();
               em.merge(entity);
               em.getTransaction().commit();
           }else{
               System.out.println("In repo updateAdmin new entity ");
           }
       }catch (Exception e){
           em.getTransaction().rollback();
           log.info(e.getMessage());
       }finally {
           em.close();
       }
    }

    @Override
    public boolean saveBike(BikeEntity bikeEntity) {
        EntityManager em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(bikeEntity);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            log.info(e.getMessage());
        }finally {
            em.close();
        }
        return false;
    }

    @Override
    public boolean saveBranch(BranchEntity branchEntity) {
        EntityManager em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(branchEntity);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            log.info(e.getMessage());
        }finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<BranchEntity> getAllBranches() {
        EntityManager em=emf.createEntityManager();
        return em.createNamedQuery("getAllBranches").getResultList();

    }

    @Override
    public List<BikeEntity> getAllBikes() {
        EntityManager em=emf.createEntityManager();
        return em.createNamedQuery("getAllBikes").getResultList();

    }

    @Override
    public BikeEntity findBikeById(Long bikeId) {
        EntityManager em= emf.createEntityManager();
        return (BikeEntity) em.createNamedQuery("findBikeById").setParameter("id",bikeId).getSingleResult();
    }

    @Override
    public BranchEntity findBranchById(Long branchId) {
        EntityManager em= emf.createEntityManager();
        return (BranchEntity) em.createNamedQuery("findBranchById").setParameter("id",branchId).getSingleResult();
    }

    @Override
    public boolean saveBikeToBranch(BikeToBranchEntity entity) {
        EntityManager em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            log.info(e.getMessage());
        }finally {
            em.close();
        }
        return false;
    }

    @Override
    public BikeToBranchEntity findBikeToBranchById(Long bikeId) {
        return (BikeToBranchEntity) emf.createEntityManager().createNamedQuery("findBikeToBranchById").setParameter("id",bikeId).getSingleResult();
    }

    @Override
    public boolean saveCustomer(CustomerEntity entity) {
        EntityManager em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            log.info(e.getMessage());
        }finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<CustomerEntity> getAllCustomerDetails() {
        return emf.createEntityManager().createNamedQuery("getCustomerDetails").getResultList();
    }

    @Override
    public List<CustomerEntity> getCustomerByFilter(String sort) {
        Schedule scheduleEnum;
        try {
            scheduleEnum = Schedule.valueOf(sort);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid schedule value: " + sort);
        }
        return  emf.createEntityManager().createQuery("select entity from CustomerEntity entity where entity.schedule=: schedule", CustomerEntity.class).setParameter("schedule",scheduleEnum).getResultList();
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        return (CustomerEntity) emf.createEntityManager().createQuery("select customer from CustomerEntity customer where customer.id=: id").setParameter("id", id).getSingleResult();
    }

    @Override
    public boolean saveFollowUp(FollowUpEntity entity) {
        EntityManager em=emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            log.info(e.getMessage());
        }finally {
            em.close();
        }
        return false;
    }

    @Override
    public List<FollowUpEntity> getFollowUpDetailsByCustomerId(Long id) {
        return emf.createEntityManager().createNamedQuery("getFollowUpDetailsByCustomerId").setParameter("id",id).getResultList();
    }

    @Override
    public int getBikeCountByBranchId(Long id) {
       return  ((Long)emf.createEntityManager().createNamedQuery("getBikeCountByBranchId").setParameter("id", id).getSingleResult()).intValue();
    }

    @Override
    public BikeToBranchEntity getBikeToBranchByModelName(String modelName) {
        return (BikeToBranchEntity) emf.createEntityManager().createNamedQuery("getBikeToBranchByModelName").setParameter("name", modelName).getSingleResult();

    }
}
