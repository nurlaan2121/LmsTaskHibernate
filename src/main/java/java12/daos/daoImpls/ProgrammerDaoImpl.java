package java12.daos.daoImpls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.congifs.HibernateConfig;
import java12.daos.ProgrammerDaoInterface;
import java12.entities.Programmer;
import java12.entities.Project;
import org.hibernate.HibernateException;

import java.util.Collections;
import java.util.List;

public class ProgrammerDaoImpl implements ProgrammerDaoInterface {
    ProjectDaoImpl projectDao = new ProjectDaoImpl();
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String save(Programmer programmer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(programmer);
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
    public String deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.find(Programmer.class, id);
            for (Project project : programmer.getProjects()) {
                project.getProgrammers().remove(programmer);
            }
            entityManager.remove(programmer);
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
    public String updateById(Long id, Programmer newProgrammer) {
        Programmer byId = findById(id);
        if (byId != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                byId.setAddress(newProgrammer.getAddress());
                byId.setEmail(newProgrammer.getEmail());
                byId.setFullName(newProgrammer.getFullName());
                byId.setProjects(newProgrammer.getProjects());
                entityManager.merge(byId);
                entityManager.getTransaction().commit();
                return "Sussess";
            } catch (HibernateException e) {
                e.printStackTrace();
            } finally {
                entityManager.close();
            }

        } else {
            return id + "not found";
        }
        return "Error";
    }

    @Override
    public Programmer findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.find(Programmer.class, id);
            entityManager.getTransaction().commit();
            return programmer;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Programmer> getAllProgrammers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Programmer> selectPFromProgrammerP = entityManager.createQuery("select p from Programmer p", Programmer.class).getResultList();
            entityManager.getTransaction().commit();
            return selectPFromProgrammerP;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public String assignProgrammerToProject(Long programmerId, Long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.createQuery("select u from Programmer u where u.id = :progId", Programmer.class).setParameter("progId",programmerId).getSingleResult();
            Project project = entityManager.createQuery("select u from Project u where u.id = :projId", Project.class).setParameter("projId",projectId).getSingleResult();
            if (programmer != null) {
                if (project != null) {
                    programmer.getProjects().add(project);
                    project.getProgrammers().add(programmer);
                    entityManager.merge(project);
                    entityManager.merge(programmer);
                    entityManager.getTransaction().commit();
                    return "Success";
                } else {
                    return "not found project " + projectId;
                }
            } else {
                return "not found programmer  " + programmerId;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return "Error";
    }
}
