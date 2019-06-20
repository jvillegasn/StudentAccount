
import java.text.NumberFormat;
import java.util.Scanner;

// class of main object that will be used in the program
// constructors, setter and getter, other methods

public class Student{
	private String userName;
	private String password;
	private String regNum;
	private String fullName;
	private int age;
	private int credits;
	private double balance;
	private double payment;
	private char inState;
	
	final private double OUT_STATE = 418.54;
	final private double IN_STATE = 156.19;
	
	public Student(){
		this("", "", "", "", 0, 0, ' ', 0.0);
	}
	
	public Student(String userName, String passwor){
		this.userName = userName;
		this.password = password;
	}
	
	public Student(String userName, String password, String regNum, String fullName, 
					int age, int credits, char inState, double balance){
		this.userName = userName;
		this.password = password;
		this.regNum = regNum;
		this.fullName = fullName;
		this.age = age;
		this.credits = credits;
		this.inState = inState;
		this.balance = balance;
		this.setPayment();
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName() { return userName; }
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){ return password; }
	
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	
	public String getRegNum() { return regNum; }
	
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	
	public String getFullName(){ return fullName; }
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() { return age; }
	
	public String getStringAge() { return Integer.toString(age); }
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int getCredits() { return credits; }
	
	public String getStringCredits() { return Integer.toString(credits); }
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addMoney(double in){
		this.balance += in;
	}
		
	public double getBalance() { return balance; }
	
	public String getStringBalance() { return Double.toString(balance); }
	
	public void setPayment() {
		if(inState == 'y')
			this.payment = credits*IN_STATE;
		else if(inState == 'n')
			this.payment = credits*OUT_STATE;
	}
	
	public void setPaymentXML(double payment) {
		this.payment = payment;
	}
	
	public void makePayment(double pay){
		
		if(pay > balance){
			System.out.println("\t-Error! Not enough funds...");
			return;
		}
		
		if(pay > payment){
			System.out.println("\t-More than enough money was paid! Refund of " 
								+ getStringCurrency(pay-payment) + " was made");
			pay = payment;
		}
		
			this.balance -= pay;
			this.payment -= pay;
	}
	
	public double getPayment() { return payment; }
	
	public String getStringPayment() { return Double.toString(payment); }
	
	public String getStringCurrency(double x) {
		NumberFormat str = NumberFormat.getCurrencyInstance();
        return str.format(x);
	} 
	
	public void  setInState(char inState) {
		this.inState = inState;
	}
	
	public char getInState() { return inState; }
	
	public boolean equals(Object object) {
        if (object instanceof Student) {
			Student x2 = (Student) object;
            if ( regNum.equals(x2.getRegNum())){
                return true;
            }
        }
        return false;
    }
	
    public String toString() {
 		String output = "UserName:    " + getUserName() + "\n" +
						"Password:    " + getPassword() + "\n" +
						"Reg Num:     " + getRegNum() + "\n" +
						"Full Name:       " + getFullName() + "\n" +
						"Age:     " + getStringAge() + "\n" +
						"Credits:     " + getStringCredits() + "\n" +
						"In-State:   " + getInState() + "\n" +
						"Balance:     " + getStringCurrency(balance) + "\n" +
						"Payment:   " + getStringCurrency(payment) + "\n";
						
		return output;
    }
		
}
