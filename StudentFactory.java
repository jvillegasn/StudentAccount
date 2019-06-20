
// Class that connects StudentDAO to StudentXML storage

public class StudentFactory
{
	// calling this methos will link the data to the xml storage file
	public static StudentDAO getStudentDAO()
	{
		StudentDAO stDAO = new StudentXML();
		return stDAO;
	}
}