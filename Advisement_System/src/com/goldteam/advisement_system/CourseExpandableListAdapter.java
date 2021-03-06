package com.goldteam.advisement_system;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
/**
 * This class is used to put the requirements and courses into the expandable list.
 * The expandable list looks like this:
 * <p>
 * Requirement<br>
 * &nbsp;Course<br>
 * &nbsp;Course<br>
 * Requirement<br>
 * &nbsp;Course<br>
 * etc.
 * <p>
 * The courses are initially hidden until the requirement is clicked which exposes
 * the list of requirements.
 * 
 * @author Jeffrey Limbacher
 */
public class CourseExpandableListAdapter extends BaseExpandableListAdapter {
	
	
	//All the requirements to be in the list
	private ArrayList<Requirement> reqs;
	//The position of the ArrayList corresponds to the requirements that the arraylist is associated to
	private ArrayList<ArrayList<Course>> courses;
	//Ties the courses to the CheckedTextView to get whether they were selected or not.
	private HashMap<Course, CheckedTextView> coursesSelected;
	//Used to expand the Views in the List.
	private LayoutInflater inflater;
	
	public CourseExpandableListAdapter(Context context, ArrayList<Requirement> requirements) {
		this.inflater = LayoutInflater.from(context);
		this.reqs = new ArrayList<Requirement>();
		courses = new ArrayList<ArrayList<Course>>();
		coursesSelected = new HashMap<Course, CheckedTextView>();
		//Populate courses (the children of the requirements)
		//This implementation assumes unique courses for now.
		for(int i = 0; i < requirements.size(); i++){
			Requirement req = requirements.get(i);
			//Add requirements
			this.addRequirement(req);
		}
	}
	
	/**
	 * Adds a requirement to the list. The requirement should have all its subrequirements already.
	 * However, the method does not check if any subrequirements exist.
	 * <p>
	 * Will throw a NullPointerException if the Requirement is null but it will not throw an exception
	 * if any of the fields of the Requirement are null. Ignores nulls in the courses.
	 * 
	 * @param req A requirement which contains all its required courses.
	 */
	public void addRequirement(Requirement req) {
		int reqPosition = reqs.size();
		//Keep the number of ArrayList<Course>s in sync with Requirements.
		courses.add(new ArrayList<Course>());
		//Fail here if req is null
		if(req.getSubRequirements() != null){
			for(SubRequirement subReq : req.getSubRequirements()) {
				//The sub-requirements each contain different courses
				//Insert all the courses into the expandable lists under the requirements.
				ArrayList<Course> subReqCourses = subReq.getCourses();
				for(Course course : subReqCourses) {
					//Ignore null values.
					if(course != null) {
						courses.get(reqPosition).add(course);
					}
				}
			}
		}
		//Only add the requirement if the above was successful
		reqs.add(req);
	}
	
	/**
	 * Adds a course to the specified requirement.
	 * @param reqPos The position of the requirement.
	 * @param course The course to add.
	 */
	public void addCourse(int reqPos, Course course){
		ArrayList<Course> reqCourses = courses.get(reqPos);
		reqCourses.add(course);
	}
	
	/**
	 * Sets the LayoutInflater.
	 * @param mInflater The inflater to be set to.
	 */
	public void setInflater(LayoutInflater mInflater) {
		this.inflater = mInflater;
	}

	@Override
	/**
	 * Gets child for a particular Requirement. Will throw an OutOfBoundsException.
	 * 
	 * @param groupPosition The position of the Requirement.
	 * @param childPosition The position of the Course.
	 */
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<Course> reqCourses = courses.get(groupPosition);
		Course gotCourse = reqCourses.get(childPosition);
		return gotCourse;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		//Retrieve the child requested
		ArrayList<Course> tempChild = courses.get(groupPosition);
		Course course = tempChild.get(childPosition);
		//Put the children in textViews
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.course_selector_checkedtextview, null);
		}
		String courseName = course.getName();
		((CheckedTextView) convertView).setText(courseName);
		//Set as tag for click callback to retrieve the object
		convertView.setTag(course);
		//Put the convertView in the HashMap.
		this.coursesSelected.put(course, (CheckedTextView)convertView);
		convertView.setOnClickListener(new OnClickListener() {
		   @Override
		//Check or uncheck the box.
		public void onClick(View v) {
			   CheckedTextView checkView = (CheckedTextView) v;
			   Course course = (Course) v.getTag();
			   checkView.setChecked(!checkView.isChecked());
			   Log.d("CourseExpandableListAdapter", course.toString());
		   }
		});
		return convertView;
	}
	
	public ArrayList<Requirement> getSelected(){
		ArrayList<Requirement> selected = new ArrayList<Requirement>();
		int noOfReqs = reqs.size();
		//Scan through each requirement and find which courses were selected
		for(int i = 0; i < noOfReqs; i++) {
			//The current requirement from the field
			Requirement currReq = reqs.get(i);
			//This contains the courses that were selected
			Requirement newReq = currReq.copy();
			//This will hold our single SubRequirement which holds all the courses.
			//I'm not separating them out by SubRequirement yet. Too messy.
			ArrayList<SubRequirement> subreqWrapper = new ArrayList<SubRequirement>();
			//Selected courses
			SubRequirement courseContainer = new SubRequirement();
			courseContainer.setCourses(new ArrayList<Course>());
			subreqWrapper.add(courseContainer);
			newReq.setSubRequirements(subreqWrapper);
			selected.add(newReq);
			//The courses related to this requirement
			ArrayList<Course> reqCourses = courses.get(i);
			for(Course course : reqCourses) {
				CheckedTextView checkView = coursesSelected.get(course);
				if(checkView != null && checkView.isChecked()) {
					//Add in the selected course
					courseContainer.getCourses().add(course);
				}
			}
		}
		return selected;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		ArrayList<Course> reqCourses = courses.get(groupPosition);
		return reqCourses.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return reqs.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return reqs.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
		   convertView = inflater.inflate(R.layout.course_selector_textview, null);
		}
		((TextView) convertView).setText(reqs.get(groupPosition).getName());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
