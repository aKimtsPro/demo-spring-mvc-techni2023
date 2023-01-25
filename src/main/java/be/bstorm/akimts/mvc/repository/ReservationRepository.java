package be.bstorm.akimts.mvc.repository;

import be.bstorm.akimts.mvc.models.entity.Reservation;

import java.time.Month;
import java.util.Set;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Set<Reservation> getFromMonth(Month month);

}
