import java.io.*;
import java.util.*;
class Student implements  Serializable
{
	int Student_rollno,Student_age;
	String Student_name,Student_emailid;
	long Student_phoneno;
	Student(int rollno,String name, int age,String email,long phno){
		this.Student_rollno = rollno;
		this.Student_name = name;
		this.Student_age = age;
		this.Student_emailid = email;
		this.Student_phoneno = phno;
	}
	public String toString() {
		return Student_rollno+" "+Student_name+" "+Student_age+" "+Student_emailid+" "+Student_phoneno;
	}
}
public class Student_file {
	public static void main(String args[])throws Exception
	{
		int ch = 1;
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		File file = new File("StudentDetailss.txt");
		ArrayList<Student> al = new ArrayList<Student>();
		ObjectOutputStream oos  = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		
		if(file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Student>)ois.readObject();
			ois.close();
		}
		do {
			System.out.println("1.Insert");
			System.out.println("2.Display");
			System.out.println("3.Delete");
			System.out.println("4.Exit");
			System.out.println("Enter the choice: ");
			ch = s.nextInt();
			
			switch(ch) {
			case 1:
			{
				System.out.print("Enter how many Student detail want : ");
				int n = s.nextInt();
				for(int i=0;i<n;i++) {
				System.out.print("Enter Student roll no: ");
				int Student_rollno = s.nextInt();
				System.out.print("Enter Student name: ");
				String Student_name = s1.nextLine();
				System.out.print("Enter Student age: ");
				int Student_age = s.nextInt();
				System.out.println("Enter Student Email id: ");
				String Student_emailid = s1.nextLine();
				System.out.println("Enter Student phone number: ");
				long Student_phoneno = s2.nextLong();
				
				al.add(new Student(Student_rollno,Student_name,Student_age,Student_emailid,Student_phoneno));
				}
				oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(al);
				oos.close();
				break;
			}
			case 2:
			{
				if(file.isFile())
				{
					ois = new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<Student>)ois.readObject();
					ois.close();
					System.out.println("----------------------------------");
					li = al.listIterator();
					while(li.hasNext())
						System.out.println(li.next());
					System.out.println("----------------------------------");
				}
				else
				{
					System.out.println("The file not Exits..!");
				}
				break;
			}
			
			case 3:
			{
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Student>)ois.readObject();
				ois.close();
				if(file.isFile()) {
				boolean found = false;
				System.out.println("Enter the Student number to Delete: ");
				int Student_rollno = s.nextInt();
				li = al.listIterator();
				while(li.hasNext())
				{
					Student st = (Student)li.next();
					if(st.Student_rollno ==  Student_rollno)
					{
						System.out.println(st);
						li.remove();
						found = true;
					}
					
				}
				if(found) {
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
					System.out.println("Record Deleted Successfully...!");
				}
					
				else
				{
					System.out.println("Record not found...!");
				}
					
				System.out.println("-----------------------------------");
				}
				else
				{
					System.out.println("The filenot exits..!");
				}
				break;
			}
			}
		}while(ch!=4);
}
}

