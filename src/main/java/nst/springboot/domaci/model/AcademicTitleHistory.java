package nst.springboot.domaci.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {

    @EmbeddedId
    private AcademicTitleHistoryId id;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @MapsId("academicTitleId")
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    private LocalDate startDate;

    @Future(message = "Datum zavrsetka mora biti u buducnosti!")
    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;

    public AcademicTitleHistory() {
    }

    public AcademicTitleHistory(AcademicTitleHistoryId id, Member member, AcademicTitle academic_title, LocalDate startDate, LocalDate endDate, ScientificField scientificField) {
        this.id = id;
        this.member = member;
        this.academicTitle = academic_title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scientificField = scientificField;
    }

    public AcademicTitleHistoryId getId() {
        return id;
    }

    public void setId(AcademicTitleHistoryId id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
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

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }
}