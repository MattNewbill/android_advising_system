

public class Course {
	int id;
	String name;
	String description;
	int unit;
	boolean isCompleted;
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(int id, String name, String description, int unit) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unit = unit;
		isCompleted = false;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public void print() {
		System.out.println("Course: "+getName());
		System.out.println("ID: "+getId());
		System.out.println("Description: "+getDescription());
		System.out.println("Units: "+getUnit());
		System.out.println("Passed: "+isCompleted());
	}

}
