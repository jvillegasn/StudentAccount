

// methods that output a menu

public class StudentMenu{

	public static void mainMenu() {
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("1) Log In");
		System.out.println("2) Sign up");
		System.out.println("3) Display Students List");
		System.out.println("4) Remember Account");
		System.out.println("5) Delete All Accounts");
		System.out.println("6) Exit Program...");
	}
	
	public static void subMenu() {
		System.out.println();
		System.out.println("1) Select Another");
		System.out.println("2) Exit Account...");
	}

	public static void accountMenu(){
		System.out.println();
		System.out.println("Student Account");
        System.out.println("1) Display Student");
		System.out.println("2) Make Payment");
		System.out.println("3) Add Money");
        System.out.println("4) Edit Student");
		System.out.println("5) Delete Student Account");
        System.out.println("6) Exit Account...");
    }
	
	public static void tryMenu() {
		System.out.println();
		System.out.println("1) Try again");
		System.out.println("2) Return to Main Menu...");
	}
}