
// methods to write to the storing mechanism

public interface StudentWriter
{
	boolean include(Student x);
	boolean update(Student x);
	boolean remove(Student x);
	boolean deleteAll();
}