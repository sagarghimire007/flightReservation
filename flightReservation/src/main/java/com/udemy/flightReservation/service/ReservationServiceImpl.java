package com.udemy.flightReservation.service;

import com.udemy.flightReservation.dto.ReservationRequest;
import com.udemy.flightReservation.entities.Flight;
import com.udemy.flightReservation.entities.Passenger;
import com.udemy.flightReservation.entities.Reservation;
import com.udemy.flightReservation.repos.FlightRepository;
import com.udemy.flightReservation.repos.PassengerRepository;
import com.udemy.flightReservation.repos.ReservationRepository;
import com.udemy.flightReservation.utill.EmailUtil;
import com.udemy.flightReservation.utill.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PDFGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setEmail(request.getEmail());
        passenger.setPhone(request.getPhone());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);

         Reservation savedReservation = reservationRepository.save(reservation);

         String filePath = "C:/Users/sagar/IdeaProjects/UdemyProjectDevelopment/reservation/" + savedReservation.getId()+".pdf";
         pdfGenerator.generateItinerary(savedReservation, filePath);
         emailUtil.sendItinerary(passenger.getEmail(), filePath);

         return reservation;

    }
}
