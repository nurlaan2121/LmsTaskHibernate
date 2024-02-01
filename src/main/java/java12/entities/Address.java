package java12.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java12.services.IdGeneration;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "addresses")
@Data
@ToString(callSuper = true)
@SequenceGenerator(name = "id_generator",sequenceName = "address_id_seq",allocationSize = 1)
public class Address extends IdGeneration{
    private String country;
    @OneToOne
    private Programmer programmer;
    public Address(String country, Programmer programmer) {
        this.country = country;
        this.programmer = programmer;
    }

    public Address(String country) {
        this.country = country;
    }

    public Address() {
    }
}
