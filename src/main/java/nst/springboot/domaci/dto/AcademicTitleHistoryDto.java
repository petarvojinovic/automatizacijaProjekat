package nst.springboot.domaci.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;

public class AcademicTitleHistoryDto implements Serializable {
    @NotNull(message = "Clan je obavezno polje!")
    private MemberDto memberDto;

    @NotNull(message = "Akademsko zvanje je obavezno polje!")
    private AcademicTitleDto academicTitleDto;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    private LocalDate startDate;

    @Future(message = "Datum zavrsetka mora biti u buducnosti!")
    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    private LocalDate endDate;

    @NotNull(message = "Uza naucna oblast je obavezno polje!")
    private ScientificFieldDto scientificFieldDto;

    public AcademicTitleHistoryDto() {
    }

    public AcademicTitleHistoryDto(MemberDto memberDto, AcademicTitleDto academicTitleDto, LocalDate startDate, LocalDate endDate, ScientificFieldDto scientificFieldDto) {
        this.memberDto = memberDto;
        this.academicTitleDto = academicTitleDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scientificFieldDto = scientificFieldDto;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public AcademicTitleDto getAcademicTitleDto() {
        return academicTitleDto;
    }

    public void setAcademicTitleDto(AcademicTitleDto academicTitleDto) {
        this.academicTitleDto = academicTitleDto;
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

    public ScientificFieldDto getScientificFieldDto() {
        return scientificFieldDto;
    }

    public void setScientificFieldDto(ScientificFieldDto scientificFieldDto) {
        this.scientificFieldDto = scientificFieldDto;
    }
}
