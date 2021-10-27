package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Exceptions.UserNotFoundException;
import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Model.TracomRooms;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Model.meeting;
import com.example.WebMeetingPlanner.Repository.MeetingRepository;
import com.example.WebMeetingPlanner.Repository.RoomsRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.UserService;
import com.example.WebMeetingPlanner.Utilities.Utility;
import com.example.WebMeetingPlanner.Utilities.UtilityPassword;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository repo;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        userService.saveUserWithDefaultRole(user);


        return "register_success";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userService.get(id);
        List<Role> listRoles = userService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }



    @RequestMapping(value = "/users/save", method = { RequestMethod.GET, RequestMethod.POST })
    public String saveUser(User user){
        userService.Saave(user);
        return "index";
    }

    @GetMapping("/index")
    public String viewHomePage()
    {
        return "index";
    }


    @GetMapping("/manpage")
    public String viewAdminPage()
    {
        return "user_form";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model)
    {
        model.addAttribute("user",new User());
        return "signup_form";
    }

    @GetMapping("/meeting_form")
    public  String meetingForm(Model model)
    {
        model.addAttribute("meet", new meeting());

        return "meeting_form";
    }


    @GetMapping("/Addroom")
    public String AddRoom(Model model)
    {
        model.addAttribute("rooms", new TracomRooms());

        return "Addrooms";
    }


    @GetMapping("/process_register")
    public String Process(Model model)
    {
        List<User> listUsers=userService.listAll();
//        List<User> listUsers=repo.findAll();
        model.addAttribute("listUsers",listUsers);
        return "register_success";
    }



    @PostMapping("/register")
    public String SaveUser(User user)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedpassword=encoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
        user.setEnabled(true);

        repo.save(user);
        return "login";
    }

    @PostMapping("/Saveroom")
    public String RoomSave(TracomRooms tracomRooms)
    {
        roomsRepository.save(tracomRooms);
        return "savedrooms";
    }

    @GetMapping("/Saveroom")
    public String AddRooms(Model model)
    {
        List<TracomRooms> addrromm=roomsRepository.findAll();
        model.addAttribute("listrooms",addrromm);

        return "savedrooms";
    }

//    @PostMapping("/process_register")
//    public String processRegister(User user)
//    {
//        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//        String encodedpassword=encoder.encode(user.getPassword());
//        user.setPassword(encodedpassword);
//        repo.save(user);
//        return "register_success";
//    }

    @PostMapping("/meetingdetails")
    public  String meetingSave (meeting meett)
    {
        meetingRepository.save(meett);
        return "meetingdetails";
    }



    @GetMapping("/meetingdetails")
    public String Meeting(Model model)
    {
        List<meeting> listmeeting=meetingRepository.findAll();
        model.addAttribute("listmeeting",listmeeting);

        return "meetingdetails";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model)
    {
        List<User> listUsers=userService.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/add_users")
    public String addUser(Model model){

        model.addAttribute("user", new User());

        return "add_users";
    }
//
//    @PostMapping("/process")
//    public String addusers(User user)
//    {
//        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//        String encodedpassword=encoder.encode(user.getPassword());
//        user.setPassword(encodedpassword);
//        user.setEnabled(true);
//
//        repo.save(user);
//        return "add_users";
//    }

    @PostMapping("/process")
    public String adduser(User user)
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedpassword=encoder.encode(user.getPassword());
        user.setPassword(encodedpassword);
        user.setEnabled(true);

        repo.save(user);
        return "forget_password";
    }

    @GetMapping(value = "/login")
    public String login (){
        return "login";
    }


    @RequestMapping("")
    public class LoginController {

        // Login form with error
        @GetMapping("/error-login")
        public String loginError(Model model) {
            model.addAttribute("loginError", true);
            return "login";
        }
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pageTitle","forgot_password");
        return "forgot_password";
    }



    @PostMapping("/forget_password")
    public String UserEnterPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String tokens = RandomString.make(30);


        try {
            userService.updateResetPasswordTokens(tokens, email);
            String resetPasswordLink = UtilityPassword.getSiteURL(request) + "/reset_password?token=" + tokens;



            sendEmails(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a set password link to the user email. Please check.");

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error",  "error while sending email");
        }

        return "forget_password";
    }


    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(255);


        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;



                       sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error",  "error while sending email");
        }

        return "forgot_password";
    }

    private void sendEmails(String email, String resetPasswordLink)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("qualitywriter331@gmail.com", "Tracom/Pergamon");
        helper.setTo(email);

        String subject = "Here's the link to set your password";

        String content = "<p>Hello,</p>"
                + "<p>You have been requested by the admin to set your password to access the meetings page.</p>"
                + "<p>Click the link below to set your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">set my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if does not concerns you, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    private void sendEmail(String email, String resetPasswordLink)
                    throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("qualitywriter331@gmail.com", "Tracom/Pergamon");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }

    @GetMapping("/reset_passwords")
    public String showResetPasswordForms(@Param(value = "token") String tokens, Model model) {
        User user = userService.getBySetPasswordTokens(tokens);
        model.addAttribute("token", tokens);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "messages";
        }

        return "reset_password_forms";
    }
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }

    @PostMapping("/reset_passwords")
    public String processResetPasswords(HttpServletRequest request, Model model) {
        String tokens = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getBySetPasswordTokens(tokens);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "messages";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully set your password.");
        }

        return "messages";
    }

}
