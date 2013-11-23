package com.goldteam.advisement_system;

public class CourseInfo {
	String name;
	int units;
	String availability;
	
	public CourseInfo(String name, int units, String availability) {
		super();
		this.name = name;
		this.units = units;
		this.availability = availability;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
	
    

}
