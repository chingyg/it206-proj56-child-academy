package child.model;

public class Enrollment
{

	/* Number of enrolled classes */
	private int enrolledClasses;

	/* 0 - Fairfax 
	 * 1 - Front Royal, 
	 * 2 - Manassas, 
	 * 3 - Loudoun, 
	 * 4 - Arlington,
	 * 5 - Washington, DC */
	private int location;

	/* 3 digit code */
	private String schoolDistrict;

	public Enrollment()
	{
		this.enrolledClasses = 0;
		this.schoolDistrict = "";
		this.location = -1;
	}
	
	public Enrollment(int enrollment, int location, String districtCode)
	{
		this.enrolledClasses = enrollment;
		this.location = location;
		this.schoolDistrict = districtCode;
	}

	public int getEnrolledClasses()
	{
		return this.enrolledClasses;
	}

	public int getLocation()
	{
		return location;
	}

	public String getSchoolDistrict()
	{
		return schoolDistrict;
	}

	public void setLocation(int location)
	{
		this.location = location;
	}

	public void setSchoolDistrict(String schoolDistrict)
	{
		this.schoolDistrict = schoolDistrict;
	}

}