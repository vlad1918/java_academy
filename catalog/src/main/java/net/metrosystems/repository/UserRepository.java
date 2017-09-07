package net.metrosystems.repository;

import org.springframework.data.repository.CrudRepository;

import net.metrosystems.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUserName(String userName);
	
}
