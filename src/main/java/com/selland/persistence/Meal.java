package com.selland.persistence;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
@DynamoDBDocument
public class Meal {
    
	private String type;
	private String uniqueId;
	private Date timestamp;
	private String details;
	private String size;
	private Float carbs;
	private String grade;
	private String rank;
	
	public Meal() {}
	
	public Meal(String type, String uniqueId, Date timestamp, String details, String size, Float carbs) {
		this.type = type;
		this.uniqueId = uniqueId;
		this.timestamp = timestamp;
		this.details = details;
		this.size = size;
		this.carbs = carbs;
	}
	
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	
	public String getUniqueId() { return uniqueId; }
	public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }
	
	public Date getTimestamp() { return timestamp; }
	public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
	
	public String getDetails() { return details; }
	public void setDetails(String details) { this.details = details; }
	
	public String getSize() { return size; }
	public void setSize(String size) { this.size = size; }
	
	public Float getCarbs() { return carbs; }
	public void setCarbs(Float carbs) { this.carbs = carbs; }	
	
	public String getGrade() { return grade; }
	public void setGrade(String grade) { this.grade = grade; }
	
	public String getRank() { return rank; }
	public void setRank(String rank) { this.rank = rank; }
	
}