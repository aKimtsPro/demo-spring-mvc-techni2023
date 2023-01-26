package be.bstorm.akimts.mvc.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", nullable = false)
    private long id;

    @Column(name= "stars",scale = 1)
    private int stars;

    @OneToMany(mappedBy = "hotel")
    private Set<WorkDetail> workDetails;

    @OneToOne
    @JoinColumn(name = "receptionist_id", unique = true)
    private Employee receptionist;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms = new LinkedHashSet<>();


}
