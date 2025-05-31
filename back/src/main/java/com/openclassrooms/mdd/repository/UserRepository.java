package com.openclassrooms.mdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.mdd.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
  List<User> findByUsername(String username);
  List<User> findByUsernameOrEmail(String username, String password);
  Optional<User> findById(Integer id);
}
