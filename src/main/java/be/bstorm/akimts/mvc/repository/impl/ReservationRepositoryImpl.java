package be.bstorm.akimts.mvc.repository.impl;

import be.bstorm.akimts.mvc.models.entity.Reservation;
import be.bstorm.akimts.mvc.repository.AbstractCrudRepository;
import be.bstorm.akimts.mvc.repository.ReservationRepository;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import jakarta.persistence.TypedQuery;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.query.sqm.TemporalUnit;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
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

        LocalDateTime firstDate = LocalDateTime.of(
                year, month, 1,
                0,0,0
        );
        LocalDateTime endDate = LocalDateTime.of(
                year, month, month.length( Year.isLeap(year) ),
                23,59,59,999_999_999
        );

        String qlQuery = "SELECT r FROM Reservation r WHERE r.start < ?2 AND r.end > ?1";
        TypedQuery<Reservation> query = entityManager.createQuery(qlQuery, Reservation.class);
        query.setParameter(1, firstDate);
        query.setParameter(2, endDate);

        List<Reservation> reservations = query.getResultList();
        entityManager.clear();
        return reservations;

    }

    @Override
    public long getBreakfastNeededForDay(LocalDate date) {

        LocalDateTime onMorning = date.atTime(8,0,0);
        String qlQuery = """
                    SELECT sum(r.additionalBeds + (r.room.doubleBeds * 2) + r.room.simpleBeds)
                    FROM Reservation r
                    WHERE (?1 BETWEEN r.start AND r.end) AND r.breakfastIncluded
                    """;

        TypedQuery<Long> query = entityManager.createQuery(qlQuery, Long.class);

        query.setParameter(1, onMorning);

        return query.getSingleResult();
    }

    @Override
    public List<Reservation> getFutureReservation() {
        String qlQuery = "SELECT r FROM Reservation r WHERE r.start > ?1";
        TypedQuery<Reservation> query = entityManager.createQuery(qlQuery, Reservation.class);

        query.setParameter(1, LocalDateTime.now());

        List<Reservation> reservations = query.getResultList();
        entityManager.clear();
        return reservations;
    }

    @Override
    public List<Reservation> getFutureShortReservation() {
        String qlQuery = """
                SELECT r FROM Reservation r WHERE r.start > ?1 AND
                (
                    SELECT date_part('day', res.end - res.start)
                    FROM Reservation res
                    WHERE res.id = r.id
                ) < 7
                """;
        TypedQuery<Reservation> query = entityManager.createQuery(qlQuery, Reservation.class);

        query.setParameter(1, LocalDateTime.now());

        List<Reservation> reservations = query.getResultList();
        entityManager.clear();
        return reservations;
    }

    @Override
    public List<Reservation> getFutureLongReservation() {

        String qlQuery = """
                SELECT r FROM Reservation r WHERE r.start > ?1 AND
                (
                    SELECT date_part('day', res.end - res.start)
                    FROM Reservation res
                    WHERE res.id = r.id
                ) >= 7
                """;
        TypedQuery<Reservation> query = entityManager.createQuery(qlQuery, Reservation.class);

        query.setParameter(1, LocalDateTime.now());

        List<Reservation> reservations = query.getResultList();
        entityManager.clear();
        return reservations;
    }

}
