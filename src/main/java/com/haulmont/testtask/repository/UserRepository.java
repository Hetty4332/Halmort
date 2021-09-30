package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends JpaRepository<User, Long>{
        User findByUsername(String username);
}
