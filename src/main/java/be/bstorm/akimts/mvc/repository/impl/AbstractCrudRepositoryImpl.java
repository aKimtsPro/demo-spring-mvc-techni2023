package be.bstorm.akimts.mvc.repository.impl;

import be.bstorm.akimts.mvc.repository.CrudRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudRepositoryImpl<TENTITY, TID> implements CrudRepository<TENTITY, TID> {

    private final Class<TENTITY> entityClass;
    protected final EntityManager manager;

    protected AbstractCrudRepositoryImpl(Class<TENTITY> entityClass, EntityManager manager) {
        this.entityClass = entityClass;
        this.manager = manager;
    }

    @Override
    public void add(TENTITY toInsert) {
        manager.getTransaction().begin();
        manager.persist(toInsert);
        manager.getTransaction().commit();
    }

    @Override
    public Optional<TENTITY> get(TID id) {
        return Optional.ofNullable( manager.find(entityClass, id) );
    }

    @Override
    public List<TENTITY> getAll() {
        String queryName = "GET_ALL_"+entityClass.getSimpleName().toUpperCase();
//        verifyNamedQuery(queryName);
        TypedQuery<TENTITY> query = manager.createNamedQuery(queryName, entityClass);
//        String qlQuery = "SELECT t FROM " + entityClass.getSimpleName() + " t";
//        TypedQuery<TENTITY> query = manager.createQuery(qlQuery, entityClass);
        List<TENTITY> list = query.getResultList();
        manager.clear();
        return list;

    }

    @Override
    public void remove(TID id) {
        TENTITY entity = manager.find(entityClass, id);
        manager.getTransaction().begin();
        manager.remove(entity);
        manager.getTransaction().commit();
    }

    private void verifyNamedQuery(String name){
        NamedQueries nqs = entityClass.getAnnotation(NamedQueries.class);
        if( nqs == null || Arrays.stream(nqs.value()).noneMatch(nq -> nq.name().equals(name)) )
                throw new RuntimeException("NamedQuery with name 'GET_ALL_" + entityClass.getSimpleName() + "' must exist");
    }

    @Override
    public void update(TID id, TENTITY entity) {
        adaptId(id, entity);
        if( get(id).isPresent() ){
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
        }
    }

    protected abstract void adaptId(TID id, TENTITY tentity);
}
