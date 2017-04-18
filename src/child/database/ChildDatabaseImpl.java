package child.database;
import child.model.Child;
import child.model.Enrollment;
import child.model.ScholarshipChild;

/* This is the controller class. Executes basic datastructure operations. */
public class ChildDatabaseImpl implements ChildDatabase
{

	/* Array containing child objects */
	private Child[] db;
	private final int MAX_NUMBER_OF_ENROLLED_CHILDREN = 17;
	
	/* Number of current enrollments */
	private int currentEnrollments;
	
	/* Number of children added */
	private int currentChildren;
	

	public ChildDatabaseImpl()
	{
		this.db = new Child[17];
		this.currentEnrollments = 0;
		this.currentChildren = 0;

	}

	@Override
	public int addChild(Child child)
	{

		/* forget optimizations */
		for (int i = 0; i < db.length; i++)
		{
			if (db[i] == null)
			{
				db[i] = child;
				currentChildren++;
				return 1;
			}
		}
		
		//No space left
		resizeDatabase();
		return addChild(child);
		

	}

	/*
	 * Enroll Non-Scholarship Child Assume child is qualified for enrollment
	 */
	@Override
	public int enrollChild(int index, Enrollment e)
	{
		if (db[index] != null)
		{
			db[index].setEnrollment(e);
			this.currentEnrollments+= e.getEnrolledClasses();
			return 1;

		} else
			return 0;
	}
	
	/*
	 * Enroll Scholarship Child Assume child is qualified for enrollment
	 */
	@Override
	public int enrollChild(int index, Enrollment e, String org, double discount)
	{
		if (db[index] != null)
		{
			db[index].setEnrollment(e);

			((ScholarshipChild) db[index]).setOrganization(org);
			((ScholarshipChild) db[index]).setDiscount(discount);
			this.currentEnrollments+= e.getEnrolledClasses();
			return 1;

		} else
			return 0;
	}

	/**
	 * Returns a child at a specified index from the Array
	 */
	public Child getChild(int i)
	{
		return this.db[i];
	}

	/**
	 * Gets the current number of Children
	 * @return The total number of current Children in the array
	 */
	public int getCurrentChildren()
	{
		return this.currentChildren;
	}

	/**
	 * Get the total number of current enrollments
	 * @return number of current enrollments
	 */
	public int getEnrollments()
	{
		return this.currentEnrollments;
	}

	/**
	 * Converts the int values to a real location. I put this here outside of
	 * the model Enrollment class because there could be cases where another
	 * database is created, but has different location key values
	 * @param location value that corresponds to a location
	 * @return The String representation of the location corresponding to the passed in value
	 */
	private String getLocationValue(int location)
	{
		String value = "";
		switch (location)
		{
		case 0:
			value = "Fairfax";
			break;
		case 1:
			value = "Front Royal";
			break;
		case 2:
			value = "Manassas";
			break;
		case 3:
			value = "Loudoun";
			break;
		case 4:
			value = "Arlington";
			break;
		case 5:
			value = "Washington, DC";
			break;
		default:
			break;
		}

		return value;
	}
	/**
	 * Returns a nicely printed list of children that are currently enrolled
	 * @return printed format of children that are currently enrolled
	 */
	public String getPrintedEnrolledChildren()
	{
		String output = "";
		for(int i = 0; i < this.db.length; i++)
		{
			if(db[i] != null && db[i].getEnrollment() != null)
			{
				output += "[" + i + "]    Child Name: " + db[i].getName() + "\n";
			}
		}
		return output;
	}

	/**
	 * Returns the list of locations as options in printed format
	 * @return Printed format of locations
	 */
	public String getPrintedLocations()
	{
		String output = "[0]    Fairfax\n"
				+ "[1]    Front Royal\n"
				+ "[2]    Manassas\n"
				+ "[3]    Loudoun\n"
				+ "[4]    Arlington\n"
				+ "[5]    Washington, DC\n";
		
		return output;
	}
	
	/**
	 * Returns a nicely printed list of children that have no enrollments
	 * @return Printed format of Children that have no enrollments
	 */
	public String getPrintedQualifiedChildren()
	{
		String output = "";
		for(int i = 0; i < this.db.length; i ++)
		{
			if(db[i] != null && db[i].getEnrollment() == null)
			{
				output += "[" + i + "]    Child Name: " + db[i].getName() + "\n";
			}
		}
		
		return output;
	}

	/**
	 * Get the size of the array of children
	 * @return size of array of children
	 */
	public int getSize()
	{
		return this.db.length;
	}

	/**
	 * Determine if there are enough seats for enrollment
	 * @param newEnrollments number of new enrollments
	 * @return True if there is enough seats, false otherwise
	 */
	public boolean hasEnoughSeats(int newEnrollments)
	{
		return (this.currentEnrollments + newEnrollments) <= MAX_NUMBER_OF_ENROLLED_CHILDREN;

	}
	/**
	 * Determines if there are any children that has no enrollments
	 * @return True if there is a Child with no enrollments
	 */
	public boolean isChildAvailable()
	{
		return this.currentChildren > 0;
	}

	/**
	 * Determine if a child is qualified for enrollment
	 * @param child Child to evaluate
	 * @return true if child is qualified for enrollment, false otherwise
	 */
	public boolean isChildQualifiedForEnrollment(Child child)
	{

		return child.getEnrollment() == null;
	}
	/**
	 * Determine if maximum number of enrollments has been reached
	 * @return True if max number of enrollments is reached, false otherwise
	 */
	public boolean isMaxEnrollmentReached()
	{
		return currentEnrollments >= MAX_NUMBER_OF_ENROLLED_CHILDREN;
	}
	@Override
	public String printChildRecord()
	{
		String output = "========================================\n"
				+ "Child Record Report:\n";
		if(this.currentChildren == 0)
		{
			output += "No children to display. Please add a child.\n";
			return output;
		}

		for (int i = 0; i < db.length; i++)
		{

			if(db[i] != null)
			{
				if (db[i] instanceof ScholarshipChild)
				{
					output += "\nChild Type: Scholarschip Child\n";
					
				} else
				{
					output += "\nChild Type: Non-Scholarschip Child\n";
				}
				output += "Child Name: " + db[i].getName() + "\n";
				output += "Child age: " + db[i].getAge() + "\n";
				output += "Parent Name: " + db[i].getParentName() + "\n";
				output += "Parent Cellphone Number: " + db[i].getParentNumber() + "\n";
				if(db[i].getEnrollment() != null)
				{
					output += "Number of enrollments: " + db[i].getEnrollment().getEnrolledClasses() + "\n";
					output += "School District: " + db[i].getEnrollment().getSchoolDistrict() + "\n";
					output += "Location: " + getLocationValue(db[i].getEnrollment().getLocation()) + "\n";
					if (db[i] instanceof ScholarshipChild)
					{
						output += "Organization: " + ((ScholarshipChild)db[i]).getOrganization() + "\n";
						output += "Discount: " + ((ScholarshipChild)db[i]).getDiscount() + "\n";
					}
					
					output += "Bill: $" + db[i].getBill() + "\n";
				}
			}

			

		}

		return output;
	}
	
	@Override
	public int removeEnrolledChild(int index)
	{

		if (db[index] != null)
		{
			this.currentChildren--;
			this.currentEnrollments-= db[index].getEnrollment().getEnrolledClasses();
			
			db[index] = null;
			return 1;
		} else
			return 0;
	}
	
	public void resetEnrolledChildren()
	{
		this.currentEnrollments = 0;
	}
	
	/**
	 * Expands the size of the current array
	 */
	@Override
	public void resizeDatabase()
	{
		// Double the size
		Child[] temp = new Child[db.length * 2];
		
		for(int i = 0; i < db.length; i++)
		{
			temp[i] = db[i];
		}
		System.out.println("size is now: " + temp.length);
		
		this.db = temp;
	}

}
