package com.udemy.flightCheckIn.controller;

import com.udemy.flightCheckIn.dto.Reservation;
import com.udemy.flightCheckIn.dto.ReservationUpdateRequest;
import com.udemy.flightCheckIn.integration.ReservationRestClient;
import com.udemy.flightCheckIn.integration.ReservationRestClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightCheckInController {

    @Autowired
    private ReservationRestClientImpl reservationRestClient;

    @RequestMapping(value = "/showCheckIn", method = RequestMethod.GET)
    public String showCheckIn(){
        return "check-in";
    }

    @RequestMapping(value = "/startCheckIn" , method = RequestMethod.POST)
    public String startCheckIn(@RequestParam("reservationId") Long id, Model model){
        Reservation reservation =  reservationRestClient.findClientReservation(id);
        model.addAttribute("reservation", reservation);
        return "complete-checkIn";
    }

    @RequestMapping(value = "/completeCheckIn", method = RequestMethod.POST)
    public String completeCheckIn(@RequestParam("reservationId") Long reservationId , @RequestParam("numberOfBags") int numberOfBags, Model model){
        ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();
        reservationUpdateRequest.setId(reservationId);
        reservationUpdateRequest.setNumberOfBags(numberOfBags);
        reservationUpdateRequest.setCheckedIn(true);
        Reservation reservation =  reservationRestClient.updateClientReservation(reservationUpdateRequest);
        if(reservation == null){
            model.addAttribute("msg", "Failed to Check In !!! Please try again...");
            return "complete-check-in";
        }
        return "check-in-confirmation";
    }
}
