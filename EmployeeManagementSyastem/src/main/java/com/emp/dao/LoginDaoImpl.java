package com.emp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.emp.entity.User;

@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired(required=true)
	MongoTemplate mongoTemplate;
	
    private static String Collection_Name ="user";

	 @Override
		public User userLogin(String emailId, String password) {
			// TODO Auto-generated method stub
//			System.out.println("test user name : " + emailId);
			try {
				Query query = new Query();
				User user = mongoTemplate.findOne(query.addCriteria(
						Criteria.where("emailId").is(emailId)), User.class);
//				System.out.println("test adminDetail : " + user);
				
				if( user != null)
				{
//					System.out.println("contorller password: " + password);
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					if(bCryptPasswordEncoder.matches( password, user.getPassword()))
					{
						
//						System.out.println("Login");
						return user;
					}
					else
					{
//						System.out.println("not login");
						return null;
					}
					
				}
				else
				{
//				System.out.println("admin null");
					return null;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				
				return null;
			}
			

		}

}
