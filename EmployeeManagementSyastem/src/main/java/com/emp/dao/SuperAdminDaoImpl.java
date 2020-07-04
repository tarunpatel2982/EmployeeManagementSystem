package com.emp.dao;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.javamail.JavaMailSender;
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
					System.out.println("Cretae collection ");
					output = true;
					ins(output);
				}
				else
				{
					System.out.println("already create cl");
//					ins(output);
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

}
