package com.goldteam.advisement_system;
///MANAGES THE PLANNER GUI
///


import java.util.Vector;

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
	///using userDefine class CourseInfo to create an object
	CourseInfo myCourse=new CourseInfo("Comp 333",3, "Fall");
	String chingada=myCourse.getName();
	
	Vector<CourseInfo> remainingCourses= new Vector<CourseInfo>();
	//Planner startPlanning;
	

	
	
	
	//variable
	String courses="";
	String[] semester={"Comp 380", "Math341", "Comp 333", "Comp 310"};
	String course;
	Button plannerDisplay;
	TextView displayCourses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//Vector of remaining classes
		remainingCourses.addElement(new CourseInfo("Comp 310", 3, "Spring"));
		remainingCourses.addElement(new CourseInfo("Comp 450", 3, "Spring"));
		remainingCourses.addElement(new CourseInfo("Comp 484", 3, "Spring"));
		remainingCourses.addElement(new CourseInfo("Comp 424", 3, "Spring"));
		
		remainingCourses.addElement(new CourseInfo("Math 481A", 3, "Fall"));
		remainingCourses.addElement(new CourseInfo("Comp 429",  3, "Fall"));
		remainingCourses.addElement(new CourseInfo("Comp 410",  3, "Fall"));
		remainingCourses.addElement(new CourseInfo("Comp 341",  3, "Fall"));
		
		//startPlanning=new Planner(remainingCourses);//using constructor to pass remaining courses to Planner class
		
		
		
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
				//for(int i=0; i<4; i++){
					//Display 4 courses
					//courses=courses+"\n"+ semester[i];
				//}
				//displayCourses.setText("Fall Semester\n" + courses);
				displayCourses.setText("Fall Semester\n" + chingada);
				
			}
		});
	}

}
