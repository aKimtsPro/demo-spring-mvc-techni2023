package be.bstorm.akimts.mvc.repository;

import be.bstorm.akimts.mvc.models.entity.Reservation;

import java.time.LocalDate;
import java.time.Month;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    void cancel(long reservationId);

    void getReservationForMonth(Month month);

    void getBreakfastNeedFor(LocalDate date);



}