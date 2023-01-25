package be.bstorm.akimts.mvc.models.form;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DemoValidationForm {

    @Min(0)
    @Max(15)
    @Positive
    @Negative
    @PositiveOrZero
    @NegativeOrZero
    private int entier;

    @NotBlank
    @NotNull
    @Null
    @NotEmpty
    @Size(min = 0, max = 10)
    @Pattern(regexp = "[0-9]+")
    private String chaine;

    @NotNull
    @Null
    private Object objet;

    @NotNull
    @Null
    @Future
    @FutureOrPresent
    @Past
    @PastOrPresent
    private LocalDateTime dateTime;

    @NotNull
    @Null
    @NotEmpty
    @Size(min = 0, max = 10)
    private List<Object> list;

}
