package demo;

import demo.domain.UnitInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// command + option + l -> format
// auto import -> option + enter
@SpringBootApplication
public class RunningLocationServiceApplication {

    public static void main(String[] args) {
        UnitInfo unitInfo = new UnitInfo();
        unitInfo.setBandMake("FitBit");
        SpringApplication.run(RunningLocationServiceApplication.class, args);
    }
}
