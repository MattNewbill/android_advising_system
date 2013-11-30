package com.goldteam.advisement_system.test;

import java.util.ArrayList;

import org.junit.Test;

import com.goldteam.advisement_system.*;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.TextView;

public class CourseExpandableListAdapterTest extends AndroidTestCase {
	
	public CourseExpandableListAdapterTest(){
		super();
	}
	
	@Test
	public void addingRequirementsTest(){
		ArrayList<Requirement> reqs = new ArrayList<Requirement>();
		CourseExpandableListAdapter adp = new CourseExpandableListAdapter(getContext(), reqs);
		assertEquals(0, adp.getGroupCount());
		//Does not throw exception when requirement has no sub-requirements
		adp.addRequirement(new Requirement());
		assertEquals(1, adp.getGroupCount());
		//Make sure no courses were added
		assertEquals(0, adp.getChildrenCount(0));
		try{
			adp.addRequirement(null);
			fail("Exception not thrown");
		}
		catch(NullPointerException e){	
		}
		//Make sure that the null was not entered
		assertEquals(1,  adp.getGroupCount());
		ArrayList<SubRequirement> subreqs = new ArrayList<SubRequirement>();
		ArrayList<Course> courses = new ArrayList<Course>();
		Course course1 = new Course();
		courses.add(course1);
		courses.add(null);
		courses.add(new Course());
		SubRequirement subreq = new SubRequirement();
		subreq.setCourses(courses);
		subreqs.add(subreq);
		Requirement toAdd = new Requirement();
		toAdd.setSubRequirements(subreqs);
		adp.addRequirement(toAdd);
		//Was the requirement added?
		assertEquals(2, adp.getGroupCount());
		//Were 2 children (not including the null) entered?
		assertEquals(2, adp.getChildrenCount(1));
		assertEquals(course1, adp.getChild(1, 0));
		assertFalse(adp.getChild(1, 1) == null);
	}
	
	@Test
	public void viewTests(){
		//Prepare the adapter
		ArrayList<Requirement> reqs = new ArrayList<Requirement>();
		CourseExpandableListAdapter adp = new CourseExpandableListAdapter(getContext(), reqs);
		ArrayList<SubRequirement> subreqs = new ArrayList<SubRequirement>();
		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(new Course());
		SubRequirement subreq = new SubRequirement();
		subreq.setCourses(courses);
		subreqs.add(subreq);
		Requirement toAdd = new Requirement();
		toAdd.setSubRequirements(subreqs);
		adp.addRequirement(toAdd);
		//Check the group view is a TextView
		View view = adp.getGroupView(0, false, null, null);
		assertEquals(TextView.class, view.getClass());
		//Check the child view is a TextView
		view = adp.getChildView(0, 0, false, null, null);
		assertEquals(TextView.class, view.getClass());
	}
}
