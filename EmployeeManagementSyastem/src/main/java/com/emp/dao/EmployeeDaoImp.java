package com.emp.dao;

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

import com.emp.entity.EmployeeWorkDetail;
import com.emp.entity.User;


@Repository
public class EmployeeDaoImp implements EmployeeDao {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	 private static String Collection_Name ="user";
	
	 @Override
	public Boolean addEmployee(User user) 
	{
		// TODO Auto-generated method stub
		boolean output= false;
		
		
		
//		 System.out.println("test date  : " + user.getFirstName());
		
			Query query = new Query();
			User user1 = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("emailId").is(user.getEmailId())), User.class);
			
//			System.out.println("test adminDetail : " + user);
			if(user1 == null)
				{
					
					String empId = UUID.randomUUID().toString();
					user.setUserId(empId);
					user.setRole("employee");
					String pass = UUID.randomUUID().toString();
					user.setPassword(pass);
					
					System.out.println("get password " + user.getPassword());
				//send mail for user
//					sendEmailEmployee(user);
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					
					mongoTemplate.insert(user, Collection_Name);
									output=true;
				}
				else
				{
//					System.out.println( " already Have Account");
					output = false;
				}
			
		return output;

	}
	 @Override
	public List<User> getEmployeeList()
	{
 		Query query = new Query();
		query.addCriteria(
		Criteria.where("role").is("employee"));
		
		List<User> result = mongoTemplate.find(query, User.class);
//		System.out.println("test :" + result);
		return result;
	}
	 @Override
	public Boolean addWork(EmployeeWorkDetail  employeeWorkDetail)
	{
		boolean status = false;
		try {
			
			mongoTemplate.insert( employeeWorkDetail , "employeeWorkDetail");
			status = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
	
	 @Override
	public List<EmployeeWorkDetail> getWorkDetail()
	{
		
     // Return user object.
        return   mongoTemplate.findAll(EmployeeWorkDetail.class,"employeeWorkDetail");
 
	}
	 @Override
	public List<EmployeeWorkDetail> getWorkDetailForAdmin()
	{
     // Return user object.
        return   mongoTemplate.findAll( EmployeeWorkDetail.class,"employeeWorkDetail");
 
	}
	 
		
		public void sendEmailEmployee(Object object) {
			 
	        User user = (User) object;
	 
	        MimeMessagePreparator preparator = getMessagePreparator(user);
	 
	        try {
	            mailSender.send(preparator);
	        } catch (MailException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
		
	    private MimeMessagePreparator getMessagePreparator(final User user) {
	    	 
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 
	            public void prepare(MimeMessage mimeMessage) throws Exception {
//	        
	                mimeMessage.setRecipient(Message.RecipientType.TO,
	                        new InternetAddress(user.getEmailId()));
	                mimeMessage.setText("User Name " + user.getEmailId() +" Password " + user.getPassword() );
	                mimeMessage.setSubject("Your user name And Password");
	            }
	        };
	        return preparator;
	    }
}
