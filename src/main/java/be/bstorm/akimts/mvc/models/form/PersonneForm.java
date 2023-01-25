package be.bstorm.akimts.mvc.models.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonneForm {

    @NotBlank
    @Size(min = 5, max = 10)
    private String nom;
    @NotBlank
    @Size(min = 5, max = 10)
    private String prenom;
    @Past
    private LocalDate dateNaiss;
}
