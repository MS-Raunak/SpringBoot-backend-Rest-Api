package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
