package mdn.ayman.MarineRecord.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mdn.ayman.MarineRecord.model.User;
import mdn.ayman.MarineRecord.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

	public Object findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
}
