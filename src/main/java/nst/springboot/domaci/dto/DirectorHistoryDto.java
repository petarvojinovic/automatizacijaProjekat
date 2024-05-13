package nst.springboot.domaci.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;

public class DirectorHistoryDto implements Serializable {
    @NotNull(message = "Katedra je obavezno polje!")
    private DepartmentDto departmentDto;

    @NotNull(message = "Clan je obavezno polje!")
    private MemberDto memberDto;

    @PastOrPresent(message = "Datum pocetka mora biti u proslosti ili sadasnjosti!")
    @NotNull(message = "Datum pocetka je obavezno polje!")
    private LocalDate startDate;

    @Future(message = "Datum zavrsetka mora biti u buducnosti!")
    @NotNull(message = "Datum zavrsetka je obavezno polje!")
    private LocalDate endDate;

    public DirectorHistoryDto() {
    }

    public DirectorHistoryDto(DepartmentDto departmentDto, MemberDto memberDto, LocalDate startDate, LocalDate endDate) {
        this.departmentDto = departmentDto;
        this.memberDto = memberDto;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
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
