package com.goldteam.advisement_system;

import java.util.ArrayList;

public class Requirement {
	int id; //This is the id of a requirement in order check that each requirement has been met.
	String name;	//The name of the requirement
	String description;	//A brief description of the requirement
	int unitRequired;	//Units required in order to pass the requirement
	boolean isGERequirement;
	ArrayList <SubRequirement> subRequirements; //This can be subject to change, but is an array list of sub Requirement objects
	
	public Requirement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Requirement(int id,String name, String description, int unitRequired, ArrayList <SubRequirement> subRequirements) {
		super();
		this.id = id;
		this.name =name;
		this.description = description;
		this.unitRequired = unitRequired;
		this.subRequirements = subRequirements;
	}
	//These are getter and setters in which other classes and methods can use
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
	public int getUnitRequired() {
		return unitRequired;
	}
	public void setUnitRequired(int unitRequired) {
		this.unitRequired = unitRequired;
	}
	public ArrayList<SubRequirement> getSubRequirements() {
		return subRequirements;
	}
	public void setSubRequirements(ArrayList<SubRequirement> subRequirements) {
		this.subRequirements = subRequirements;
	}
	//Test method to display the information of the sub requirement
	public void print() {
		System.out.println("Requirement: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Description: "+getDescription());
		System.out.println("Units Required: "+getUnitRequired());
	}
	
	

}
