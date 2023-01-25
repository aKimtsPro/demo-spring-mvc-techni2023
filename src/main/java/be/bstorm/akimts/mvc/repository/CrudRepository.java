package be.bstorm.akimts.mvc.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<TENTITY, TID> {

    List<TENTITY> getAll();
    Optional<TENTITY> getById(TID id);

    void create(TENTITY entity);

    void update(TID id, TENTITY entity);

    void delete(TENTITY entity);
    void deleteById(TID id);

}
