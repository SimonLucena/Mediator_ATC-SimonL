package br.edu.ifpb.pp.repository;

import br.edu.ifpb.pp.entity.Flight;
import java.util.ArrayList;

public class FlightRepository {
    private final ArrayList<Flight> flights;

    public FlightRepository() {
        this.flights = new ArrayList<Flight>();
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
}
