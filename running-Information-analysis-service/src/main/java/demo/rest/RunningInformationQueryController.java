package demo.rest;

import demo.domain.RunningInformation;
import demo.service.RunningInformationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInformationQueryController {

    private final String kDefaultPage = "0";
    private final String kDefaultPageSize = "30";

    // better practice
    private final String krunningId = "runningId";

    private final RunningInformationService runningInformationService;

    @Autowired
    public RunningInformationQueryController(RunningInformationService runningInformationService) {
        this.runningInformationService = runningInformationService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList) {
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInformationService.deleteALl();
    }

    @RequestMapping(value = "/heartRate/{heartRate}", method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRate(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = kDefaultPageSize ) Integer size) {

        return runningInformationService.findByHeartRate(heartRate, PageRequest.of(page, size));
    }

    @RequestMapping(value = "/heartRateGreaterThan/{heartRate}", method = RequestMethod.GET)
    public ResponseEntity<?> findByHeartRateGreaterThan(
        @PathVariable Integer heartRate,
        @RequestParam(name = "page", required = false, defaultValue = kDefaultPage) Integer page,
        @RequestParam(name = "size", required = false, defaultValue = kDefaultPageSize ) Integer size) {

        Page<RunningInformation> rawResult = runningInformationService.findByHeartRateGreaterThan(heartRate, PageRequest.of(page, size));
        List<RunningInformation> content = rawResult.getContent();

        // Transform RunningInformation to customized JSON objects
        List<JSONObject> results = new ArrayList<JSONObject>();
        for (RunningInformation item : content) {
            JSONObject info = new JSONObject();
            info.put(krunningId, item.getRunningId());
            info.put("totalRunningTime", item.getTotalRunningTime());
            info.put("heartRate", item.getHeartRate());
            info.put("userId", item.getId());
            info.put("userName", item.getUserInfo().getUsername());
            info.put("userAddress", item.getUserInfo().getAddress());
            info.put("healthWarningLevel", item.getHealWarningLevel());

            results.add(info);
        }
        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);
    }
}
