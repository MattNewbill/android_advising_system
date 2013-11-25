

import java.util.ArrayList;

public class Major {
	int id;
	String name;
	int unitsRequired;
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
	public void print() {
		System.out.println("Major: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Units Required: "+getUnitsRequired());
	}
	
	
	
	

}
