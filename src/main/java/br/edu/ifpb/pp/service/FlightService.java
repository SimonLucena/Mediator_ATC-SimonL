package br.edu.ifpb.pp.service;

import br.edu.ifpb.pp.entity.Flight;
import br.edu.ifpb.pp.repository.FlightRepository;

public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService() {
        this.flightRepository = new FlightRepository();
    }

    public void addFlight(Flight flight) {
        flightRepository.addFlight(flight);
    }
}
