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
import com.emp.entity.User;

@Repository
public class UserDaoImplementation implements UserDao{

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
    JavaMailSender mailSender;

	 private static String Collection_Name ="user";
	
	 
	@Override
	public Boolean createAdminUser() 
	{
		// TODO Auto-generated method stub
			boolean output= false;
			
			User user = new User();
			
			if(!mongoTemplate.collectionExists(User.class))
			{
				mongoTemplate.createCollection(User.class);
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
				User admin2 = mongoTemplate.findOne(query.addCriteria(
				Criteria.where("userName").is("superadmin@gmail.com")), User.class);
				System.out.println("test adminDetail : " + admin2);
			
				if(admin2 == null)
					{ 
					
						User user = new User();	
						System.out.println("Insert Data ");
						user.setUserId(UUID.randomUUID().toString());
						user.setRole("superAdmin");
						user.setEmailId("superadmin@gmail.com");
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
		Query query = new Query(Criteria.where("adminId").is(userId));
		
		System.out.println("test " + query);
 
     // Return user object.
        return   mongoTemplate.findOne(query, User.class,Collection_Name);
 
	}
	
	@Override
	public boolean update(String userId,User user)
	{
		boolean status=false;
		try {
			 status=true;  
			 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		        
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				
				mongoTemplate.save(user, Collection_Name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;  
		
	}

	
	@Override
	public User adminLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		System.out.println("test user name : " + emailId);
		try {
			Query query = new Query();
			User user = mongoTemplate.findOne(query.addCriteria(
					Criteria.where("emailId").is(emailId)), User.class);
			System.out.println("test adminDetail : " + user);
			
			if( user != null)
			{
				System.out.println("contorller password: " + password);
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if(bCryptPasswordEncoder.matches( password, user.getPassword()))
				{
					
					System.out.println("Login");
					return user;
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




}
