package com.goldteam.advisement_system;

import java.util.ArrayList;

/**
 * This class is used to calculate what the user should take next.
 * @author Jeffrey
 *
 */
public class RequirementCalculator {
	
	private Major major;
	private ArrayList<Course> taken;
	
	
	public RequirementCalculator(Major major, ArrayList<Course> taken){
		this.major = major;
		this.taken = taken;
	}
	
	//untested
	public ArrayList<Course> planCourses(int amount){
		ArrayList<Course> toTake = new ArrayList<Course>();
		ArrayList<Requirement> remaining = getRemainingRequirements();
		return toTake;
	}
	
	//untested
	public ArrayList<Requirement> trimUnavailableReqs(ArrayList<Requirement> reqs){
		ArrayList<Requirement> newReqs = new ArrayList<Requirement>();
		for(Requirement req : reqs){
			boolean available;
			for(SubRequirement subReq : req.subRequirements){
				for(Course course : subReq.courses) {
					
				}
			}
		}
		return newReqs;
	}
	
	public boolean isCourseTaken(Course course){
		for(Course took : taken){
			if(course.id == took.id){
				return true;
			}
		}
		return false;
	}
	
	//untested
	public ArrayList<Requirement> getRemainingRequirements() {
		ArrayList<Requirement> remaining = new ArrayList<Requirement>();
		ArrayList<Requirement> reqs = major.getRequirements();
		for(Requirement req : reqs){
			if(!isReqSatisfied(req)){
				remaining.add(req);
			}
		}
		return remaining;
	}
	
	public boolean isReqSatisfied(Requirement req){
		int unitsCompleted = 0; //The number of units completed towards the Requirement.
		for(SubRequirement subReq : req.getSubRequirements()){
			//Ignore SubRequirements for now
			for(Course course : subReq.getCourses()){
				if(course.isCompleted){
					unitsCompleted += course.unit;
				}
			}
		}
		return unitsCompleted >= req.unitRequired;
	}
}
