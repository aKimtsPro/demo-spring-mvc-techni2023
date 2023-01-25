package be.bstorm.akimts.mvc.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Room {

    @Id
    @Column(name = "room_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_view", nullable = false)
    private RoomView view;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    public int floor;

    @Column(name = "simple_beds", nullable = false)
    private int simpleBeds;

    @Column(name = "double_beds", nullable = false)
    private int doubleBeds;

    @Column(name = "additionnal_bed", nullable = false)
    public int additionnalBeds;

    @Column(nullable = false)
    public double size;

    @Column(nullable = false)
    private boolean available;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType type;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations = new LinkedHashSet<>();

}
