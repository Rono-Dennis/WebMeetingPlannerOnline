package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.Dates;
import com.example.WebMeetingPlanner.Service.DatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;

@Controller
public class DatesIntervalController {
//	private static final String VIEW_DATES = "meeting_formm";
//	private static final String VIEW_INTERVAL = "interval";

	@Autowired
	private DatesService datesService;

	@GetMapping("/meeting_form")
	public String redirectToDates(Dates dates) {
		return "meeting_formm";
//				"redirect:/" + VIEW_DATES;
	}

//	@GetMapping("/meeting_form")
//	public String showDatesForm(Dates dates) {
//		return "meeting_formm";
////				VIEW_DATES;
//	}

	@PostMapping("/interval")
	public String calculateInterval(@Valid Dates dates, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "meeting_formm";
//					VIEW_DATES;
		}
		long interval = datesService.calculateDateInterval(dates.getStartDate(), dates.getEndDate());
		model.addAttribute("interval", interval);
		return "interval";
//				VIEW_INTERVAL;
	}
}
