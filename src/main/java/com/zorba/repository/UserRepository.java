package com.zorba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zorba.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
