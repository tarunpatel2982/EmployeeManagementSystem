package com.emp.dao.implementation;

import java.util.UUID;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.emp.dao.interfaces.UserDao;
import com.emp.entity.Admin;

@Repository
public class UserDaoImplementation implements UserDao{

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	 private static String Collection_Name ="admin";
	
	 
	@Override
	public Boolean createAdminUser() 
	{
		// TODO Auto-generated method stub
			boolean output= false;
			
			Admin admin = new Admin();
			
			if(!mongoTemplate.collectionExists(Admin.class))
			{
				mongoTemplate.createCollection(Admin.class);
				System.out.println("Cretae collection ");
				output = true;
				ins(output);
			}
			else
			{
				System.out.println("already create cl");
//				ins(output);
			}
		
			return output;
	
	}
	
	public void ins(boolean output)
	{
		System.out.println("call ins() method");
		if ( output == true)
			{
				Query query = new Query();
				Admin admin2 = mongoTemplate.findOne(query.addCriteria(
				Criteria.where("userName").is("superadmin@gmail.com")), Admin.class);
				System.out.println("test adminDetail : " + admin2);
			
				if(admin2 == null)
					{ 
					
						Admin admin = new Admin();	
						System.out.println("Insert Data ");
						admin.setAdminId(UUID.randomUUID().toString());
						admin.setRole("superAdmin");
						admin.setEmailId("superadmin@gmail.com");
						BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			        
						admin.setPassword(bCryptPasswordEncoder.encode("admin"));
						mongoTemplate.insert(admin, Collection_Name);
						output=true;
					}
					else
					{
						System.out.println("already created ");
					}
			
			}

	 }
	@Override
	public Admin findUserId(String adminId)
	{
		Query query = new Query(Criteria.where("adminId").is(adminId));
		
		System.out.println("test " + query);
 
     // Return user object.
        return   mongoTemplate.findOne(query, Admin.class,Collection_Name);
 
	}
	
	@Override
	public boolean update(String adminId,Admin admin)
	{
		boolean status=false;
		try {
			 status=true;  
			 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		        
				admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
				
				mongoTemplate.save(admin, Collection_Name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;  
		
	}

	@Override
	public Boolean addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		boolean output = false;
		Admin status;
		try {
			
			Query query = new Query();
			status = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("emailId").is(admin.getEmailId())), Admin.class);
			
			System.out.println("test adminDetail : " + admin);
			System.out.println( "test status " + status);
			if(status == null)
			{
				admin.setAdminId(UUID.randomUUID().toString());
				admin.setRole("admin");
				admin.setPassword(UUID.randomUUID().toString());
				
				sendEmailAdmin(admin);
				
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				
				admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
				mongoTemplate.insert(admin, Collection_Name);
				
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

	@Override
	public String adminLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		System.out.println("test user name : " + emailId);
		try {
			Query query = new Query();
			Admin admin = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("emailId").is(emailId)), Admin.class);
			System.out.println("test adminDetail : " + admin);
			
			if( admin != null)
			{
				System.out.println("contorller password: " + password);
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if(bCryptPasswordEncoder.matches( password, admin.getPassword()))
				{
					
					System.out.println("Login");
					return admin.getAdminId();
				}
				else
				{
					System.out.println("not login");
					return null;
				}
				
			}
			else
			{
			System.out.println("admin null");
				return null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return null;
		}
		

	}


	public void sendEmailAdmin(Object object) {
		 
        Admin admin = (Admin) object;
 
        MimeMessagePreparator preparator = getMessagePreparator(admin);
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
	
    private MimeMessagePreparator getMessagePreparator(final Admin admin) {
    	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
//                mimeMessage.setFrom("customerserivces@yourshop.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(admin.getEmailId()));
                mimeMessage.setText("User Name " + admin.getEmailId() +" Password " + admin.getPassword() );
                mimeMessage.setSubject("Your user name And Password");
            }
        };
        return preparator;
    }



}
