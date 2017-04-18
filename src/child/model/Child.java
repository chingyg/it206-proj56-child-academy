package child.model;

/* This is the model class. No calculations allowed here. */

public class Child
{

	/* Required info */
	private int age;
	private String name;
	private String parentName;
	private String parentNumber;

	/* Enrollment info */
	private Enrollment enrollment;

	/* Billing info */
	private double bill;

	public Child(int age, String name, String parentName, String parentNumber)
	{
		this.age = age;
		this.name = name;
		this.parentName = parentName;
		this.parentNumber = parentNumber;
		this.enrollment = null;
		this.setBill(0.0);

	}

	public int getAge()
	{
		return this.age;
	}

	public double getBill()
	{
		return bill;
	}

	public Enrollment getEnrollment()
	{
		return this.enrollment;
	}

	public String getName()
	{
		return this.name;
	}

	public String getParentName()
	{
		return this.parentName;
	}

	public String getParentNumber()
	{
		return this.parentNumber;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setBill(double bill)
	{
		this.bill = bill;
	}

	public void setEnrollment(Enrollment e)
	{
		this.enrollment = e;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setParentName(String name)
	{
		this.parentName = name;
	}

	public void setParentNumber(String number)
	{
		this.parentNumber = number;
	}

}