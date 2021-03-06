      package com.goldteam.advisement_system;

import java.util.ArrayList;

import android.app.Activity;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class CourseSelector extends Activity {

	private ExpandableListView expList;
	private CourseExpandableListAdapter adp;
	private Button submitBtn;
	
	//I want to eventually populate the expandable list like this:
			/*
			 * Requirement
			 * 	Course
			 * 	Course
			 * Requirement
			 * 	Course... etc
			 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("CourseSelector", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_selector);
		// Show the Up button in the action bar.
		setupActionBar();
		//Initialize views
		submitBtn = (Button)findViewById(R.id.button1);
		expList = (ExpandableListView)findViewById(R.id.expandableListView1);
		expList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//Get the intent passing the Major's ID.
		Intent intent = getIntent();
		int majorId = intent.getIntExtra(ClassHelper.EXTRA_MAJOR_ID, -1);
		if(majorId == -1) {
			//Do some error checking here
		}
		//Grab the major from the database
		Major major = getMajorWithRequirements(majorId);
		adp = new CourseExpandableListAdapter(this, major.getRequirements());
		expList.setAdapter(adp);
	}
	
	public void sendSelected(View v){
		ArrayList<Requirement> selected = adp.getSelected();
	}
	
	private Major getMajorWithRequirements(int id){
		//Make test case for now
		ArrayList<Requirement> reqs = new ArrayList<Requirement>();
		ArrayList<Course> tempCourses = new ArrayList<Course>();
		Course temp = new Course(0, "COMP 380", "", 3);
		tempCourses.add(temp);
		temp = new Course(1, "COMP 380/L", "", 3);
		tempCourses.add(temp);
		SubRequirement subreq = new SubRequirement(0, "COMP 380", "", tempCourses);
		ArrayList<SubRequirement> subreqs = new ArrayList<SubRequirement>();
		subreqs.add(subreq);
		Requirement req = new Requirement(0, "COMP 380", "", 4, subreqs);
		reqs.add(req);
		subreqs = new ArrayList<SubRequirement>();
		tempCourses = new ArrayList<Course>();
		tempCourses.add(new Course(1, "COMP 110", "", 3));
		tempCourses.add(new Course(1, "COMP 110/L", "", 3));
		subreq = new SubRequirement();
		subreq.setCourses(tempCourses);
		subreqs.add(subreq);
		req = new Requirement(0, "COMP 110", "", 4, subreqs);
		reqs.add(req);
		subreqs = new ArrayList<SubRequirement>();
		tempCourses = new ArrayList<Course>();
		tempCourses.add(new Course(1, "MATH 150A", "", 3));
		subreq = new SubRequirement();
		subreq.setCourses(tempCourses);
		subreqs.add(subreq);
		req = new Requirement(0, "MATH 150A", "", 4, subreqs);
		reqs.add(req);
		subreqs = new ArrayList<SubRequirement>();
		tempCourses = new ArrayList<Course>();
		tempCourses.add(new Course(1, "COMP 122", "", 3));
		tempCourses.add(new Course(1, "COMP 122/L", "", 3));
		subreq = new SubRequirement();
		subreq.setCourses(tempCourses);
		subreqs.add(subreq);
		req = new Requirement(0, "COMP 122", "", 4, subreqs);
		reqs.add(req);
		subreqs = new ArrayList<SubRequirement>();
		tempCourses = new ArrayList<Course>();
		tempCourses.add(new Course(1, "COMP 182", "", 3));
		tempCourses.add(new Course(1, "COMP 182/L", "", 3));
		subreq = new SubRequirement();
		subreq.setCourses(tempCourses);
		subreqs.add(subreq);
		req = new Requirement(0, "COMP 182", "", 4, subreqs);
		reqs.add(req);
		subreqs = new ArrayList<SubRequirement>();
		tempCourses = new ArrayList<Course>();
		tempCourses.add(new Course(1, "PHIL 230", "", 3));
		subreq = new SubRequirement();
		subreq.setCourses(tempCourses);
		subreqs.add(subreq);
		req = new Requirement(0, "PHIL 230", "", 4, subreqs);
		reqs.add(req);
		Major major = new Major(1, "Computer Science", 123, reqs);
		return major;
	}
	

	
	//Auto-generated stuff
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_selector, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
