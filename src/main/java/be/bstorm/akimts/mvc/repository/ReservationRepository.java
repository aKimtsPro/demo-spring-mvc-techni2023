package be.bstorm.akimts.mvc.repository;

import be.bstorm.akimts.mvc.models.entity.Reservation;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    /**
     * Gets the reservations that begins or ends in a month
     * @param month
     * @param year
     * @return the set of reservation for a specific month
     */
    List<Reservation> getFromMonth(Month month, int year);

    /**
     * Gets the number of breakfast that should be prepared for a day
     *
     * @param date
     * @return the number of breakfast
     */
    long getBreakfastNeededForDay(LocalDate date);
    /**
     * Gets the reservations that begin in the future
     * @return a Set of reservation
     */
    List<Reservation> getFutureReservation();
    /**
     * Gets the reservations that begin in the future and last less than a week
     * @return a Set of reservation
     */
    List<Reservation> getFutureShortReservation();
    /**
     * Gets the reservations that begin in the future and last a week or more
     * @return a Set of reservation
     */
    List<Reservation> getFutureLongReservation();

}
