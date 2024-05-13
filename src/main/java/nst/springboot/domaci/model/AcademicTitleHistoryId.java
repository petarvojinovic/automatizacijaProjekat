package nst.springboot.domaci.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AcademicTitleHistoryId implements Serializable {
    private Long memberId;
    private Long academicTitleId;

    public AcademicTitleHistoryId() {
    }

    public AcademicTitleHistoryId(Long memberId, Long academicTitleId) {
        this.memberId = memberId;
        this.academicTitleId = academicTitleId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getAcademicTitleId() {
        return academicTitleId;
    }

    public void setAcademicTitleId(Long academicTitleId) {
        this.academicTitleId = academicTitleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcademicTitleHistoryId)) return false;
        AcademicTitleHistoryId that = (AcademicTitleHistoryId) o;
        return Objects.equals(getMemberId(), that.getMemberId()) &&
                Objects.equals(getAcademicTitleId(), that.getAcademicTitleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId(), getAcademicTitleId());
    }
}
