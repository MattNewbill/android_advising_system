package com.goldteam.advisement_system.test;

import java.util.ArrayList;

import org.junit.Test;

import com.goldteam.advisement_system.*;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class CourseExpandableListAdapterTest extends AndroidTestCase {
	
	public CourseExpandableListAdapterTest(){
		super();
	}
	
	@Test
	public void testConstructor(){
		try{
			CourseExpandableListAdapter adp = new CourseExpandableListAdapter(getContext(), null);
			fail("Exception not thrown");
		}
		catch(NullPointerException e){
		}
		try{
			CourseExpandableListAdapter adp = new CourseExpandableListAdapter(null, new ArrayList<Requirement>());
			fail("Exception not thrown");
		}
		catch(NullPointerException e){
		}
		CourseExpandableListAdapter adp = new CourseExpandableListAdapter(getContext(), new ArrayList<Requirement>());
		assertEquals(0, adp.getGroupCount());
		adp = new CourseExpandableListAdapter(getContext(), makeRequirements());
		assertEquals(1, adp.getGroupCount());
		assertEquals(2, adp.getChildrenCount(0));
	}
	
	@Test
	public void testAddingRequirements(){
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
		//Enter requirements with children
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
		//Is course1 the first child?
		assertEquals(course1, adp.getChild(1, 0));
		//Was the second child the null?
		assertFalse(adp.getChild(1, 1) == null);
		//Did we add the Requirement to group?
		assertEquals(toAdd, adp.getGroup(1));
	}
	
	@Test
	public void testViews(){
		//Prepare the adapter
		ArrayList<Requirement> reqs = makeRequirements();
		CourseExpandableListAdapter adp = new CourseExpandableListAdapter(getContext(), reqs);
		ArrayList<SubRequirement> subreqs = new ArrayList<SubRequirement>();
		//Check the group view is a TextView
		View view = adp.getGroupView(0, false, null, null);
		assertEquals(TextView.class, view.getClass());
		String text = ((TextView) view).getText().toString();
		assertTrue(text.equals("COMP 110"));
		//Check the child view is a TextView
		view = adp.getChildView(0, 0, false, null, null);
		assertEquals(CheckedTextView.class, view.getClass());
	}
	
	/**
	 * Tests to see if selecting the CheckedTextViews returns the right requirements and courses.
	 */
	@Test
	public void testSelections(){
		ArrayList<Requirement> reqs = makeRequirements();
		CourseExpandableListAdapter adp = new CourseExpandableListAdapter(getContext(), reqs);
		ArrayList<Requirement> selected = adp.getSelected();
		assertEquals(1, selected.size());
		assertEquals(0, selected.get(0).getSubRequirements().get(0).getCourses().size());
		CheckedTextView view = (CheckedTextView)adp.getChildView(0, 0, false, null, null);
		assertEquals(view.isChecked(), false);
		view.setChecked(true);
		selected = adp.getSelected();
		assertEquals(1, selected.size());
		Requirement selectedReq = selected.get(0);
		assertEquals(1, selectedReq.getSubRequirements().get(0).getCourses().size());
		Course selectedCourse = selectedReq.getSubRequirements().get(0).getCourses().get(0);
		assertEquals("COMP 110", selectedCourse.getName());
	}
	
	
	private ArrayList<Requirement> makeRequirements(){
		ArrayList<Requirement> reqs = new ArrayList<Requirement>();
		Requirement toAdd = makeRequirement(1, "COMP 110");
		reqs.add(toAdd);
		return reqs;
	}
	
	private Requirement makeRequirement(int id, String name){
		Requirement req = new Requirement(id, name, "", 4, null);
		ArrayList<SubRequirement> subreqs = new ArrayList<SubRequirement>();
		ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(new Course(1, "COMP 110", "", 3));
		courses.add(new Course(1, "COMP 110/L", "", 1));
		SubRequirement subreq = new SubRequirement();
		subreq.setCourses(courses);
		subreqs.add(subreq);
		req.setSubRequirements(subreqs);
		return req;
	}
}
