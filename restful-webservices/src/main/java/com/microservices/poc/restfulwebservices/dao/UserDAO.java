package com.microservices.poc.restfulwebservices.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.microservices.poc.restfulwebservices.entity.User;

public interface UserDAO extends MongoRepository<User, Integer> {
	
	User findFirstByName(String name);

	User findByNameAndBirthDate(String name, Date birthDate);

    //Supports native JSON query string
    @Query("{name:'?0'}")
    User findCustomByName(String name);

    @Query("{name: { $regex: ?0 } })")
    List<User> findCustomByRegExName(String name);
    
    @Query("{_id:'?0'}")
    User findOne(Integer id);
}
