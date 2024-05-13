package nst.springboot.domaci.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SecretaryHistoryId implements Serializable {
    private Long departmentId;
    private Long memberId;

    public SecretaryHistoryId() {
    }

    public SecretaryHistoryId(Long departmentId, Long memberId) {
        this.departmentId = departmentId;
        this.memberId = memberId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecretaryHistoryId)) return false;
        SecretaryHistoryId that = (SecretaryHistoryId) o;
        return Objects.equals(getDepartmentId(), that.getDepartmentId()) &&
                Objects.equals(getMemberId(), that.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentId(), getMemberId());
    }

}
