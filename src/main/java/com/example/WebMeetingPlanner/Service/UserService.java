package com.example.WebMeetingPlanner.Service;

import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.RoleRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;
    public static final int MAX_FAILED_ATTEMPTS = 5;
    public static final int USER_PER_PAGE = 3;
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

    @Autowired
    private UserRepository repo;

    @Autowired
    RoleRepository roleRepo;


    public void registerDefaultUser(User user) {
        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }

    public void saveUserWithDefaultRole(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
          String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

//        user.setAccountNonLocked(true);
        user.setEnabled(true);
        Role roleuser = roleRepo.findByName("User");
        user.addRole(roleuser);

        userRepo.save(user);

    }

    public void Saave(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

         userRepo.save(user);
    }

    public List<User> listAll(){
        return userRepo.findAll();
    }

    public  User get(Long id){
        return userRepo.findById(id).get();
    }

    public List<Role> getRoles(){
        return roleRepo.findAll();
    }

//    @Autowired
//    private TestEntityManager entityManager;

//    public void increaseFailedAttempts(User user) {
////        int newFailAttempts = user.getFailedAttempt() + 1;
//        repo.updateFailedAttempt(user.getFailedAttempt() +1, user.getEmail());
//    }

//    public void resetFailedAttempts(String email) {
//        repo.updateFailedAttempts(0, email);
//    }
//
//    public void lock(User user) {
//        user.setAccountNonLocked(false);
//        user.setLockTime(new Date());
//
//        repo.save(user);
//    }

//    public boolean unlockWhenTimeExpired(User user) {
//        long lockTimeInMillis = user.getLockTime().getTime();
//        long currentTimeInMillis = System.currentTimeMillis();
//
//        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
//            user.setAccountNonLocked(true);
//            user.setLockTime(null);
//            user.setFailedAttempt(0);
//
//            repo.save(user);
//
//            return true;
//        }
//
//        return false;
//    }

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepo.save(user);
        } else {
            throw new UserNotFoundException("Could not find any User with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepo.findByResetPasswordToken(token);
    }



    public void updateResetPasswordTokens(String tokens, String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setSetPasswordToken(tokens);
            userRepo.save(user);
        } else {
            throw new UserNotFoundException("Could not find any User with the email " + email);
        }
    }

    public User getBySetPasswordTokens(String tokens) {
        return userRepo.findBySetPasswordToken(tokens);
    }


//    public  User getByEmail(String email)
//    {
//        return userRepo.updateFailedAttempts(0, email);
//    }
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

//        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setResetPasswordToken(null);
        userRepo.save(user);
    }

    public User getByEmail(String email) {
      return userRepo.getUserByEmail(email);
    }


//    public User getByEmail(String email) {
//        userRepo.updateFailedAttempts(0, email);
//
//    }

}
