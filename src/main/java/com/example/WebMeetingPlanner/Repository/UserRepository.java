package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  UserRepository extends JpaRepository<User, Long>{


    @Query("SELECT u FROM User u WHERE u.email= ?1")
    User findByEmail(String email);

    public User findByResetPasswordToken(String token);

    User findBySetPasswordToken(String tokens);

//    @Modifying
//    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
//
//    int updateFailedAttempt(int failedAttempt, String email);

    User getUserByEmail(String email);

//    void updateFailedAttempts(int newFailAttempts, String email);
//    updateFailedAttempts(int newFailAttempts, String email);
    
//   void updateFailedAttempts(int failAttempt, String email);




//    @Modifying
//    @Transactional
//    @Query("UPDATE User u SET u.accountNonLocked = ?1 WHERE u.accountNonLocked = ?2")
//    public void updateAllUsers(int accountNonLocked, String email);

}
