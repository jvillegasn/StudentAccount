
// One only interface that includes data from other interfaces
// Data Access Object

public interface StudentDAO extends StudentReader, StudentWriter, StudentConstants
{
	// Includes all methods from both StudentReader and StudentWriter interfaces
	// Includes all constants from StudentConstants interface
}