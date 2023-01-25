package be.bstorm.akimts.mvc.repository;

import be.bstorm.akimts.mvc.models.entity.Reservation;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.Set;

@Repository
public class ReservationRepositoryImpl
        extends AbstractCrudRepository<Reservation, Long>
        implements ReservationRepository
{

    public ReservationRepositoryImpl(EMFSharer emfSharer) {
        super(Reservation.class, emfSharer.createEntityManager());
    }

    @Override
    public void adaptId(Long id, Reservation entity) {
        entity.setId(id);
    }

    @Override
    public Set<Reservation> getFromMonth(Month month) {
        return null;
    }

}
