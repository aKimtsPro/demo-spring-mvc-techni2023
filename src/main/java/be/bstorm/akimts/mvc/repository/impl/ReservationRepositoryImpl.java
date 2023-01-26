package be.bstorm.akimts.mvc.repository.impl;

import be.bstorm.akimts.mvc.models.entity.Reservation;
import be.bstorm.akimts.mvc.repository.AbstractCrudRepository;
import be.bstorm.akimts.mvc.repository.ReservationRepository;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Repository
public class ReservationRepositoryImpl
        extends AbstractCrudRepository<Reservation, Long>
        implements ReservationRepository
{

    public ReservationRepositoryImpl(EMFSharer emfSharer) {
        super(Reservation.class, emfSharer.createEntityManager());
    }


    @Override
    public List<Reservation> getFromMonth(Month month, int year) {

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;

        LocalDateTime firstDate = LocalDateTime.of(
                year, month, 1,
                0,0,0
        );
        LocalDateTime endDate = LocalDateTime.of(
                year, month, month.length(isLeapYear),
                23,59,59,999_999_999
        );

        String qlQuery = "SELECT r FROM Reservation r WHERE (r.start BETWEEN ?1 AND ?2) OR (r.end BETWEEN ?1 AND ?2)";
        TypedQuery<Reservation> query = entityManager.createQuery(qlQuery, Reservation.class);
        query.setParameter(1, firstDate);
        query.setParameter(2, endDate);

        List<Reservation> reservations = query.getResultList();
        entityManager.clear();
        return reservations;

    }

    @Override
    public int getBreakfastNeededForDay(LocalDate date) {
        return 0;
    }

    @Override
    public List<Reservation> getFutureReservation() {
        return null;
    }

    @Override
    public List<Reservation> getFutureShortReservation() {
        return null;
    }

    @Override
    public List<Reservation> getFutureLongReservation() {
        return null;
    }

}
