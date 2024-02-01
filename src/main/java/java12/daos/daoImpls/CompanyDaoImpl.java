package java12.daos.daoImpls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java12.congifs.HibernateConfig;
import java12.daos.CompanyDaoInterface;
import java12.entities.Address;
import java12.entities.Company;
import java12.exceptions.NotFoundException;
import org.hibernate.HibernateException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CompanyDaoImpl implements CompanyDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();
    AddressDaoImpl addressDao = new AddressDaoImpl();

    @Override
    public String saveCompany(Company newCompany) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCompany);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "error";
    }

    @Override
    public String deleteById(Long companyId) {
        Company company = findById(companyId).orElseThrow(() -> new NotFoundException("Not found"));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(company);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "Error";

    }

    @Override
    public Optional<Company> findById(Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, companyId);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(company);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<Company> getAllCompanies() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Company> selectCFromCompanyC = entityManager.createQuery("select c from Company c", Company.class).getResultList();
            transaction.commit();
            return selectCFromCompanyC;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public String updateById(Long id, Company newCompany) {
        Company company = findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            company.setAddress(newCompany.getAddress());
            company.setName(newCompany.getName());
            company.setProjects(newCompany.getProjects());
            entityManager.merge(company);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return "Error";
    }

    @Override
    public String assignCompanyToAddress(Long companyId, Long addressId) {
        Company company = findById(companyId).orElseThrow(() -> new NotFoundException("Not found"));
        Address address = addressDao.findAddressById(addressId).orElseThrow(() -> new NotFoundException("Not found"));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            company.setAddress(address);
            entityManager.merge(company);
            entityManager.getTransaction().commit();
            return "Success";
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return "Error";
    }
}
