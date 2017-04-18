package child.util;
import child.model.Child;
import child.model.ScholarshipChild;

/* Does calculations on child model classes */
public class ProcessChildren
{

	private final double BILL_RATE = 79.0;
	private final double DC_TRANSPORTATION = 23.0;

	/**
	 * Calculates the bill for passed in Child
	 * @param child Child used to calculate the total bill
	 * @return total bill for child
	 */
	public double calculateBill(Child child)
	{

		int enrollments = child.getEnrollment().getEnrolledClasses();
		double result = enrollments * BILL_RATE;

		// Add Washington DC Parking fee
		if (child.getEnrollment().getLocation() == 5)
		{
			result += (enrollments * DC_TRANSPORTATION);
		}

		if (child instanceof ScholarshipChild)
		{
			result -= result * ((ScholarshipChild) child).getDiscount();
		}

		return result;
	}
}