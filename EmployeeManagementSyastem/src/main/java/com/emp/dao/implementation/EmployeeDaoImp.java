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
import com.emp.entity.EmployeeDetail;
import com.emp.entity.User;


@Repository
public class EmployeeDaoImp  implements EmployeeDao{

	
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
		
		
		
			
			Query query = new Query();
			User user1 = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("emailId").is(user.getEmailId())), User.class);
			
			System.out.println("test adminDetail : " + user);
			if(user1 == null)
				{
					
					String empId = UUID.randomUUID().toString();
					user.setUserId(empId);
					user.setRole("employee");
					String pass = UUID.randomUUID().toString();
					user.setPassword(pass);
					
					System.out.println("get password " + user.getPassword());
				
//					sendEmailEmployee(employee1);
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					
					mongoTemplate.insert(user, Collection_Name);
									output=true;
				}
				else
				{
					System.out.println( " already Have Account");
					output = false;
				}
			
		return output;

	}

	public List<User> getEmployeeList()
	{
 		Query query = new Query();
		query.addCriteria(
		Criteria.where("role").is("admin"));
		
		List<User> result = mongoTemplate.find(query, User.class);
		System.out.println("test :" + result);
		return result;
	}

	@Override
	public User findEmployeeId(String employeeId)
	{
		Query query = new Query(Criteria.where("employeeId").is(employeeId));
		
		System.out.println("test " + query);
 
     // Return user object.
        return   mongoTemplate.findOne(query, User.class,Collection_Name);
 
	}
	
	@Override
	public boolean updateEmployee(String employeeId,EmployeeDetail employeeDetail)
	{
		boolean status=false;
		try {
			 status=true;  
			 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		        
			 employeeDetail.setPassword(bCryptPasswordEncoder.encode(employeeDetail.getPassword()));
				
				mongoTemplate.save(employeeDetail, Collection_Name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;  
		
	}



	public void sendEmailEmployee(Object object) {
		 
        EmployeeDetail employeeDetail = (EmployeeDetail) object;
 
        MimeMessagePreparator preparator = getMessagePreparator(employeeDetail);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
    private MimeMessagePreparator getMessagePreparator(final EmployeeDetail employeeDetail) {
    	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
//                mimeMessage.setFrom("customerserivces@yourshop.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(employeeDetail.getEmailId()));
                mimeMessage.setText("Hello : "+employeeDetail.getFirstName());
                mimeMessage.setText("  User Name :  " + employeeDetail.getEmailId() +"  And  Password : " + employeeDetail.getPassword() );
                mimeMessage.setSubject("Your user name And Password");
            }
        };
        return preparator;
    }


	
}
