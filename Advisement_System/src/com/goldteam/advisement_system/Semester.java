package com.goldteam.advisement_system;
import java.util.Vector;
//semester arranges classes
public class Semester {
	String year;
	String term;
	String name;
	int totalUnits=0;
	
	//a semester has a list of courses
	Vector <CourseInfo> courseThatMakeASemester=new Vector<CourseInfo>();

	public Semester(String year, String term) {
		super();
		this.year = year;
		this.term = term;
		this.name = term + " "+year;
		
	}
	
	public void addClasses(CourseInfo addClass){
		courseThatMakeASemester.add(addClass); //add a class
		
		totalUnits+=addClass.units; //add number of units of the added course to the total number of units
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public int getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(int totalUnits) {
		this.totalUnits = totalUnits;
	}

	public Vector<CourseInfo> getCourseThatMakeASemester() {
		return courseThatMakeASemester;
	}

	public void setCourseThatMakeASemester(
			Vector<CourseInfo> courseThatMakeASemester) {
		this.courseThatMakeASemester = courseThatMakeASemester;
	}
	
	
	
	
	
	
	

}