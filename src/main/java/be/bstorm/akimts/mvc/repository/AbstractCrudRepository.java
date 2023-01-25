package be.bstorm.akimts.mvc.repository;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudRepository<TENTITY, TID> implements CrudRepository<TENTITY, TID>{

    private final Class<TENTITY> entityClass;
    private final EntityManager entityManager;

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
    public void create(TENTITY entity) {

        entityManager.getTransaction().begin();
        entityManager.persist( entity );
        entityManager.getTransaction().commit();

    }

    @Override
    public void update(TID id, TENTITY entity) {

        if( getById(id).isPresent() ){
            entityManager.getTransaction().begin();
            adaptId(id, entity);
            entityManager.merge( entity );
            entityManager.getTransaction().commit();
        }

    }

    public abstract void adaptId(TID id, TENTITY entity);

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
}
