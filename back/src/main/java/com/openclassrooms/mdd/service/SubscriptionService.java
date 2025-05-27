package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Subscription;
import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
  @Autowired
  private SubscriptionRepository subscriptionRepository;

  /**
   * Get all subscriptions for a user
   * @param user The user id
   * @return A list of Theme ids
   */
  public List<Theme> getSubscriptions(User user) {
    List<Subscription> subscriptions = subscriptionRepository.findByUserId(user.getId());
    List<Theme> Themes = new ArrayList<>();
    for (Subscription subscription : subscriptions) {
      Themes.add(subscription.getTheme());
    }
    return Themes;
  }
}
