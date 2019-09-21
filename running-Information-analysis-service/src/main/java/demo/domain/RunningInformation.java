package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "RUNNING_INFORMATION")
public class RunningInformation {

    public enum HealWarningLevel {
        LOW, NORMAL, HIGH
    }

    @Id
    @GeneratedValue
    private long id;

    // Embed UserInfo
    @Embedded
    private final UserInfo userInfo;

    private String runningId;

    private double longitude;
    private double latitude;

    private double runningDistance;
    private double totalRunningTime;

    private int heartRate;
    private HealWarningLevel healWarningLevel;

    private Date timestamp = new Date();

    public RunningInformation() {
        this.userInfo = null;
    }

    // final make it immutable
    public RunningInformation(String username, String address) {
        this.userInfo = new UserInfo(username, address);
    }

    @JsonCreator
    public RunningInformation(
          @JsonProperty("runningId") String runningId,
          @JsonProperty("longitude") String longitude,
          @JsonProperty("latitude") String latitude,
          @JsonProperty("runningDistance") String runningDistance,
          @JsonProperty("totalRunningTime") String totalRunningTime,
          @JsonProperty("heartRate") String heartRate,
          @JsonProperty("timestamp") String timestamp,
          @JsonProperty("userInfo") UserInfo userInfo
    ) {
        this.runningId = runningId;
        this.longitude = Double.parseDouble(longitude);
        this.latitude = Double.parseDouble(latitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        // using the current time as timestamp
        this.timestamp = new Date();
        this.userInfo = userInfo;

        this.heartRate = _generateRandomHeartRate(60, 200);

        if (this.heartRate > 120) {
            this.healWarningLevel = HealWarningLevel.HIGH;
        } else if (this.heartRate > 75) {
            this.healWarningLevel = HealWarningLevel.NORMAL;
        } else if (this.heartRate > 60) {
            this.healWarningLevel = HealWarningLevel.LOW;
        } else {
            // DANGER new enum
            // or left blank do nothing
            // exception
            // print warning
            System.out.println("Error random heart rate: " + this.heartRate);
        }
    }



    private int _generateRandomHeartRate(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max - min + 1);
    }
}
