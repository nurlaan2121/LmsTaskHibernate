package java12.entities;

import jakarta.persistence.*;
import java12.services.IdGeneration;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "projects")
@Data
@ToString(callSuper = true)

@SequenceGenerator(name = "id_generator",sequenceName = "project_id_seq",allocationSize = 1)
public class Project extends IdGeneration {
    private String title;
    @ManyToOne()
    private Company company;
    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
    private List<Programmer> programmers;

    public Project(String title, List<Programmer> programmers) {
        this.title = title;
        this.programmers = programmers;
    }
    public Project() {

    }

}
