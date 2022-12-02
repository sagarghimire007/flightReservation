package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.dto.ReservationRequest;
import com.udemy.flightReservation.entities.Flight;
import com.udemy.flightReservation.entities.Reservation;
import com.udemy.flightReservation.repos.FlightRepository;
import com.udemy.flightReservation.service.ReservationService;
import com.udemy.flightReservation.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationServiceImpl reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id).get();
        model.addAttribute("flight", flight);
        return "completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request, Model model){
        Reservation reservation = reservationService.bookFlight(request);
        if(reservation == null){
            model.addAttribute("flight", "Failed to make a reservation !! Please try again..");
            return "completeReservation";
        }
        model.addAttribute("reservation", reservation);
        return "displayReservation";
    }
}
