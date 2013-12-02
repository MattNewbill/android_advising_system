package com.goldteam.advisement_system;

import java.util.ArrayList;

public class Major {
	int id;	//a unique id for each major
	String name;  //name of the major
	int unitsRequired;	//units required for the major
	ArrayList <Requirement> Requirements;
	
	public Major() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Major(int id, String major, int units, ArrayList<Requirement> requirements) {
		super();
		this.id = id;
		this.name = major;
		this.unitsRequired = units;
		this.Requirements = requirements;	
	}
//from here down are getters and setters so other classes or methods can call these variables
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

	public int getUnitsRequired() {
		return unitsRequired;
	}

	public void setUnitsRequired(int unitsRequired) {
		this.unitsRequired = unitsRequired;
	}

	public ArrayList<Requirement> getRequirements() {
		return Requirements;
	}

	public void setRequirements(ArrayList<Requirement> requirements) {
		Requirements = requirements;
	}
	//test is a test method in order to display a major's information
	public void print() {
		System.out.println("Major: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Units Required: "+getUnitsRequired());
	}
	
	@Override
	public String toString(){
		//To work with the ArrayAdapter.
		return this.name;
	}

}
