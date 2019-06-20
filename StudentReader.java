import java.util.ArrayList;

// methods to read from the storing mechanism

public interface StudentReader
{
	Student getStudent(String regNum);
	Student getStudent(String userName, String password);
	ArrayList<Student> getList();
}