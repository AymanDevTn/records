package mdn.ayman.MarineRecord.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mdn.ayman.MarineRecord.model.MarineRecord;

public interface MarineRecordRepository extends JpaRepository<MarineRecord, Long> {

	 
	 @Query("SELECT r FROM MarineRecord r WHERE " +
	           "r.boatName LIKE %?1% OR " +
	           "r.boatIMO LIKE %?1% OR " +
	           "r.sourcePort LIKE %?1% OR " +
	           "r.corpPenalty LIKE %?1% OR " +
	           "r.boatCommander LIKE %?1% OR " +
	           "r.penaltyType LIKE %?1% OR " +
	           "r.legislativeReference LIKE %?1%")
	    List<MarineRecord> findByNameContainingIgnoreCase(String keyword);

	 
	 
	 @Query("SELECT EXTRACT(MONTH FROM r.date), COUNT(r) FROM MarineRecord r WHERE EXTRACT(YEAR FROM r.date) = :year GROUP BY EXTRACT(MONTH FROM r.date)")
	    List<Object[]> countPenaltiesByMonth(@Param("year") int year);

	    @Query("SELECT EXTRACT(YEAR FROM r.date), COUNT(r) FROM MarineRecord r GROUP BY EXTRACT(YEAR FROM r.date)")
	    List<Object[]> countPenaltiesByYear();

	    @Query("SELECT r.boatName, COUNT(r) FROM MarineRecord r GROUP BY r.boatName ORDER BY COUNT(r) DESC")
	    List<Object[]> findMaxPenaltiesByBoat();

	    @Query("SELECT r.sourcePort, COUNT(r) FROM MarineRecord r GROUP BY r.sourcePort ORDER BY COUNT(r) DESC")
	    List<Object[]> findMaxPenaltiesByPort();
	 
	 
	 
	 
}

 