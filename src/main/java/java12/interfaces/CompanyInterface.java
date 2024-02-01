package java12.interfaces;

import java12.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyInterface {
    String createCompany(Company newCompany);
    String removeById(Long companyId);
    Optional<Company> findById(Long companyId);
    List<Company> getAllCompanies();
    String updateById(Long id,Company newCompany);
    String assignCompanyToAddress(Long companyId,Long addressId);
}
