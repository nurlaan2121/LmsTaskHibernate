package java12.daos.daoImpls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java12.congifs.HibernateConfig;
import java12.daos.ProjectDaoInterface;
import java12.entities.Company;
import java12.entities.Project;
import java12.exceptions.NotFoundException;
import org.hibernate.HibernateException;

import java.util.Collections;
import java.util.List;

public class ProjectDaoImpl implements ProjectDaoInterface {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManagerFactory();

    @Override
    public String save(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
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
    public String save(Long companyId, Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, companyId);
            company.getProjects().add(project);
            project.setCompany(company);
            entityManager.merge(company);
            entityManager.merge(project);
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
            Project byId = entityManager.find(Project.class,id);
            entityManager.remove(byId);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException w) {
            w.printStackTrace();
        } finally {
            entityManager.close();
        }
        return "Error";
    }

    @Override
    public Project findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            if (project != null) {
                return project;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Not found");
    }

    @Override
    public List<Project> getAllProjects() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Project> selectPFromProjectP = entityManager.createQuery("select p from Project p", Project.class).getResultList();
            entityManager.getTransaction().commit();
            return selectPFromProjectP;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public String updateById(Long id, Project newProject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Project byId = findById(id);
            byId.setProgrammers(newProject.getProgrammers());
            byId.setTitle(newProject.getTitle());
            byId.setCompany(newProject.getCompany());
            entityManager.merge(byId);
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
    public String assignProjectToCompany(Long idProject, Long idCompany) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, idProject);
            Company company = entityManager.find(Company.class, idCompany);
            if (project != null) {
                if (company != null) {
                    project.setCompany(company);
                    company.getProjects().add(project);
                    entityManager.merge(project);
                    entityManager.merge(company);
                    entityManager.getTransaction().commit();
                    return "Success";
                } else {
                    return "not found company" + idCompany;
                }

            } else {
                return "Not found project" + idProject;
            }
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            entityManager.close();
        }
        return "Error";
    }
}
