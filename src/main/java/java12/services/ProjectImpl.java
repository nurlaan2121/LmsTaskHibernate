package java12.services;

import java12.daos.daoImpls.ProjectDaoImpl;
import java12.entities.Project;
import java12.interfaces.ProjectInterface;

import java.util.List;

public class ProjectImpl implements ProjectInterface {
    ProjectDaoImpl projectDao  = new ProjectDaoImpl();
    @Override
    public String save(Project project) {
        return projectDao.save(project);
    }

    @Override
    public String save(Long companyId, Project project) {
        return projectDao.save(companyId,project);
    }

    @Override
    public String deleteById(Long id) {
        return projectDao.deleteById(id);
    }

    @Override
    public Project findById(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @Override
    public String updateById(Long id, Project newProject) {
        return projectDao.updateById(id,newProject);
    }

    @Override
    public String assignProjectToCompany(Long idProject, Long idCompany) {
        return projectDao.assignProjectToCompany(idProject,idCompany);
    }
}
