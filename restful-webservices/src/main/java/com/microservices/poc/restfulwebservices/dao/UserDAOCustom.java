/**
 * 
 */
package com.microservices.poc.restfulwebservices.dao;

import com.microservices.poc.restfulwebservices.request.User;

/**
 * @author aritnag
 *
 */
public interface UserDAOCustom {

	com.microservices.poc.restfulwebservices.entity.User fetchUser(Integer id);

	long updateUser(String name, User user);

	void addUser(User user);

	long deleteUser(String name);

	void deleteUserById(Integer id);

}
