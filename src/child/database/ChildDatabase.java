package child.database;
import child.model.Child;
import child.model.Enrollment;

/**
 * Defines operations used by a database. Operations should include: adding,
 * removing, searching, resizing, and printing
 * 
 * @author Ching Chen
 *
 */
public interface ChildDatabase
{

	/*
	 * required fields: child’s name, age,parent’s name,and parent’s cell phone
	 * number.
	 */
	int addChild(Child child);

	int enrollChild(int i, Enrollment e);

	int enrollChild(int i, Enrollment e, String org, double discount);

	int removeEnrolledChild(int i);
	
	void resizeDatabase();
	
	Child getChild(int i);

	String printChildRecord();

}