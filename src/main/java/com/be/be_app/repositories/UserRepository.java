package com.be.be_app.repositories;

import com.be.be_app.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
}
