package java12.services;

import java12.daos.daoImpls.ProgrammerDaoImpl;
import java12.daos.daoImpls.ProjectDaoImpl;
import java12.entities.Programmer;
import java12.interfaces.ProgrammerInterface;

import java.util.List;

public class ProgrammerImpl implements ProgrammerInterface {
    ProgrammerDaoImpl programmerDao = new ProgrammerDaoImpl();

    @Override
    public String save(Programmer programmer) {
        return programmerDao.save(programmer);
    }

    @Override
    public String deleteById(Long id) {
        return programmerDao.deleteById(id);
    }

    @Override
    public String updateById(Long id, Programmer newProgrammer) {
        return programmerDao.updateById(id,newProgrammer);
    }

    @Override
    public Programmer findById(Long id) {
        return programmerDao.findById(id);
    }

    @Override
    public List<Programmer> getAllProgrammers() {
        return programmerDao.getAllProgrammers();
    }

    @Override
    public String assignProgrammerToProject(Long programmerId, Long projectId) {
        return programmerDao.assignProgrammerToProject(programmerId,projectId);
    }
}
