

import java.util.ArrayList;

public class SubRequirement {
	int id;
	String name;
	String description;
	ArrayList <Course> courses;
	
	public SubRequirement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubRequirement(int id,String name, String description, ArrayList<Course> courses) {
		super();
		this.name =name;
		this.id = id;
		this.description = description;
		this.courses = courses;
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

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	public void print() {
		System.out.println("SubRequirement: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Description: "+getDescription());
	}

}
