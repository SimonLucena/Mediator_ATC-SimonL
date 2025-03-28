package br.edu.ifpb.pp.atc;

import br.edu.ifpb.pp.entity.Equipe;
import br.edu.ifpb.pp.entity.Flight;
import br.edu.ifpb.pp.landing.Runway;
import br.edu.ifpb.pp.landing.state.DisponivelState;
import br.edu.ifpb.pp.landing.state.IndisponivelState;
import br.edu.ifpb.pp.service.FlightService;
import br.edu.ifpb.pp.service.RunwayService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ATC implements ATCMediator {
    private final FlightService flights = new FlightService();
    private final RunwayService runways = new RunwayService();
    private ArrayList<Flight> fila = new ArrayList<>();
    private Equipe equipe;
    private boolean status;

    @Override
    public void registerRunway(Runway runway) {
        runways.addRunway(runway);
    }

    @Override
    public void registerFlight(Flight flight) {
        flights.addFlight(flight);
    }

    @Override
    public void regsiterTeam(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public void setLandingStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void setLandingOcupied(Runway runway) {
        equipe.setAtivo(true);
    }

    @Override
    public void clearRunway(Runway pista) {
        for (Runway runway : runways.getAll()) {
            if (runway == pista) {
                runway.setState(new DisponivelState());
                runway.land();
                fila.getFirst().setReady();
            }
        }
    }

    @Override
    public Runway selectRunwayForLanding() {
        for (Runway runway : runways.getAll()) {
            if (runway.getStatus().equalsIgnoreCase("disponivel")) {
                runway.setState(new IndisponivelState());
                return runway;
            }
        }
        return null;
    }

    @Override
    public boolean isLandingOk() {
        for (Runway runway : runways.getAll()) {
            if(runway.getStatus().equalsIgnoreCase("disponivel")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean solicitarLanding(Flight flight) {
        fila.add(flight);
        if (isLandingOk() && fila.getFirst().equals(flight)) {
            Runway runway = selectRunwayForLanding();
            runways.setOtherImseguro(runway);
            return true;
        }
        return false;
    }

    @Override
    public void setLanded(Flight flight) {
        fila.removeFirst();
    }

    @Override
    public boolean checarFila(Flight flight) {
        return fila.getFirst().equals(flight);
    }
}
