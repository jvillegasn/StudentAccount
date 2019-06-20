
import java.util.Scanner;

// methods for a single student account, after log in

public class StudentAccount implements StudentConstants {
	private static Scanner sc = new Scanner(System.in);
	private static StudentDAO stdDAO = StudentFactory.getStudentDAO();
	
	public static void showAccount(Student user) {    // display student account info
            StringBuilder sb = new StringBuilder();
			
			System.out.println("Account Info:");
			sb.append(StringUtils.padSpaces("UserName", USER_NAME_S + 2, " ")
			
						+ StringUtils.padSpaces("Password", PASSWORD_S + 4, " ")
						
						+ StringUtils.padSpaces("RegNum", REG_NUM_S + 4, " ")
						
						+ StringUtils.padSpaces("Full Name", NAME_S + 2, " ")
						
						+ StringUtils.padSpaces("Age", DOUBLE_S + 3, " ")
						
						+ StringUtils.padSpaces("Credits", DOUBLE_S + 7, " ")
						
						+ StringUtils.padSpaces("In-State", CHAR_S + 9, " ")
						
						+ StringUtils.padSpaces("Balance", NUMBER_S + 2, " ")
						
						+ StringUtils.padSpaces("Payment", NUMBER_S + 2, " ")
						+ "\n");
			sb.append( StringUtils.padSpaces("", BAR_S, "-")
						+ "\n");
		
            sb.append(
						StringUtils.padSpaces(user.getUserName(), USER_NAME_S + 2, " ")
						
                        + StringUtils.padSpaces(user.getPassword(), PASSWORD_S + 4, " ")
						
						+ StringUtils.padSpaces(user.getRegNum(), REG_NUM_S + 4, " ")
						
					    + StringUtils.padSpaces(user.getFullName(), NAME_S + 2, " ")
						
						+ StringUtils.padSpaces(user.getStringAge(), DOUBLE_S + 3, " ")
						
					    + "   " + StringUtils.padSpaces(user.getStringCredits(), DOUBLE_S + 4, " ")
						
						+ "     " + StringUtils.padSpaces(Character.toString(user.getInState()), CHAR_S + 5, " ")
						
						+ StringUtils.padSpaces(user.getStringCurrency(user.getBalance()), NUMBER_S + 2, " ")
						
						+ StringUtils.padSpaces(user.getStringCurrency(user.getPayment()), NUMBER_S + 2, " ")
						+ "\n"
                );
            System.out.println(sb.toString());
			
			Validator.pressContinue(sc);
    }
	
	public static void deleteAccount(Student user) {  // delete account
		if (user != null){
			boolean success = stdDAO.remove(user);
			if (success) {
				System.out.println(user.getFullName() + " was deleted from Student's List.\n");
			}
			else
				System.out.println("\t-Error! Unable to delete account\n");
		}
		
		Validator.pressContinue(sc);
	}

    public static void editAccount(Student user) {     // edit account info
        
        String fullName = Validator.getLine(sc, "Enter new full name: ");
		String userName = Validator.getSubString(sc, "Enter new UserName: ", USER_NAME_S);	
		String password = Validator.getSubString(sc, "Enter new Password: ", PASSWORD_S);		
        int age = Validator.getInt(sc, "Enter new age: ");
		int credits = Validator.getInt(sc, "Enter num of credits: ");
		char inState = Validator.getChar(sc, "Are you in-State student? (y/n)");

        user.setFullName(fullName);
		user.setUserName(userName);
		user.setPassword(password);
		user.setAge(age);
		user.setCredits(credits);
		user.setInState(inState);
		user.setPayment();
		
        boolean success = stdDAO.update(user);
        if (success) {
            System.out.println(fullName + " was successfully edited.\n");
        } else {
            System.out.println("\t-Error! Unable to edit the Student info\n");
        }
		
		Validator.pressContinue(sc);
    }
	
	
	public static void paying(Student user){      // making a payment
		System.out.println("Making Payment...");
		double pay = Validator.getPositiveDouble(sc, "Enter amount: ");
		
		user.makePayment(pay);
		
		boolean success = stdDAO.update(user);
        if (success) {
            System.out.println("Payment completed!\n");
        } else {
            System.out.println("\t-Error! Unable to complete payment\n");
        }
		
		Validator.pressContinue(sc);
	}
	
	public static void adding(Student user){        // adding money to account balance
		System.out.println("Adding Money...");
		double add = Validator.getPositiveDouble(sc, "Enter amount: ");
		
		user.addMoney(add);
		
		boolean success = stdDAO.update(user);
        if (success) {
            System.out.println("Amount added to Balance!\n");
        } else {
            System.out.println("\t-Error! Unable to add amount to balance\n");
        }
		Validator.pressContinue(sc);
	}
	
}