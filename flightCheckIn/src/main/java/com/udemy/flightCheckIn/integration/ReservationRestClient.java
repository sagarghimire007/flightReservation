package com.udemy.flightCheckIn.integration;

import com.udemy.flightCheckIn.dto.Reservation;
import com.udemy.flightCheckIn.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

    public Reservation findClientReservation(Long id);

    public Reservation updateClientReservation(ReservationUpdateRequest reservation);
}
