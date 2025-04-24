package com.openclassrooms.mdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.mdd.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByEmail(String email);
  List<User> findByUsername(String username);
  List<User> findByUsernameOrEmail(String username, String password);

}
