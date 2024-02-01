package java12.services;

import java12.daos.daoImpls.CompanyDaoImpl;
import java12.entities.Company;
import java12.exceptions.NotFoundException;
import java12.interfaces.CompanyInterface;

import java.util.List;
import java.util.Optional;

public class CompanyImpl implements CompanyInterface {
    CompanyDaoImpl companyDao = new CompanyDaoImpl();

    @Override
    public String createCompany(Company newCompany) {
        return companyDao.saveCompany(newCompany);
    }

    @Override
    public String removeById(Long companyId) {
        try {
            return companyDao.deleteById(companyId);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public Optional<Company> findById(Long companyId) {
        try {
            return companyDao.findById(companyId);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }

    @Override
    public String updateById(Long id, Company newCompany) {
        return companyDao.updateById(id,newCompany);
    }

    @Override
    public String assignCompanyToAddress(Long companyId, Long addressId) {
        return companyDao.assignCompanyToAddress(companyId,addressId);
    }
}
