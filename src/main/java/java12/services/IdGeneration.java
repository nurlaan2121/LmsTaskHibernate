package java12.services;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;

@MappedSuperclass
@Data
public class IdGeneration{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_generator")
    private Long id;
    @Override
    public String toString() {
        return "id=" + id ;
    }
}
