package mdn.ayman.MarineRecord.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mdn.ayman.MarineRecord.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
}