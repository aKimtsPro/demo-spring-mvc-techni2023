package be.bstorm.akimts.mvc.repository.impl;

import be.bstorm.akimts.mvc.models.entity.Reservation;
import be.bstorm.akimts.mvc.repository.ReservationRepository;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;

@Repository
public class ReservationRepositoryImpl extends AbstractCrudRepositoryImpl<Reservation, Long> implements ReservationRepository {

    public ReservationRepositoryImpl(EMFSharer emfSharer) {
        super(Reservation.class, emfSharer.createEntityManager());
    }

    @Override
    protected void adaptId(Long id, Reservation reservation) {
        reservation.setId(id);
    }


    @Override
    public void cancel(long reservationId) {

    }

    @Override
    public void getReservationForMonth(Month month) {

    }

    @Override
    public void getBreakfastNeedFor(LocalDate date) {

    }
}
