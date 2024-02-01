package java12.entities;

import jakarta.persistence.*;
import java12.services.IdGeneration;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programmers")
@Data
@SequenceGenerator(name = "id_generator", sequenceName = "programmer_id_seq", allocationSize = 1)
@ToString(callSuper = true)

public class Programmer extends IdGeneration {
    private String fullName;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "programmers",cascade = {CascadeType.DETACH,CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Project> projects;


    @OneToOne(mappedBy = "programmer", cascade = CascadeType.ALL)
    private Address address;

    public Programmer(String fullName, String email,Address address) {
        this.fullName = fullName;
        this.email = email;

        this.address = address;
    }

    public Programmer() {
    }

    public Programmer(String fullName, String email, List<Project> projects) {
        this.fullName = fullName;
        this.email = email;
        this.projects = projects;
    }

    public Programmer(String fullName, String email, List<Project> projects, Address address) {
        this.fullName = fullName;
        this.email = email;
        this.projects = projects;
        this.address = address;
    }

    public Programmer(String s, String s1) {
        this.fullName = s;
        this.email = s1;
    }
}
