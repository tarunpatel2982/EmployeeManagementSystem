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

import com.emp.entity.User;

@Repository
public class AdminDaoImpl {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	 private static String Collection_Name ="user";
	 
	 public Boolean addAdmin(User user) {
			// TODO Auto-generated method stub
			boolean output = false;
			User status;
			try {
				
				Query query = new Query();
				status = mongoTemplate.findOne(query.addCriteria(
						Criteria.where("emailId").is(user.getEmailId())), User.class);
				
				System.out.println("test adminDetail : " + user);
				System.out.println( "test status " + status);
				if(status == null)
				{
					user.setUserId(UUID.randomUUID().toString());
					user.setRole("admin");
					user.setPassword(UUID.randomUUID().toString());
					
//					sendEmailAdmin(user);
					
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

	 	public List<User> getAdminList()
		{
	 		Query query = new Query();
			query.addCriteria(
			Criteria.where("role").is("admin"));
			
			List<User> result = mongoTemplate.find(query, User.class);
			System.out.println("test :" + result);
			return result;
		}
	 
	 
		public void sendEmailAdmin(Object object) {
			 
	        User user = (User) object;
	 
	        MimeMessagePreparator preparator = getMessagePreparator(user);
	 
	        try {
	            mailSender.send(preparator);
	            System.out.println("Message Send...Hurrey");
	        } catch (MailException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
		
	    private MimeMessagePreparator getMessagePreparator(final User user) {
	    	 
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 
	            public void prepare(MimeMessage mimeMessage) throws Exception {
//	                mimeMessage.setFrom("customerserivces@yourshop.com");
	                mimeMessage.setRecipient(Message.RecipientType.TO,
	                        new InternetAddress(user.getEmailId()));
	                mimeMessage.setText("User Name " + user.getEmailId() +" Password " + user.getPassword() );
	                mimeMessage.setSubject("Your user name And Password");
	            }
	        };
	        return preparator;
	    }


}
