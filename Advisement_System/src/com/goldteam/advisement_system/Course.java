package com.goldteam.advisement_system;

public class Course {
	int id;		// unique id for each course
	String name;	//name of course
	String description;	// A brief description of the course
	int unit;	//number of units this course is
	boolean isCompleted;	//boolean value to check if course has been passed
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	//Constructor
	public Course(int id, String name, String description, int unit) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unit = unit;
		isCompleted = false;
	}
	// from here down are getters and setters so other classes and method use
	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	//test method to display a information on the course
	public void print() {
		System.out.println("Course: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Description: "+getDescription());
		System.out.println("Units: "+getUnit());
		System.out.println("Passed: "+isCompleted());
	}

}
