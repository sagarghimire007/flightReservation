package com.udemy.flightReservation.service;

import com.udemy.flightReservation.dto.ReservationRequest;
import com.udemy.flightReservation.entities.Reservation;

public interface ReservationService {

    Reservation bookFlight(ReservationRequest request);
}
