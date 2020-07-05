package com.emp.dao;

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

import com.emp.entity.User;

@Repository
public class SuperAdminDaoImpl implements SuperAdminDao {

	
	@Autowired(required=true)
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	 private static String Collection_Name ="user";

	 @Override
	 public Boolean createSuperAdminUser() 
		{
			// TODO Auto-generated method stub
				boolean output= false;
				
				User user = new User();
				
				if(!mongoTemplate.collectionExists(User.class))
				{
					mongoTemplate.createCollection(User.class);
//					System.out.println("Cretae collection ");
					output = true;
					ins(output);
				}
				else
				{
//					System.out.println("already create cl");
//					ins(output);
				}
			
				return output;
		
		}
		
		public void ins(boolean output)
		{
//			System.out.println("call ins() method");
			if ( output == true)
				{
					Query query = new Query();
					User admin2 = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("userName").is("taruntest0001@gmail.com")), User.class);
//					System.out.println("test adminDetail : " + admin2);
				
					if(admin2 == null)
						{ 
						
							User user = new User();	
							System.out.println("Insert Data ");
							user.setUserId(UUID.randomUUID().toString());
							user.setRole("superAdmin");
							user.setEmailId("taruntest0001@gmail.com");
							BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				        
							user.setPassword(bCryptPasswordEncoder.encode("admin"));
							mongoTemplate.insert(user, Collection_Name);
							output=true;
						}
						else
						{
							System.out.println("already created ");
						}
				
				}

		 }
		@Override
		public User findUserId(String userId)
		{
			Query query = new Query(Criteria.where("userId").is(userId));
			
			System.out.println("test123 " + query);
	 
	     // Return user object.
	        return   mongoTemplate.findOne(query, User.class,"user");
	 
		}

		@Override
		public Boolean changeUserPassword(String userId,User user) {
			// TODO Auto-generated method stub
			boolean status=false;
			try {
//				 System.out.println("test password  " + user.getPassword());
//				 System.out.println("test userId " + userId);
				 user.setPassword(user.getPassword());
//				 sendEmailAdmin(user);
				 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			        
				 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					
					mongoTemplate.save(user, Collection_Name);
					status=true;  
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return status;  

		}
		
		

		public void sendEmailAdmin(Object object) {
			 
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
	                mimeMessage.setSubject("Your  Password is Changed");
	            }
	        };
	        return preparator;
	    }



}
