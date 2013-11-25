

import java.util.ArrayList;

public class Requirement {
	int id; //This is the id of a requirement in order check that each requirement has been met.
	String name;
	String description;
	int unitRequired;
	boolean isGERequirement;
	ArrayList <SubRequirement> subRequirements;
	
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
	public void print() {
		System.out.println("Requirement: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Description: "+getDescription());
		System.out.println("Units Required: "+getUnitRequired());
	}
	
	

}
