package be.bstorm.akimts.mvc.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Set;

@Entity
@Getter @Setter
public class Employee extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "employee")
    private Set<WorkDetail> workDetails;

}
