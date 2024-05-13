package nst.springboot.domaci.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_director_history")
public class DirectorHistory {
    @EmbeddedId
    private DirectorHistoryId id;

    @ManyToOne
    @MapsId("departmentId")
    @JoinColumn(name = "department_Id")
    private Department department;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    private LocalDate startDate;

    @Future(message = "Datum zavrsetka mora biti u buducnosti!")
    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    private LocalDate endDate;

    public DirectorHistory() {
    }

    public DirectorHistory(DirectorHistoryId id, Department department, Member member, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.department = department;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DirectorHistoryId getId() {
        return id;
    }

    public void setId(DirectorHistoryId id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
