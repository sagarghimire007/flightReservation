package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.entities.Flight;
import com.udemy.flightReservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping("findFlights")
    public String findFlight(@RequestParam("from") String from, @RequestParam("to") String to,
                             @DateTimeFormat(pattern="MM-dd-yyyy") Date departureDate, Model model){
        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
        model.addAttribute("flights", flights);
        return "displayFlights";
    }
}
