package com.spring.orm.springorm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.springorm.dao.studentDao;
import com.spring.orm.springorm.entities.Student;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        studentDao studentDao = (studentDao) context.getBean("studentDao", studentDao.class);
        
        /* Student student = new Student();
        student.setStudentId(102);
        student.setStudentName("Ashneer Grovar");
        student.setStudentCity("Delhi");
        int result = studentDao.insert(student);
        System.out.println("Student inserted successfully" +result);	*/
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        boolean go=true;
        while(go) {
    	   System.out.println("********* Welcome to Sping ORM Framework Project *********");
           System.out.println("Press 1 for add new Student");
           System.out.println("Press 2 for display all Sudents");
           System.out.println("Press 3 for get details of single student");
           System.out.println("Press 4 for delete Students");
           System.out.println("Press 5 for update the Students");
           System.out.println("Press 6 for exit");
           
           try
           {
        	   int input = Integer.parseInt(br.readLine());
        	   
        	   switch (input) 
        	{
			case 1:
				//add new student
				//taking inputs from user
				System.out.println("Enter user id");
				int studentId = Integer.parseInt(br.readLine());
				
				System.out.println("Enter user name");
				String studentName = br.readLine();
				
				System.out.println("Enter user city");
				String studentCity = br.readLine();
				
				//creating the object and setting the values
				Student s = new Student();
		        s.setStudentId(studentId);
		        s.setStudentName(studentName);
		        s.setStudentCity(studentCity);
		        
		        //saving student object to database by calling insert method of the student dao
		        int result = studentDao.insert(s);
		        System.out.println("Student added successfully\n");
		        
				break;
			case 2:
				//display the all studnets
				System.out.println("Details of the students");
				List<Student> allStudents = studentDao.getAllStudents();
				for(Student st: allStudents)
				{
					System.out.println("Id: "+st.getStudentId());
					System.out.println("Name: "+st.getStudentName());
					System.out.println("City: "+st.getStudentCity());
					System.out.println("___________________________________");
				}
				
				break;
			case 3:
				//get details of the single student
				System.out.println("Enter the user id");
				int stId = Integer.parseInt(br.readLine());
				
				Student student = studentDao.getStudent(stId);
				System.out.println("Id: "+student.getStudentId());
				System.out.println("Name: "+student.getStudentName());
				System.out.println("City: "+student.getStudentCity());
				System.out.println("___________________________________\n");
				
				break;
			case 4:
				//delete the students
				System.out.println("Enter the student id");
				int sId = Integer.parseInt(br.readLine());
				studentDao.deleteStudent(sId);
				System.out.println("Student deleted successfully\n");
				break;
			case 5:
				//update the students
				System.out.println("Enter the student id to be updated");
				int updatedId = Integer.parseInt(br.readLine());
				
				System.out.println("Press 1 for update the name");
				System.out.println("Press 2 for update the city\n");
				
				System.out.println("Enter your choise for what do you want to update?");
				int choice = Integer.parseInt(br.readLine());
				
				Student std1 = studentDao.getStudent(updatedId);
				
				String updatedName = std1.getStudentName();
				String updatedCity = std1.getStudentCity();
				
				switch (choice)
				{
				case 1:
					System.out.println("Enter the name to be updated");
					updatedName = br.readLine();
					//setting the student objects value
					std1 = new Student(updatedId, updatedName, updatedCity);
					System.out.println("Name updated successfully");
					break;

				case 2:
					System.out.println("Enter the city name to be updated");
					updatedCity = br.readLine();
					//setting the student objects value in another way
						std1.setStudentId(updatedId);
						std1.setStudentName(updatedName);
						std1.setStudentCity(updatedCity);
					System.out.println("City updated successfully\n");
					break;
				}
				
				studentDao.updateStudent(std1);
				System.out.println("Updated successfully\n");
				
				break;
			case 6:
				//exit
				go=false;
				break;
				
			}
           } catch(Exception e)
           		{
        	   		System.out.println("Invalid input try with another input");
        	   		System.out.println(e.getMessage());
           		}
           
       }
       
       System.out.println("Thank you for using my application");
       System.out.println("See you soon!!");
       
       
    }
}
