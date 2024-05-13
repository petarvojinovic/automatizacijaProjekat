package nst.springboot.domaci.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ime je obavezno polje")
    @Size(min = 2, max = 10, message = "Broj znakova je od 2 do 10")
    private String name;

    private int espb;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;

    public Subject() {
    }

    public Subject(Long id, String name, int espb, Department department) {
        this.id = id;
        this.name = name;
        this.espb = espb;
        this.department = department;
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

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return espb == subject.espb && Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(department, subject.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, espb, department);
    }

}
