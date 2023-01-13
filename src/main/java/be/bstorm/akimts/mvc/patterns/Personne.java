package be.bstorm.akimts.mvc.patterns;

import lombok.Data;

@Data
public class Personne {

    private String prenom;
    private String nom;

    private Personne(String prenom, String nom){
        this.prenom = prenom;
        this.nom = nom;
    }

    public static Builder builder(String prenom){
        return new Builder(prenom);
    }

    public static class Builder {

        private final String prenom;
        private String nom;

        public Builder(String prenom) {
            this.prenom = prenom;
        }

        public Builder nom(String nom){
            this.nom = nom;
            return this;
        }

        public Personne build(){
            return new Personne(this.prenom, this.nom);
        }

    }

}
