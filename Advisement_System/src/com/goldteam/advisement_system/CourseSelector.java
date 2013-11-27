package com.goldteam.advisement_system;

import java.util.ArrayList;

import android.app.Activity;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class CourseSelector extends Activity {

	private ExpandableListView expList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_selector);
		// Show the Up button in the action bar.
		setupActionBar();
		//Initialize global references
		expList = (ExpandableListView)findViewById(R.id.expandableListView1);
		//Get the intent passing the Major's ID.
		Intent intent = getIntent();
		int majorId = intent.getIntExtra(ClassHelper.EXTRA_MAJOR_ID, -1);
		if(majorId == -1) {
			//Do some error checking here
		}
		Major major = getMajorWithRequirements(majorId);
		populateListView(major);
	}
	
	private Major getMajorWithRequirements(int id){
		//Make test case for now
		ArrayList<Course> tempCourses = new ArrayList<Course>();
		Course temp = new Course(0, "COMP 380", "", 3);
		tempCourses.add(temp);
		temp = new Course(1, "COMP 380/L", "", 3);
		tempCourses.add(temp);
		SubRequirement subreq = new SubRequirement(0, "COMP 380", "", tempCourses);
		ArrayList<SubRequirement> subreqs = new ArrayList<SubRequirement>();
		subreqs.add(subreq);
		Requirement req = new Requirement(0, "COMP 380", "", 4, subreqs);
		ArrayList<Requirement> reqs = new ArrayList<Requirement>();
		reqs.add(req);
		Major major = new Major(1, "Computer Science", 123, reqs);
		return major;
	}
	
	private void populateListView(Major major){
		//I want to eventually populate the expandable list like this:
		/*
		 * Requirement
		 * 	Course
		 * 	Course
		 * Requirement
		 * 	Course... etc
		 */
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
