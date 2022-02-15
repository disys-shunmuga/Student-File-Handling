import java.io.*;
import java.util.*;
class Employee implements Serializable{
	int empo;
	String ename;
	int salary;
	Employee(int eno,String ename,int salary){
		this.empo = eno;
		this.ename = ename;
		this.salary = salary;
	}
	public String toString()
	{
		return empo+" "+ename+" "+salary;
	}
}
public class Student_details {
	public static void main(String args[])throws Exception
	{
		int ch = 1;
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		File file = new File("employee.txt");
		ArrayList<Employee> al = new ArrayList<Employee>();
		ObjectOutputStream oos  = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		
		if(file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Employee>)ois.readObject();
			ois.close();
		}
		do {
			System.out.println("1.Insert");
			System.out.println("2.Display");
			System.out.println("3.Search");
			System.out.println("4.Delete");
			System.out.println("5.Update");
			System.out.println("6.Sort");
			System.out.println("0.Exit");
			System.out.println("Enter the choice: ");
			ch = s.nextInt();
			
			switch(ch) {
			case 1:
			{
				System.out.print("Enter how many employee you want : ");
				int n = s.nextInt();
				for(int i=0;i<n;i++) {
				System.out.print("Enter Employee no: ");
				int empo = s.nextInt();
				System.out.print("Enter Employee name: ");
				String ename = s1.nextLine();
				System.out.print("Enter Employee Salary: ");
				int salary = s.nextInt();
				
				al.add(new Employee(empo,ename,salary));
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
					al = (ArrayList<Employee>)ois.readObject();
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
				al = (ArrayList<Employee>)ois.readObject();
				ois.close();
				if(file.isFile()) {
				boolean found = false;
				System.out.println("Enter the employee number to search: ");
				int empo = s.nextInt();
				li = al.listIterator();
				while(li.hasNext())
				{
					Employee e = (Employee)li.next();
					if(e.empo ==  empo)
					{
						System.out.println(e);
						found = true;
					}
					
				}
				if(!found) {
					System.out.println("Record not found...!");
				}
					
				System.out.println("----------------------------------");
				}
				else
				{
					System.out.println("The filenot exits..!");
				}
				break;
			}
			case 4:
			{
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Employee>)ois.readObject();
				ois.close();
				if(file.isFile()) {
				boolean found = false;
				System.out.println("Enter the employee number to Delete: ");
				int empo = s.nextInt();
				li = al.listIterator();
				while(li.hasNext())
				{
					Employee e = (Employee)li.next();
					if(e.empo ==  empo)
					{
						System.out.println(e);
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
					
				System.out.println("----------------------------------");
				}
				else
				{
					System.out.println("The filenot exits..!");
				}
				break;
			}
			case 5:
			{
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Employee>)ois.readObject();
				ois.close();
				if(file.isFile()) {
				boolean found = false;
				System.out.print("Enter the employee number to update: ");
				int empo = s.nextInt();
				li = al.listIterator();
				while(li.hasNext())
				{
					Employee e = (Employee)li.next();
					if(e.empo ==  empo)
					{
						System.out.print("Enter New employee Name : ");
						String ename = s1.nextLine();
						System.out.print("Enter the salary : ");
						int salary = s.nextInt();
						li.set(new Employee(empo,ename,salary));
						found = true;
					}
					
				}
				if(found) {
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
					System.out.println("Record updated Successfully...!");
				}
					
				else
				{
					System.out.println("Record not found...!");
				}
					
				System.out.println("----------------------------------");
				}
				else
				{
					System.out.println("The filenot exits..!");
				}
				break;
			}
			case 6:
			{
				if(file.isFile())
				{
					ois = new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<Employee>)ois.readObject();
					ois.close();
					
					Collections.sort(al,new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2) {
							return e1.empo - e2.empo;
						}
					});
					
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
					
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
			}
		}while(ch!=0);
	}
}


