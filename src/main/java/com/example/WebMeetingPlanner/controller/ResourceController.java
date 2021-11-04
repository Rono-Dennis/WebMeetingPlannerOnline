package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Exceptions.UserNotFoundExceptionn;
import com.example.WebMeetingPlanner.Model.*;
import com.example.WebMeetingPlanner.Repository.*;
import com.example.WebMeetingPlanner.Service.UserService;
import com.example.WebMeetingPlanner.Utilities.Utility;
import com.example.WebMeetingPlanner.Utilities.UtilityPassword;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ResourceController {



    @Autowired
    private ResourcesRepository resourceRepository;




    @GetMapping("/Addresource")
    public String AddResource(Model model) {
        model.addAttribute("resource", new Resources());

        return "AddResources";
    }




    @PostMapping("/Saveresource")
    public String ResourceSave(Resources resources)
    {
        resourceRepository.save(resources);
        return "savedresources";
    }

    @GetMapping("/Saveresource")
    public String AddResources(Model model)
    {
        List<Resources> addresource=resourceRepository.findAll();
        model.addAttribute("listresources",addresource);

        return "savedresources";
    }


}
