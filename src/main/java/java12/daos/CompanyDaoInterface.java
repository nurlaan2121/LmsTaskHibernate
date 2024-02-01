package java12.daos;

import java12.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDaoInterface {
    String saveCompany(Company newCompany);
    String deleteById(Long companyId);
    Optional<Company> findById(Long companyId);
    List<Company> getAllCompanies();
    String updateById(Long id,Company newCompany);
    String assignCompanyToAddress(Long companyId,Long addressId);

}
