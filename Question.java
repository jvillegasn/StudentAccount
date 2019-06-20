
import java.util.ArrayList;

// class that includes methods to recover an Student account info

public class Question{
	private String que;
	private String ans;
	private int x;
	
	public Question(){
		this("", "");
	}
	
	public Question(String que, String ans){
		this.que = que;
		this.ans = ans;
	}
	
	public void setQue(String que){
		this.que = que;
	}
	
	public String getQue() { return que; }
	
	public void setAns(String ans){
		this.ans = ans;
	}
	
	public String getAns() {return ans; };
	
	public ArrayList<Question> setQuestions(){  // list of security questions
		ArrayList<Question> list = new ArrayList<Question>();
		
        Question q1 = new Question("Mix of blue and yellow gives: ", "green");
		Question q2 = new Question("The square root of (2*3+2)*2 is: ", "4");
		Question q3 = new Question("What was the color of Napoleon's white horse?: ", "white");
		Question q4 = new Question("Man's best friend is: ", "dog");
		Question q5 = new Question("The fifth power of 2, times 2 is: ", "64");
		Question q6 = new Question("Which is an spanish word? (hola, salut, hello): ", "hola");
		Question q7 = new Question("How many days does August have?: ", "31");
		Question q8 = new Question("10 divided by the third part of 15 is: ", "2");
		
		list.add(q1);
		list.add(q2);
		list.add(q3);
		list.add(q4);
		list.add(q5);
		list.add(q6);
		list.add(q7);
		list.add(q8);
		
		return list;
	}
}