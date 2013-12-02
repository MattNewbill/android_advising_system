package com.goldteam.advisement_system;

import java.util.Vector;


public class Planner {

	int MaxUnits = 11;                                          // sets the unit cap for each semester

	Vector<CourseInfo> remainingClasses = new Vector<CourseInfo>(); // holds collection of courseInfo objects

	Vector<Semester> semesters = new Vector<Semester>();        // holds a collection of semester objects.

	Semester creatingSemester;

	//initialize remainingClasses vector
	public Planner(Vector<CourseInfo> remainingClasses) {
		super();
		this.remainingClasses = remainingClasses;
													
	}
	//method systematically arranges courseInfo objects into semesters
	public Vector<Semester> automated_SemesterScheduling() {
		int baseYear = 2013;  //base year for semesters
		int counter = 0;  //use to alternate between Spring and Fall term
		String term;


		// populating semesters until we run out of classes(CourseInfo objects)
		while (!remainingClasses.isEmpty()) {
			// alternating between terms
			if ((counter % 2) == 0)// if counter is even, then it is a Spring semester
									
				term = "Spring";
			else
				term = "Fall";

			// incrementing the year when the term is Spring
			if (term.equals("Spring"))
				baseYear++;

			// making a semester
			creatingSemester = new Semester(String.valueOf(baseYear), term); 

			counter++;// alternates between terms

			// iterating through each class(courseInfo objects) in the vector of remaining classes that needs to be taken
			for (CourseInfo classItem : remainingClasses) {
				
				// check that the class is offered in the same semester that we are populating
				if (classItem.getAvailability().equals(term)) { 
					
					// check that adding the current class(CourseInfo object) will not exceed the unit cap
					if ((creatingSemester.getTotalUnits() + classItem
							.getUnits()) <= MaxUnits) { 
						// adding the class(CourseInfo object) because it is both offered in the right term and doesn't exceed the semester units maximum
						creatingSemester.addClasses(classItem);
					}
				}
			}
			//removing the classes(CourseInfo objects) that we added to a semester from our vector of remainingClasses.
			//This can't be done during the initial run due to concurrent modification exceptions
			for (CourseInfo classRemove : creatingSemester
					.getCourseThatMakeASemester()) {

				remainingClasses.remove(classRemove);
			}
			
			semesters.add(creatingSemester); // adding a populated semester to my collection of semesters
		}
		return semesters;
	}

}


