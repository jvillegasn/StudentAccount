
// Final Project: Student App
// Student: Javier Villegas
// CIT 242 - Data Structures
// Prof: Jack Haley
// Date: 25 April 2017

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


// This program creates a list of Student records that implements many different options

public class StudentApp implements StudentConstants {

	private static StudentDAO stdDAO = null;
    private static Scanner sc = null;
	
	public static void main(String args[]) {
        System.out.println("\nWelcome to Student Register App");

        stdDAO = StudentFactory.getStudentDAO();
        sc = new Scanner(System.in);
		
        int choice = 0;
        while (choice != 6) {
			StudentMenu.mainMenu();     // Main menu of the app
 
            choice = Validator.getInt(sc,
                    "Enter choice: ", 1, 6);   // get choice. if 5, end the program
            System.out.println();
			
			switch(choice){
				case 1: logIn(); break;
				case 2: signUp(); break;
				case 3: showList(); break;
				case 4: rememberAccount(); break;
				case 5: deleteAll(); break;
				case 6: System.out.println("Thanks for using the program!\n");  break;
			}
        }
    }

	public static void showList() {   		// displays the all student records found in the list
        ArrayList<Student> list = stdDAO.getList();
        if (list == null || list.size() == 0) {
            System.out.println("\t-There are no Students registered.\n");
        } else {
            Student std = null;
            StringBuilder sb = new StringBuilder();
			
			System.out.println("Student List: ");
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
		
            for (int i = 0; i < list.size(); i++) {
                std = list.get(i);
                sb.append(
						StringUtils.padSpaces(std.getUserName(), USER_NAME_S + 2, " ")
						
                        + StringUtils.padSpaces("******", PASSWORD_S + 4, " ")
						
						+ StringUtils.padSpaces(std.getRegNum(), REG_NUM_S + 4, " ")
						
					    + StringUtils.padSpaces(std.getFullName(), NAME_S + 2, " ")
						
						+ StringUtils.padSpaces(std.getStringAge(), DOUBLE_S + 3, " ")
						
					    + "   " + StringUtils.padSpaces(std.getStringCredits(), DOUBLE_S + 4, " ")
						
						+ "     " + StringUtils.padSpaces(Character.toString(std.getInState()), CHAR_S + 5, " ")
						
						+ StringUtils.padSpaces(std.getStringCurrency(std.getBalance()), NUMBER_S + 2, " ")
						
						+ StringUtils.padSpaces(std.getStringCurrency(std.getPayment()), NUMBER_S + 2, " ")
						+ "\n"
                );
            }
            System.out.println(sb.toString());
        }
		
		Validator.pressContinue(sc);
    }

	public static void signUp(){     // adds a new student record to the list
	    String fullName = Validator.getLine(sc, "Enter your full name: ");
		String userName = Validator.getSubString(sc, "Enter a UserName[8 char]: ", USER_NAME_S);	
		String password = Validator.getSubString(sc, "Enter a Password[6 char]: ", PASSWORD_S);	
		
		String regNum = "exists";
		while(regNum.equals("exists")){
			regNum = Validator.getRegNum(stdDAO);
		}
		System.out.println("RegNum Assigned: " + regNum);
	
		int age = Validator.getInt(sc, "Enter your age[2 digits]: ", 10, 60);
		int credits = Validator.getInt(sc, "Enter num of credits[2 digits]: ", 0, 40);
		char inState = Validator.getChar(sc, "Are you in-State student? (y/n): ");        
		double balance = Validator.getPositiveDouble(sc, "Enter your balance: ");
		
        Student user = new Student(userName, password, regNum, fullName, age, credits, inState, balance);
		
        boolean success = stdDAO.include(user);

        System.out.println();
        if (success) {
            System.out.println(fullName + " was successfully included.\n");
        } else {
            System.out.println("\t-Error! Unable to aggregate Student\n");
		}
		
		Validator.pressContinue(sc);
	}


	public static void logIn(){         // the user access to an student account and lets do many operations
		ArrayList<Student> list = stdDAO.getList();
		if (list == null || list.size() == 0) {
            System.out.println("\t-There are no Students registered.\n");
			Validator.pressContinue(sc);
			return;
		}
			
		String userName = Validator.getString(sc, "UserName[8 char]: ");	
		String password = Validator.getString(sc, "Password[6 char]: ");
		
		Student user = stdDAO.getStudent(userName, password);	
		if(user != null){
			int choice = 0;
			while (choice != -1 ) {
				StudentMenu.accountMenu();   // Student Account Menu 

				choice = Validator.getInt(sc,      //gets choice. If 6, returns to main Menu
						"Enter choice: ", 1, 6);
				System.out.println();
			
				switch(choice){
					case 1: StudentAccount.showAccount(user);  break;
					case 2: StudentAccount.paying(user); break;
					case 3: StudentAccount.adding(user); break;
					case 4: StudentAccount.editAccount(user);   break;
					case 5: StudentAccount.deleteAccount(user);
					case 6: choice = -1; continue;
				}
			
				StudentMenu.subMenu();  // Menu that asks to make another operation or return to main menu
				choice = Validator.getInt(sc,
						"Enter choice: ", 1, 2);		//gets choice. if 2, return to main menu
			
				switch(choice){
					case 1: continue;
					case 2: choice = -1; continue;
				}
			}
		}
		else{
			System.out.println("\t-Wrong UserName or Password!\n");
			Validator.pressContinue(sc);
		}
	}
	
	public static void deleteAll(){      // deletes All records
		char choice = Validator.getChar(sc, "Deleting all accounts...\nAre your sure? (y/n): ");
		if(choice == 'y'){
			boolean success = stdDAO.deleteAll();

			System.out.println();
			if (success) {
				System.out.println("All student records were deleted!\n");
			} else {
				System.out.println("\t-Error! Unable to clear Student List\n");
			}
		}
		else{
			System.out.println();
			System.out.println("No records were deleted!\n");
		}
		
		Validator.pressContinue(sc);
	}

	public static void rememberAccount(){     		// remember an account info by entering regNum 
													// and answering to questions
		ArrayList<Student> listX = stdDAO.getList();
		if (listX == null || listX.size() == 0) {
            System.out.println("\t-There are no Students registered.\n");
			Validator.pressContinue(sc);
			return;
		}
		
		System.out.println("Remember Account:");
		
		int choice = 0;
		
		while(choice != -1){
			String regNum = Validator.getString(sc, "Enter your regNum[4 digits]: ");
			Student user = stdDAO.getStudent(regNum);	
			if(user == null)
				System.out.println("\t-Student Not Found!");
			else{
				Question que = new Question();
				ArrayList<Question> list = que.setQuestions();
				Random rand = new Random();
				int correct = 0;
				String ans = " ";
				
				int r1 = rand.nextInt(8), r2 = rand.nextInt(8);
				while(r2 == r1){
					r2 = rand.nextInt(8);
				}
				
				for(int i = 0; i < 2; i++){
					switch(i){
						case 0: que = list.get(r1); break;
						case 1: que = list.get(r2); break;
					}
					
					ans = Validator.getString(sc, que.getQue());
					if(ans.equalsIgnoreCase(que.getAns())){
						correct++;
					}
					else{
						System.out.println("\t-Wrong Answer!");
						Validator.pressContinue(sc);
						System.out.println();
						break;
					}
				}
					
				if(correct == 2){
					System.out.println("Correct!\n");
					StudentAccount.showAccount(user);
					return;
				}
				else
					continue;
			}
			
			StudentMenu.tryMenu();
			choice = Validator.getInt(sc,
					"Enter choice: ", 1, 2);
			System.out.println();
			
			switch(choice){
				case 1: continue;
				case 2: choice = -1; continue;
			}
		}
	}
}