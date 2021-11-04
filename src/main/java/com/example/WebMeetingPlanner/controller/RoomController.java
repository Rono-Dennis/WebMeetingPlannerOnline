package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.Resources;
import com.example.WebMeetingPlanner.Model.TracomRooms;
import com.example.WebMeetingPlanner.Repository.ResourcesRepository;
import com.example.WebMeetingPlanner.Repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RoomController {



    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private ResourcesRepository resourceRepository;



    @GetMapping("/Addroom")
    public String AddRoom(Model model) {
        model.addAttribute("room", new TracomRooms());

        return "Addrooms";
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



}
