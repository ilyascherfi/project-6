package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  List<Subscription> findByUserId(Long id);
}
