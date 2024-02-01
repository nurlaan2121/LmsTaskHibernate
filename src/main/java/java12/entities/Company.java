package java12.entities;

import jakarta.persistence.*;
import java12.services.IdGeneration;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@ToString(callSuper = true)
@SequenceGenerator(name = "id_generator",sequenceName = "company_id_seq",allocationSize = 1)
public class Company extends IdGeneration {
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    private Address address;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Project> projects;

    public Company(String name, List<Project> projects) {
        this.name = name;
        this.projects = projects;
    }

    public Company() {

    }
}
