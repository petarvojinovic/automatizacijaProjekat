package nst.springboot.domaci.dto;

import jakarta.validation.constraints.NotNull;

public class EducationTitleDto {
    private Long id;

    @NotNull(message = "Ime je obavezno polje!")
    private String name;

    public EducationTitleDto() {
    }

    public EducationTitleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
