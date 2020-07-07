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
public class AdminDaoImpl implements AdminDao {

	@Autowired(required=true)
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	//Collection name
	 private static String Collection_Name ="user";
	 
	 
//	 Create New AdminUser 
	 @Override
	 public Boolean addAdmin(User user) {
		 
			// TODO Auto-generated method stub
			boolean output = false;
			
			
			User status;
			try {
				
				Query query = new Query();
				status = mongoTemplate.findOne(query.addCriteria(
						Criteria.where("emailId").is(user.getEmailId())), User.class);
				
//				System.out.println("test adminDetail : " + user);
//				System.out.println( "test status " + status);
	
//				Status is null then create new Admin User
				if(status == null)
				{
//					automatically create a userId
					user.setUserId(UUID.randomUUID().toString());
					
					user.setRole("admin");
//					automatically create a Password
					user.setPassword(UUID.randomUUID().toString());
					System.out.println("admin password : " + user.getPassword());
//					Send Email For User In this mail UserName And Password
//					sendEmailAdmin(user);
					
//					Encrypt password
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
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				output = false;
			}
			return output;
		}
	 
//	 get AdminUser List 
	 @Override
	 	public List<User> getAdminList()
		{
	 		Query query = new Query();
			query.addCriteria(
			Criteria.where("role").is("admin"));
			
			List<User> result = mongoTemplate.find(query, User.class);
//			System.out.println("test :" + result);
			return result;
		}
	 
//	 delete AdminUser 
	 @Override
	 	public Boolean deleteAdmin(String userId) {
			boolean status = false;
			
			// TODO Auto-generated method stub
			try {
				
				
				mongoTemplate.remove(new Query(Criteria.where("userId").is(userId)), User.class, "user");
				mongoTemplate.remove(new Query(Criteria.where("adminId").is(userId)), User.class, "user");
				
				status=true;
//				System.out.println("delete");
			} catch (Exception e) {
				// TODO: handle exception
				status = false;
				e.printStackTrace();
			}
			
			return status;
		}
		
//	Get Employee Work Detail 	
	 @Override
		public List<EmployeeWorkDetail> getWorkDetailForAdmin()
		{
	     // Return EmployeeWorkDetail object.
	        return   mongoTemplate.findAll( EmployeeWorkDetail.class,"employeeWorkDetail");
	 
		}
	 
//	 Find Employee WorkId 
	 @Override
		public EmployeeWorkDetail findWorkId(String workID)
		{
			Query query = new Query(Criteria.where("workID").is(workID));
			
			System.out.println("test123 01 " + query);
	 
	     // Return  EmployeeWorkDetail Object.
	        return   mongoTemplate.findOne(query, EmployeeWorkDetail.class,"employeeWorkDetail");
	 
		}
//	 Give EmployeeWork FeedBack
	 @Override
		public boolean employeeWorkFeedBack(EmployeeWorkDetail employeeWorkDetail) {
			// TODO Auto-generated method stub
		 
		 boolean status=false;
		 
		 try {
			
			 mongoTemplate.save(employeeWorkDetail,"employeeWorkDetail" );
			 status=true;
		} catch (Exception e) {
			// TODO: handle exceptione
			e.printStackTrace();
		}
			return status;
		}
//	 Send Email From AdminUser 
		public void sendEmailAdmin(Object object) {
			 
	        User user = (User) object;
	 
	        MimeMessagePreparator preparator = getMessagePreparator(user);
	 
	        try {
	            mailSender.send(preparator);
	           
	        } catch (MailException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
		
//		this Method Using set Message title ,etc.
	    private MimeMessagePreparator getMessagePreparator(final User user) {
	    	 
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	              
	                mimeMessage.setRecipient(Message.RecipientType.TO,
	                        new InternetAddress(user.getEmailId()));
	                mimeMessage.setText("User Name " + user.getEmailId() +" Password " + user.getPassword() );
	                mimeMessage.setSubject("Your user name And Password");
	            }
	        };
	        return preparator;
	    }
		


}
