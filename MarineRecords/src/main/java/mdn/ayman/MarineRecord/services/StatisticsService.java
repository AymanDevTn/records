package mdn.ayman.MarineRecord.services;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import mdn.ayman.MarineRecord.model.MarineRecord;
import mdn.ayman.MarineRecord.repositories.MarineRecordRepository;
import mdn.ayman.MarineRecord.repositories.UserRepository;


@Service
public class StatisticsService {

    @Autowired
    private MarineRecordRepository marineRecordRepository;

    public Map<String, Integer> getPenaltiesByMonth(int year) {
        List<Object[]> results = marineRecordRepository.countPenaltiesByMonth(year);
        return convertResultsToMap(results);
    }

    public Map<String, Integer> getPenaltiesByYear() {
        List<Object[]> results = marineRecordRepository.countPenaltiesByYear();
        return convertResultsToMap(results);
    }

    public Map<String, Integer> getMaxPenaltiesByBoat() {
        List<Object[]> results = marineRecordRepository.findMaxPenaltiesByBoat();
        return convertResultsToMap(results);
    }

    public Map<String, Integer> getMaxPenaltiesByPort() {
        List<Object[]> results = marineRecordRepository.findMaxPenaltiesByPort();
        return convertResultsToMap(results);
    }

    private Map<String, Integer> convertResultsToMap(List<Object[]> results) {
        return results.stream().collect(Collectors.toMap(
            result -> String.valueOf(result[0]),
            result -> ((Number) result[1]).intValue()
        ));
    }
}

