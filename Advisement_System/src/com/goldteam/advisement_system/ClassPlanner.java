package com.goldteam.advisement_system;
///MANAGES THE PLANNER GUI
///


import java.util.Vector;
import java.util.Iterator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

import com.goldteam.advisement_system.R;
import com.goldteam.advisement_system.R.id;
import com.goldteam.advisement_system.R.layout;



public class ClassPlanner extends Activity{
	
	
	//content variables
	Vector<CourseInfo> remainingCourses= new Vector<CourseInfo>();
	Planner startPlanning;
	Vector<Semester> mySemesters=new Vector<Semester>();

	//GUI variables
	Button plannerDisplay;
	TextView displayCourses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		//populating vector of remaining classes
		remainingCourses.addElement(new CourseInfo("Comp 310", 3, "Spring"));
		/*remainingCourses.addElement(new CourseInfo("Comp 450", 3, "Fall"));
		remainingCourses.addElement(new CourseInfo("Comp 484", 3, "Spring"));
		remainingCourses.addElement(new CourseInfo("Comp 424", 3, "Spring"));
		
		remainingCourses.addElement(new CourseInfo("Math 481A", 3, "Fall"));
		remainingCourses.addElement(new CourseInfo("Comp 429",  3, "Spring"));
		remainingCourses.addElement(new CourseInfo("Comp 410",  3, "Fall"));
		remainingCourses.addElement(new CourseInfo("Comp 341",  2, "Spring"));*/
		
		startPlanning=new Planner(remainingCourses);//using constructor to pass remaining courses to Planner class
		
		
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_planner);
		
		//set our course variable to match XML variables
		//course=0;
		plannerDisplay=(Button)findViewById(R.id.buttonPlannerDisplay);
		displayCourses=(TextView)findViewById(R.id.textViewPlannerDisplay);
		
		//setting up the click listener
		plannerDisplay.setOnClickListener(new View.OnClickListener() {
			
			//when button is clicked--it will be executed
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//change  the display view
			
				String results="";
				String graduation="";
				mySemesters=startPlanning.automated_SemesterScheduling();//store the collection of semester return by automatic...
				
				if(mySemesters.isEmpty()){
					displayCourses.setText("Your have no classes to take.\n Perhaps you should start with the Class helper\n");//display semester to the app.
				}
				else{
				//iterate through each semesters
				for (Semester eachSemester : mySemesters){
					
					
					results+="\n"+eachSemester.getName()+"\n"; //adding the semester information to our display variable
			
					
				    //iterating through the courses that make up the current semester
					for(CourseInfo myCourse: eachSemester.getCourseThatMakeASemester()){
						
						results+= myCourse.getName()+"\n";//adding the classes that make up the current semester to the display variable
						
						
					}
					graduation=eachSemester.getName();	
					
			}
				displayCourses.setText("Your projected graduation: "+ graduation +"\n"+results+"\n");//display semester to the app.
			}
			}
				
		});
	}

}
