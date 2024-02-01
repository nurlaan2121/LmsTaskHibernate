package java12.daos.daoImpls;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.congifs.HibernateConfig;
import java12.daos.AddressDaoInterface;
import java12.entities.Address;
import java12.exceptions.NotFoundException;
import org.hibernate.HibernateException;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String saveAddress(Address newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newAddress);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (EntityExistsException e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Address already exists";
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Write correct info";
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address address = findAddressById(addressId).orElseThrow(() -> new NotFoundException("Not found"));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(address);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "Error deleted";
    }


    public Optional<Address> findAddressById(Long addressId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            entityManager.getTransaction().commit();
            return Optional.ofNullable(address);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public List<Address> getAllAddresses() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Address> resultList = entityManager.createQuery("select  a from Address a", Address.class).getResultList();
            entityManager.getTransaction().commit();
            return resultList;
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public String updateAddressById(Long addressId, Address newAddress) {
        Address address = findAddressById(addressId).orElseThrow(() -> new NotFoundException("Not found"));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            address.setCountry(newAddress.getCountry());
            address.setProgrammer(newAddress.getProgrammer());
            entityManager.merge(address);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return "Error updated";
    }
}
