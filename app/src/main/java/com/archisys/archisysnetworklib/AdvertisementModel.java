package com.archisys.archisysnetworklib;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AdvertisementModel {

	@JsonProperty("StartDate")
	private String startDate;

	@JsonProperty("DisplayLocation")
	private String displayLocation;

	@JsonProperty("IsDeleted")
	private boolean isDeleted;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("IsActive")
	private boolean isActive;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Id")
	private String id;

	@JsonProperty("CreatedOn")
	private String createdOn;

	@JsonProperty("Image")
	private String image;

	@JsonProperty("EndDate")
	private String endDate;

	public AdvertisementModel(){
		this.displayLocation="";
		this.image="";
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	public void setDisplayLocation(String displayLocation){
		this.displayLocation = displayLocation;
	}

	public String getDisplayLocation(){
		return displayLocation;
	}

	public void setIsDeleted(boolean isDeleted){
		this.isDeleted = isDeleted;
	}

	public boolean isIsDeleted(){
		return isDeleted;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCreatedOn(String createdOn){
		this.createdOn = createdOn;
	}

	public String getCreatedOn(){
		return createdOn;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image.replaceAll("\\\\","/");
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}
}