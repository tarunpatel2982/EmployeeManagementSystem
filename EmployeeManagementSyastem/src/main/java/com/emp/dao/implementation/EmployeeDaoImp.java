package com.emp.dao.implementation;

import java.util.List;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.emp.dao.interfaces.EmployeeDao;
import com.emp.entity.Admin;
import com.emp.entity.Employee;

@Repository
public class EmployeeDaoImp  implements EmployeeDao{

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	 private static String Collection_Name ="employee";

	@Override
	public Boolean addEmployee(Employee employee1) 
	{
		// TODO Auto-generated method stub
		boolean output= false;
		
		
			Employee employee;
			Query query = new Query();
			employee = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("emailId").is(employee1.getEmailId())), Employee.class);
			
			System.out.println("test adminDetail : " + employee);
			System.out.println( "test status " + employee);
			if(employee == null)
				{
					employee1.setEmployeeId(UUID.randomUUID().toString());
					employee1.setRole("employee");
					employee1.setPassword(UUID.randomUUID().toString());
					System.out.println("get password " + employee1.getPassword());
				
					sendEmailEmployee(employee1);
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					employee1.setPassword(bCryptPasswordEncoder.encode(employee1.getPassword()));
					
					mongoTemplate.insert(employee1, Collection_Name);
				
					output=true;
				}
				else
				{
					System.out.println( " already Have Account");
					output = false;
				}
			
		return output;

	}

	@Override
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		return (List<Employee>) mongoTemplate.findAll(Employee.class,"employee");
	}

	@Override
	public Employee findEmployeeId(String employeeId)
	{
		Query query = new Query(Criteria.where("employeeId").is(employeeId));
		
		System.out.println("test " + query);
 
     // Return user object.
        return   mongoTemplate.findOne(query, Employee.class,Collection_Name);
 
	}
	
	@Override
	public boolean updateEmployee(String employeeId,Employee employee)
	{
		boolean status=false;
		try {
			 status=true;  
			 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		        
			 employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
				
				mongoTemplate.save(employee, Collection_Name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;  
		
	}



	public void sendEmailEmployee(Object object) {
		 
        Employee employee = (Employee) object;
 
        MimeMessagePreparator preparator = getMessagePreparator(employee);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
    private MimeMessagePreparator getMessagePreparator(final Employee employee) {
    	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
//                mimeMessage.setFrom("customerserivces@yourshop.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(employee.getEmailId()));
                mimeMessage.setText("Hello : "+employee.getFirstName());
                mimeMessage.setText("  User Name :  " + employee.getEmailId() +"  And  Password : " + employee.getPassword() );
                mimeMessage.setSubject("Your user name And Password");
            }
        };
        return preparator;
    }


	
}
