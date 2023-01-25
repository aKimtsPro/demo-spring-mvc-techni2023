package be.bstorm.akimts.mvc.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private Long id;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Column(name = "additional_beds", nullable = false)
    private int additionalBeds;

    @Column(nullable = false)
    private double price;

    private Double discount;

    @Column(name = "breakfast_included")
    private boolean breakfastIncluded;

    @Column(name = "reservation_start", nullable = false)
    private LocalDateTime start;
    @Column(name = "reservation_end", nullable = false)
    private LocalDateTime end;

    @ManyToOne//(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne//(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

}
