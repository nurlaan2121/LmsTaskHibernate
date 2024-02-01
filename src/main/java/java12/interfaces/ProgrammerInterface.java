package java12.interfaces;

import java12.entities.Programmer;

import java.util.List;

public interface ProgrammerInterface {
    String save(Programmer programmer);
    String deleteById(Long id);
    String updateById(Long id,Programmer newProgrammer);
    Programmer findById(Long id);
    List<Programmer> getAllProgrammers();
    String assignProgrammerToProject(Long programmerId,Long projectId);
}
