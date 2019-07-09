package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName ='Chu1' ").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Chu1");
			displayStudents(theStudents);
			
			// query students: lastName='Chu2' OR firstName='hojin3'
			theStudents =
					session.createQuery("from Student s where"
							+" s.lastName='Chu2' OR s.firstName='hojin3'").getResultList();
	
			System.out.println("\n\nStudents who have last name of Chu2 OR first name hojin3");
			displayStudents(theStudents);			
			
			
			// query students where email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%gmail.com'").getResultList();
			
			System.out.println("\n\nStudents who email ends with gmail.com");
			displayStudents(theStudents);
			
			
			
			
			// commit tranction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}








































