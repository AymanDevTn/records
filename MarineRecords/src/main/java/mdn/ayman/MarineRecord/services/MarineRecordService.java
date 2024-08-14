package mdn.ayman.MarineRecord.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import mdn.ayman.MarineRecord.model.MarineRecord;
import mdn.ayman.MarineRecord.repositories.MarineRecordRepository;
import mdn.ayman.MarineRecord.repositories.UserRepository;

@Service
public class MarineRecordService {
    @Autowired
    private MarineRecordRepository repository;

    public List<MarineRecord> findAll() {
        return repository.findAll();
    }

    public MarineRecord findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public MarineRecord save(MarineRecord record) {
        return repository.save(record);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

	   
		public List<MarineRecord> searchItemsByName(String query) {
			return repository.findByNameContainingIgnoreCase(query);
		}
}
