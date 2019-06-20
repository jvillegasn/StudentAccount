
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

// methods to get correct input from the user

public class Validator {

    public static String getLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.nextLine();        // read the whole line
        return s;
    }

    public static String getString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String s = sc.next();        // read the first string on the line
        sc.nextLine();               // discard the rest of the line
        return s;
    }
	
	public static String getSubString(Scanner sc, String prompt, int size) {
		String s = getString(sc, prompt);       // read first string on the line
		
			if(s.length() > size){					// if string's length greater than required
													// return a substring up to the required size
				System.out.println("\t-Input is greater than required." + 
										"The String has been cut to the first " + size + " characters");
				return s.substring(0,size);
			}
			else if(s.length() < size){					// if the string's length less than required
														// complete string with 0's up to the requires size
				System.out.println("\t-Input is less than required." + 
										"The String will be completed with 0's ");
				StringBuilder sb = new StringBuilder();
				sb.append(StringUtils.padSpaces(s, size, "0"));
				return sb.toString();
			}
			else									// if string's length present's size required, return
				return s;
    }
	
	public static String getRegNum(StudentDAO stdDAO) { // gets a string of 4 randomly generated
				
				Random rand = new Random();
				String regNum = " ";
				int random = 0;
				StringBuilder sb = new StringBuilder();
				boolean ok = true; 
				ArrayList<Student> list = stdDAO.getList();
				
				for(int i = 0; i < 4; i++){
					random = rand.nextInt(10);
					sb.append(Integer.toString(random));
				}
				
				regNum = sb.toString();
				
				for(Student std : list){
					String temp = std.getRegNum();
					if(temp.equals(regNum)){
						System.out.println("Already Exists\n");
						return "exists";
					}
				}
				
				return regNum;			
    }

    public static int getInt(Scanner sc, String prompt) {    //get a valid integer value
        boolean isValid = false;
        int i = 0;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("\t-Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }
	
    public static int getInt(Scanner sc, String prompt,      // get a valid integer value between two numbers
            int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (isValid == false) {
            i = getInt(sc, prompt);
            if (i < min || i > max) {
                System.out.println(
                        "\t-Error! Enter a choice between " + min + " and " + max);
            } else {
                isValid = true;
            }
        }
        return i;
    }

    public static double getDouble(Scanner sc, String prompt) {    // get a valid double number
        boolean isValid = false;
        double d = 0;
        while (isValid == false) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else {
                System.out.println("\t-Error! Invalid decimal value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
    }

    public static double getDouble(Scanner sc, String prompt,     // get a valid number between two numbers
            double min, double max) {
        double d = 0;
        boolean isValid = false;
        while (isValid == false) {
            d = getDouble(sc, prompt);
            if (d <= min) {
                System.out.println(
                        "\t-Error! Number must be greater than " + min);
            } else if (d >= max) {
                System.out.println(
                        "\t-Error! Number must be less than " + max);
            } else {
                isValid = true;
            }
        }
        return d;
    }
	
	public static double getPositiveDouble(Scanner sc, String prompt) {  // get a positive value number
        double d = 0;														// equal or greater than 0
        boolean isValid = false;
        while (isValid == false) {
            d = getDouble(sc, prompt);
            if (d < 0) {
                System.out.println(
                        "\t-Error! Number must be greater than " + 0);
            } else {
                isValid = true;
            }
        }
        return d;
    }

    public static char getChar(Scanner sc, String prompt) {          // get y for yes or n for no
        char c = ' ';												// reject any other character input
        boolean isValid = false;
        while (isValid == false) {
            System.out.print(prompt);

            if (sc.hasNext()) {
                c = sc.findInLine(".").charAt(0);
                if (c == 'y' || c == 'n') {
                    isValid = true;
                } else {
                    System.out.println("\t-Error! Invalid decimal value. Try again.");
                }
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return c;
    }
	
	public static void pressContinue(Scanner sc){         // press any key to continue
	
		System.out.print("Press Enter to continue...");
        String s = sc.nextLine(); 
	}
}
