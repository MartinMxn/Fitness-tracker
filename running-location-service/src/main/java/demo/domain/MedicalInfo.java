package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
// RequiredArgsConstructor require final variables initially have values
public class MedicalInfo {

    private long bfr;

    private long fmi;

    public MedicalInfo(long bfr, long fmi) {
        this.bfr = bfr;
        this.fmi = fmi;
    }
}
