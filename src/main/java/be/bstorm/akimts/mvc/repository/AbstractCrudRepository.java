package be.bstorm.akimts.mvc.repository;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudRepository<TENTITY, TID> implements CrudRepository<TENTITY, TID>{

    private final Class<TENTITY> entityClass;
    protected final EntityManager entityManager;

    public AbstractCrudRepository(Class<TENTITY> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    @Override
    public List<TENTITY> getAll() {

        String entityName = entityClass.getSimpleName();
        String qlQuery = String.format("SELECT e FROM %s e", entityName);
        TypedQuery<TENTITY> query = entityManager.createQuery(qlQuery, entityClass);
        List<TENTITY> list = query.getResultList();
        entityManager.clear();
        return list;

    }

    @Override
    public Optional<TENTITY> getById(TID id) {

        TENTITY tentity = entityManager.find(entityClass, id);
        entityManager.clear();
        return Optional.ofNullable( tentity );

    }

    @Override
    public void save(TENTITY entity) {

        entityManager.getTransaction().begin();
        entityManager.merge( entity );
        entityManager.getTransaction().commit();

    }


    @Override
    public void delete(TENTITY entity) {

        entityManager.getTransaction().begin();
        TENTITY managedEntity = entityManager.merge(entity);
        entityManager.remove( managedEntity );
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteById(TID id) {

//        Field[] fields = entityClass.getDeclaredFields();
//        String idName = null;
//
//        for (Field field : fields) {
//            if( field.isAnnotationPresent( Id.class ) ){
//                idName = field.getName();
//                break;
//            }
////            Column column = field.getAnnotation(Column.class);
////            if( column != null ){
////                System.out.println( column.name() );
////            }
//        }
//
//        if( idName == null ){
//            throw new RuntimeException("entityClass is not an Entity class");
//        }
//
//        String qlQuery = String.format("DELETE FROM %s WHERE %s = ?1", entityClass.getSimpleName(), idName);
//        Query query = entityManager.createQuery(qlQuery);
//
//        query.setParameter(1, id);
//
//        entityManager.getTransaction().begin();
//        query.executeUpdate();
//        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        TENTITY entity = entityManager.find( entityClass, id );
        entityManager.remove(entity);
        entityManager.getTransaction().commit();

    }

    @Override
    public boolean existsById(TID id) {
        return entityManager.find(entityClass, id) != null;
    }
}
