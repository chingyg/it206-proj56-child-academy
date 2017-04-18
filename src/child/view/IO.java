package child.view;
import java.util.InputMismatchException;
import java.util.Scanner;

import child.database.ChildDatabaseImpl;
import child.model.Child;
import child.model.Enrollment;
import child.model.ScholarshipChild;
import child.util.ProcessChildren;
import child.util.Validator;

/* What the user will be interacting with. Does the input and output to console */
public class IO
{
	
	/**
	 * Class dedicated for printing stuff to the console. This is the only place
	 * in this program that handles the printing.
	 * 
	 * @author Ching Chen
	 *
	 */
	private class Print{
		
		public void emptyEnrollments()
		{
			String message = "There are no enrolled child to remove\n";
			System.out.println(message);
		}
		
		public void insufficientSpace()
		{
			String message = "[INSUFFICIENT SPACE]\n";
			System.out.println(message);
		}
		public void invalidAge()
		{
			String message = "[INVALID INPUT] Child Age must be a number between 9-16\n";
			System.out.print(message);
		}
		
		public void invalidChildType()
		{
			String message = "[INVALID INPUT] Please enter 'y' or 'n'\n";
			System.out.print(message);
		}
		
		public void invalidDiscount()
		{
			String message = "[INVALID INPUT] Please enter a number between 0 and 100\n";
			System.out.println(message);
		}
		
		public void invalidDistrictCode()
		{
			String message = "[INVALID INPUT] District Codes are in the format: xyx "
					+ "where x is a number and y is an upper case letter\n";
			System.out.println(message);
		}

		public void invalidEnrollments()
		{
			String message = "[INVALID INPUT] Please enter a number between 1 and 6\n";
			System.out.println(message);
		}
		public void invalidIndex()
		{
			String message = "[INVALID INPUT] Please choose a number from the list of children.\n";
			System.out.println(message);
		}
		public void invalidLocation()
		{
			String message = "[INVALID INPUT] Please choose a number from the list of locations\n";
			System.out.println(message);
		}

		public void invalidMenuInput()
		{
			String message = "[INVALID INPUT]\n";
			System.out.println(message);
		}

		public void invalidNumber()
		{
			String message = "[INVALID INPUT] Phone numbers must be in this format: xxx-xxx-xxxx\n";
			System.out.print(message);
		}

		public void menu()
		{
			String menu = "========================================\n"
					+ "Please select an option from the following menu items.\n\n"
					+ "[0]    Add Child\n"
					+ "[1]    Enroll Child\n"
					+ "[2]    Remove Enrolled Child\n"
					+ "[3]    Print Child Record\n"
					+ "[4]    Quit\n";
			
			System.out.println(menu);
		}

		public  void quit()
		{
			System.out.println("[TERMINATING] Goodbye!\n"
					+ "========================================");
			System.exit(0);
		}

		public void successAddChild()
		{
			String message = "[SUCCESS] Child added.\n";
			System.out.print(message);
		}

		public void successEnrollChild()
		{
			String message = "[SUCCESS] Child enrolled.\n";
			System.out.print(message);
		}
		
		public void successRemoveChild()
		{
			String message = "[SUCCESS] Child removed.\n";
			System.out.print(message);
		}
		public void welcome()
		{
			String message = "========================================\n"
					+ "Welcome to Project 5/6!\n"
					+ "Author: Ching Chen\n";
			
			System.out.println(message);
		}
	}
	/* Singletons */
	private final Scanner scan;
	private final Validator validator;
	private final Print print = new Print();
	
	private final ProcessChildren process = new ProcessChildren();

	private ChildDatabaseImpl database;

	public IO()
	{
		this.scan = new Scanner(System.in);
		this.validator = new Validator();
		this.database = new ChildDatabaseImpl();

	}
	
	private void addChild()
	{
		System.out.println("You selected: [Add Child]\n");
		int childAge = 0;
		String childName = "", parentName="",parentNumber="";
		String input = "";
		
		/* Age */
		System.out.println("Enter Child's age");
		while((input = scan.nextLine()) != null)
		{
			if(input.length() > 2)
			{
				print.invalidAge();
				continue;
			}
			
			try
			{
				childAge = Integer.parseInt(input);
				
				if (!validator.isValidAge(childAge))
				{
					print.invalidAge();
				}
				else
				{
					break;
				}
				

			} catch (InputMismatchException e)
			{
				print.invalidAge();
			}	
		}
		
		/* Name */
		System.out.println("Enter Child's name");
		childName = scan.nextLine();
		
		/* Parent's Name */
		System.out.println("Enter Parent's name");
		parentName = scan.nextLine();
		
		/* Parent Number*/
		while(true)
		{
			System.out.println("Enter Parent's number");
			parentNumber = scan.nextLine();
			
			if (!validator.isValidPhoneNumber(parentNumber))
			{
				print.invalidNumber();
			}
			else
			{
				break;
			}
	
		}
		
		/* Type of Child */
		
		while(true)
		{
			System.out.print("Is this a Scholarship Child?\n"
					+ "Enter 'y' for yes and 'n' for no\n");
			input = scan.nextLine();
			
			if(input.length() != 1)
			{
				print.invalidChildType();
				continue;
			}
			int ascii = (int)input.charAt(0);
			if (ascii == 89 || ascii == 121)
			{
				// scholarship child
				database.addChild(new ScholarshipChild(childAge, childName,parentName,parentNumber));
				break;
			}
			else if (ascii == 78 || ascii == 110)
			{
				//non-scholarship child
				database.addChild(new Child(childAge, childName,parentName,parentNumber));
				break;
			}
			else
			{
				print.invalidChildType();
				continue;
			}
		}
		
		print.successAddChild();	
	}
	
	private void enrollChild()
	{
		Enrollment enrollInfo;
		int index = 0;
		int enrollments = 0;
		int location = 0;
		String districtCode = "";
		
		/* Check if child is available */
		if (!database.isChildAvailable())
		{
			System.out.println("No Child to enroll. Please add a child.\n");
			return;
		}
		if(database.isMaxEnrollmentReached())
		{
			System.out.println("Maximum number of enrollments reached.\n");
			return;
		}
		

		/* index */
		while(true)
		{
			System.out.println("Please select which child to enroll.\n");
			
			//Print out all children and their indexes.
			System.out.println(database.getPrintedQualifiedChildren());
			
			String input = scan.nextLine();
			
			try
			{
				index = Integer.parseInt(input);
			} catch (InputMismatchException e)
			{
				print.invalidIndex();
				continue;
			}

			if (index > database.getSize())
			{
				print.invalidIndex();
				continue;
			}
			if(database.getChild(index) == null)
			{
				print.invalidIndex();
				continue;
			}
			else{
				break;
			}

		}
	
	
		/* Number of enrollments */
		while(true)
		{
			System.out.println("Please enter number of enrollments.\n");
			String input = scan.nextLine();
			if (input.length() > 1)
			{
				print.invalidEnrollments();
				continue;
			}
			try
			{
				enrollments = Integer.parseInt(input);
			} catch (InputMismatchException e)
			{
				print.invalidEnrollments();
				continue;
			}
			if (!validator.isValidEnrollment(enrollments))
			{
				print.invalidEnrollments();
				continue;
			}
			
			/* Need to check if there's enough seats */
			if(!database.hasEnoughSeats(enrollments))
			{
				print.insufficientSpace();
				continue;
			}
			else
			{
				break;
			}
			
		}
		
		/* Location */
		while (true)
		{
			System.out.println("Please Enter a location from the following list:\n");
			System.out.println(database.getPrintedLocations());
			String input = scan.nextLine();
			if (input.length() > 1)
			{
				print.invalidLocation();
				continue;
			}
			
			try
			{
				location = Integer.parseInt(input);
			} catch (InputMismatchException e)
			{
				print.invalidLocation();
				continue;
			}
			
			if (!validator.isValidLocation(location))
			{
				print.invalidLocation();
				continue;
			}
			else
			{
				break;
			}
			
		}
		
		/* School district code */
		while(true)
		{
			System.out.println("Please Enter school district code\n");
			districtCode = scan.nextLine();
			if(!validator.isValidSchoolDistrict(districtCode))
			{
				print.invalidDistrictCode();
				continue;
			}
			else
			{
				break;
			}	
		}
		
		/* We have enough info to create Enrollment object */
		enrollInfo = new Enrollment(enrollments,location,districtCode);
		
		
		/* Additional Scholarship Child information */
		if (database.getChild(index) != null && database.getChild(index) instanceof ScholarshipChild )
		{
			String organization = "";
			double discount = 0.0;
			
			/* organization */
			System.out.println("Please enter organization name\n");
			organization = scan.nextLine();
			
			/* Discount */
			while(true)
			{
				System.out.println("Please enter discount amount\n");
				String input = scan.nextLine();
				try
				{
					discount = Double.parseDouble(input);
				} catch (NumberFormatException e)
				{
					print.invalidDiscount();
					continue;
				}
				if(!validator.isValidDiscount(discount))
				{
					print.invalidDiscount();
					continue;
				}
				else
				{
					break;
				}
			}
			
			database.enrollChild(index, enrollInfo, organization, discount);
		}
		else
		{
			database.enrollChild(index, enrollInfo);
		}
		print.successEnrollChild();
		
		/* Calculate the bill */
		double bill = this.process.calculateBill(database.getChild(index));
		
		database.getChild(index).setBill(bill);
		
	}
	private void printChildRecord(){
		System.out.println(this.database.printChildRecord());
		
	}
	private void quit()
	{
		this.scan.close();
		print.quit();
		System.exit(0);
	}
	private void removeEnrollChild(){
		int index = 0;
		
		if(database.getEnrollments() < 1)
		{
			print.emptyEnrollments();
			return;
		}
		
		while(true)
		{
			System.out.println("Please select a child from the list of children\n");
			System.out.println(database.getPrintedEnrolledChildren());
			
			String input = scan.nextLine();
			try
			{
				index = Integer.parseInt(input);
				
				
			} catch (NumberFormatException e)
			{
				print.invalidIndex();
				continue;
			}
			if (index > database.getSize())
			{
				print.invalidIndex();
				continue;
			}
			
			if(database.getChild(index) == null || database.getChild(index).getEnrollment() == null)
			{
				print.invalidIndex();
				continue;
			}
			else
			{
				break;
			}
	
		}
		
		database.removeEnrolledChild(index);
		print.successRemoveChild();
	}
	
	/* Starts the program. Should be the only thing exposed. */
	public void start()
	{
		print.welcome();
		print.menu();
		String input = "";
		while((input = scan.nextLine()) != null)
		{
			//System.out.println("You Typed: " + input);
			if(!validator.isValidMenuInput(input))
			{
				print.invalidMenuInput();
				print.menu();
				continue;
			}

			int inputOption = Integer.parseInt(input);
			switch (inputOption)
			{
			case 0:
			{
				/* Add Child */
				addChild();
				print.menu();
				break;
			}
			case 1:
			{
				enrollChild();
				print.menu();
				break;
			}
			case 2:
			{
				removeEnrollChild();
				print.menu();
				break;
			}
			case 3:
			{
				printChildRecord();
				print.menu();
				break;
			}
			case 4:
			{
				quit();
				break;
			}
			default:{
				print.menu();
				break;
			}
			}
		}
	}
}