
import java.util.*;
import java.io.*;
import javax.xml.stream.*;  // StAX API

public class StudentXML implements StudentDAO {
	
	private String fileName = "StudentList.xml";
	private String fileQuestion = "QuestionList.xml";
	private File listFile = null;

	public StudentXML() {
		listFile = new File(fileName);
    }

    private void listExists() throws IOException{
        // if the file doesn't exist, create it
        if (!listFile.exists()) {
            listFile.createNewFile();
		}
    }	
	
	private boolean saveList(ArrayList<Student> list) {
        // create the XMLOutputFactory object
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        try{ // check the file to make sure it exists
            this.listExists();

            // create XMLStreamWriter object
            FileWriter fileWriter
                    = new FileWriter(fileName);
            XMLStreamWriter writer
                    = outputFactory.createXMLStreamWriter(fileWriter);

            //write the student list to the xml file
            writer.writeStartDocument("1.0");
            writer.writeStartElement("StudentList");
            for (Student std : list) {
				writer.writeStartElement("Student");
                writer.writeAttribute("UserName", std.getUserName());
				writer.writeAttribute("Password", std.getPassword());
				writer.writeAttribute("RegNum", std.getRegNum());

                writer.writeStartElement("FullName");
                writer.writeCharacters(std.getFullName());
                writer.writeEndElement();
				
				writer.writeStartElement("Age");
                writer.writeCharacters(std.getStringAge());
                writer.writeEndElement();
				
				writer.writeStartElement("Credits");
                writer.writeCharacters(std.getStringCredits());
                writer.writeEndElement();
				
				writer.writeStartElement("In-State");
                char inState = std.getInState();
                writer.writeCharacters(Character.toString(inState));
                writer.writeEndElement();
				
				writer.writeStartElement("Balance");
                writer.writeCharacters(std.getStringBalance());
                writer.writeEndElement();
				
				writer.writeStartElement("Payment");
                writer.writeCharacters(std.getStringPayment());
                writer.writeEndElement();
				
				writer.writeEndElement();
            }
			writer.writeEndElement();
            writer.flush();
            writer.close();
        } catch (IOException e) {
			saveList(list);
            return false;
        } catch (XMLStreamException e) {
			saveList(list);
            return false;
        }
        return true;
    }

	public ArrayList<Student> getList() {
        ArrayList<Student> list = new ArrayList<Student>();
		Student std = null;

        // create the XMLInputFactory object
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            // check the file to make sure it exists
            this.listExists();

            // create a XMLStreamReader object
            FileReader fileReader
                    = new FileReader(fileName);
            XMLStreamReader reader
                    = inputFactory.createXMLStreamReader(fileReader);

            // read the student list from the xml file
            while (reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
						if (elementName.equals("Student")) {
							std = new Student();
 
                            String userName = reader.getAttributeValue(0);
                            std.setUserName(userName);
							String password = reader.getAttributeValue(1);
							std.setPassword(password);
							String regNum = reader.getAttributeValue(2);
							std.setRegNum(regNum);
                        }
						
						if (elementName.equals("FullName")) {
                            String fullName = reader.getElementText();
                            std.setFullName(fullName);
                        }
						
						if (elementName.equals("Age")) {
                            String number = reader.getElementText();
                            int age = Integer.parseInt(number);
                            std.setAge(age);
                        }
						
						if (elementName.equals("Credits")) {
                            String number = reader.getElementText();
                            int credits = Integer.parseInt(number);
                            std.setCredits(credits);
                        }
						
						if (elementName.equals("In-State")) {
                            String str = reader.getElementText();
							char inState = str.charAt(0);
                            std.setInState(inState);
                        }

						if (elementName.equals("Balance")) {
                            String number = reader.getElementText();
                            double balance = Double.parseDouble(number);
                            std.setBalance(balance);
                        }
						
                        if (elementName.equals("Payment")) {
                            String number = reader.getElementText();
                            double payment = Double.parseDouble(number);
                            std.setPaymentXML(payment);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if (elementName.equals("Student")) {
                            list.add(std);
                        }
                        break;
                    default:
                        break;
                }
                reader.next();
            }
        } catch (IOException e) {
			saveList(list);
            return null;
        } catch (XMLStreamException e) {
			saveList(list);
            return null;
        }
        return list;
    }
	
	public Student getStudent(String regNum) {   // get a sinlge student info using its regNum
        ArrayList<Student> list = this.getList();
        for (Student user : list) {
            if (user.getRegNum().equals(regNum)) {
                return user;
            }
        }
        return null;
    }
	
	public Student getStudent(String userName, String password) {  // use to log in
        ArrayList<Student> list = this.getList();
        for (Student user : list) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
	
	public boolean include(Student user) {   // add a new student to the list
        ArrayList<Student> list = this.getList();
        list.add(user);
        return this.saveList(list);
    }
	
	public boolean remove(Student user) {   // remove a student from the list
        ArrayList<Student> list = this.getList();
        list.remove(user);
        return this.saveList(list);
    }

    public boolean update(Student user2) {    // update a single student info
        ArrayList<Student> list = this.getList();

        // get the old product and remove it
        Student user1 = this.getStudent(user2.getRegNum());
        int i = list.indexOf(user1);
        list.remove(i);

        // add the updated product
		list.add(i, user2);

        return this.saveList(list);
    }
	
	public boolean deleteAll(){      // delete all student records in the list
		ArrayList<Student> list = this.getList();

		list.clear();
		
		return this.saveList(list);
	}
}