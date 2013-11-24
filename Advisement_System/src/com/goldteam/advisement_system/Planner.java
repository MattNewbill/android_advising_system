package com.goldteam.advisement_system;

import java.util.Vector;
import java.util.Iterator;


public class Planner {
	
	int MaxUnits=13;
	
	Vector<CourseInfo> remainingClasses=new Vector<CourseInfo>(); //holds collection of remaining classes
	
	Vector<Semester> semesters=new Vector<Semester>(); //holds a collection of semester objects.
	
	Semester creatingSemester;
	
	//get all the remaining classes 
	public Planner(Vector<CourseInfo> remainingClasses) {
		super();
		this.remainingClasses = remainingClasses;//keeps all the classes that remain to be taken.
		System.out.println(remainingClasses.get(0).name);
	}
	public Vector<Semester> automaticSemesterScheduling(){
		int baseYear=2013;
		int counter=0; 
		String term;
		
		Iterator it=remainingClasses.iterator();//to iterate through all the class in remainingClasses vector
		
		
		//populating semesters until we run out of classes
		while(it.hasNext())
		{
			//alternating between terms
			if((counter % 2)==0)//if counter is even, then it is Spring semester
				term="Spring";
			else
			    term="Fall";
			
			//incrementing the year when the term is Spring
			if(term.equals("Spring"))
				  baseYear++;
			
			//making a semester	
			creatingSemester=new Semester(String.valueOf(baseYear),term); //instantiating a semester
			counter++;//helps alternate between terms
			
			while((it.hasNext())&& (creatingSemester.getTotalUnits()<MaxUnits)){
				creatingSemester.addClasses(remainingClasses.remove(0));//adding classes to spring 2014 semester
				
			}
			semesters.add(creatingSemester); //adding a semester to my collection 

	    }
			
			return semesters;
		
	}
	

}
