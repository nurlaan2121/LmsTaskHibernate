package java12.daos;

import java12.entities.Project;

import java.util.List;

public interface ProjectDaoInterface {
    String save(Project project);
    String save(Long companyId,Project project);
    String deleteById(Long id);
    Project findById(Long id);
    List<Project> getAllProjects();
    String updateById(Long id,Project newProject);
    String assignProjectToCompany(Long idProject,Long idCompany);
}
