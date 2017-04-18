package child.util;
/* Used to validate user input */
public class Validator
{

	public boolean isAHyphenOrDot(char c)
	{
		int ascii = (int) c;
		return (ascii == 45 || ascii == 46);
	}

	public boolean isANumber(char c)
	{
		int ascii = (int) c;
		return (ascii > 47 && ascii < 58);
	}

	public boolean isUpperCase(char c)
	{
		int ascii = (int) c;
		return (ascii > 64 && ascii < 91);
	}

	/* Children are between 9 and 16 inclusive */
	public boolean isValidAge(int age)
	{
		return age > 8 && age < 17;
	}

	public boolean isValidDiscount(double discount)
	{
		return discount >= 0.0 && discount <= 100.0;
	}

	/* Each Child can have 1-6 number of enrollments inclusive */
	public boolean isValidEnrollment(int enrollments)
	{
		return enrollments > 0 && enrollments < 7;
	}

	/* Locations are from [0-5] inclusive */
	public boolean isValidLocation(int l)
	{
		return l > -1 && l < 6;
	}

	public boolean isValidPhoneNumber(String n)
	{
		if (n != null && !n.equals(""))
		{
			// 121-121-1212
			if (n.length() == 12)
			{
				for (int i = 0; i < n.length(); i++)
				{
					if (i == 3 || i == 7)
					{
						if (!isAHyphenOrDot(n.charAt(i)))
						{
							return false;
						}
						continue;
					}
					if (!isANumber(n.charAt(i)))
					{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isValidSchoolDistrict(String code)
	{
		if (code != null && !code.equals(""))
		{
			// Check length
			if (code.length() == 3)
			{
				// Check first letter
				if (isANumber(code.charAt(0)))
				{
					// Check second letter
					if (isUpperCase(code.charAt(1)))
					{
						// Check third letter
						if (isANumber(code.charAt(2)))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isValidMenuInput(String input)
	{

		if (input != null && !input.equals(""))
		{
			if(input.length() == 1)
			{
				int n = 0;
				try
				{
					n = Integer.parseInt(input);
					return (n > -1 && n < 5);
				} catch (NumberFormatException e)
				{
					//System.out.println(e);
				}
				
			}

		}
		return false;
	}
}