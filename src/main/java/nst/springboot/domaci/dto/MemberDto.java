package nst.springboot.domaci.dto;

import jakarta.validation.constraints.NotNull;

public class MemberDto {
    private Long id;

    @NotNull(message = "Ime je obavezno polje!")
    private String firstName;

    @NotNull(message = "Prezime je obavezno polje!")
    private String lastName;

    @NotNull(message = "Akademsko zvanje je obavezno polje!")
    private AcademicTitleDto academicTitleDto;

    @NotNull(message = "Stepen strucne spreme je obavezno polje!")
    private EducationTitleDto educationTitleDto;

    @NotNull(message = "Uza naucna oblast je obavezno polje!")
    private ScientificFieldDto scientificFieldDto;

    @NotNull(message = "Katedra je obavezno polje!")
    private DepartmentDto departmentDto;

    public MemberDto() {
    }

    public MemberDto(Long id, String firstName, String lastName, AcademicTitleDto academicTitleDto, EducationTitleDto educationTitleDto, ScientificFieldDto scientificFieldDto, DepartmentDto departmentDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicTitleDto = academicTitleDto;
        this.educationTitleDto = educationTitleDto;
        this.scientificFieldDto = scientificFieldDto;
        this.departmentDto = departmentDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AcademicTitleDto getAcademicTitleDto() {
        return academicTitleDto;
    }

    public void setAcademicTitleDto(AcademicTitleDto academicTitleDto) {
        this.academicTitleDto = academicTitleDto;
    }

    public EducationTitleDto getEducationTitleDto() {
        return educationTitleDto;
    }

    public void setEducationTitleDto(EducationTitleDto educationTitleDto) {
        this.educationTitleDto = educationTitleDto;
    }

    public ScientificFieldDto getScientificFieldDto() {
        return scientificFieldDto;
    }

    public void setScientificFieldDto(ScientificFieldDto scientificFieldDto) {
        this.scientificFieldDto = scientificFieldDto;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }
}
