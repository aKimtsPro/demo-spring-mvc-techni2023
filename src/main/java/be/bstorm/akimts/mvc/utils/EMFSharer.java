package be.bstorm.akimts.mvc.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public final class EMFSharer {

    public EMFSharer() {
        this.emf = Persistence.createEntityManagerFactory("hotel-manager");
    }


    private final EntityManagerFactory emf;


    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager createEntityManager(){
        return emf.createEntityManager();
    }

    public void close(){
        emf.close();
    }
}
