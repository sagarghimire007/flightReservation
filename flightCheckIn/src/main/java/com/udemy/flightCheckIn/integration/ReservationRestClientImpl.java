package com.udemy.flightCheckIn.integration;

import com.udemy.flightCheckIn.dto.Reservation;
import com.udemy.flightCheckIn.dto.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservationRestClientImpl implements ReservationRestClient{

    @Value("${com.udemy.flightCheckIn.restServiceURL}")
    private String filePath;

    @Override
    public Reservation findClientReservation(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Reservation reservation = restTemplate.getForObject(filePath + id, Reservation.class);
        return reservation;
    }

    @Override
    public Reservation updateClientReservation(ReservationUpdateRequest reservationUpdateRequest) {
        RestTemplate restTemplate = new RestTemplate();
        Reservation reservation1 = restTemplate.postForObject(filePath,reservationUpdateRequest, Reservation.class);
        return reservation1;
    }
}
