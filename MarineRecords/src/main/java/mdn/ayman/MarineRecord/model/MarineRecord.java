package mdn.ayman.MarineRecord.model;
  
import java.sql.Time;
import java.util.Date;


import javax.persistence.*;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "MarineRecord")
public class MarineRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date date;

    @Column(name = "time")
  //  @DateTimeFormat(pattern = "HH:mm")
    private String time;
   
    
    
    
    @Column(name = "boatName")
    private String boatName;
    @Column(name = "boatIMO")
    private String boatIMO;
    @Column(name = "sourcePort")
    private String sourcePort;
    @Column(name = "corpPenalty")
    private String corpPenalty;
    @Column(name = "boatCommander")
    private String boatCommander;
    @Column(name = "penaltyType")
    private String penaltyType;
    @Column(name = "legislativeReference")
    private String legislativeReference;
    
    
	public MarineRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MarineRecord(Long id, Date date, String time, String boatName, String boatIMO, String sourcePort,
			String corpPenalty, String boatCommander, String penaltyType, String legislativeReference) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.boatName = boatName;
		this.boatIMO = boatIMO;
		this.sourcePort = sourcePort;
		this.corpPenalty = corpPenalty;
		this.boatCommander = boatCommander;
		this.penaltyType = penaltyType;
		this.legislativeReference = legislativeReference;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBoatName() {
		return boatName;
	}
	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}
	public String getBoatIMO() {
		return boatIMO;
	}
	public void setBoatIMO(String boatIMO) {
		this.boatIMO = boatIMO;
	}
	public String getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getCorpPenalty() {
		return corpPenalty;
	}
	public void setCorpPenalty(String corpPenalty) {
		this.corpPenalty = corpPenalty;
	}
	public String getBoatCommander() {
		return boatCommander;
	}
	public void setBoatCommander(String boatCommander) {
		this.boatCommander = boatCommander;
	}
	public String getPenaltyType() {
		return penaltyType;
	}
	public void setPenaltyType(String penaltyType) {
		this.penaltyType = penaltyType;
	}
	public String getLegislativeReference() {
		return legislativeReference;
	}
	public void setLegislativeReference(String legislativeReference) {
		this.legislativeReference = legislativeReference;
	}

    
}
