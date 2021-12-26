package com.appsdeveloperblog.photoapp.api.users.data;

import org.springframework.data.repository.CrudRepository;

//The very first one is a data type of entity object that we need to store,
//the second one is a data type of the ID that that user entity has and the data type of database ID is long
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	//here is no ready to use method that finds a record by username, for example,or finds a record by email.
	//We will have to create that method
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

	//there is a special way how we name query methods in JPA
	//If I wanted to delete a record I will start with delete.
	//If I wanted to update a record in the database, I will start with an update.
	//If I want to select a record from a database, I need to start with find by .
	//It will be framework that will create an SQL query based on the way we name our method.
}
