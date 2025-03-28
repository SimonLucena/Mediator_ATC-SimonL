package br.edu.ifpb.pp.atc;

import br.edu.ifpb.pp.entity.Equipe;
import br.edu.ifpb.pp.entity.Flight;
import br.edu.ifpb.pp.landing.Runway;
import br.edu.ifpb.pp.landing.state.DisponivelState;
import br.edu.ifpb.pp.landing.state.IndisponivelState;
import br.edu.ifpb.pp.landing.state.InseguroState;
import br.edu.ifpb.pp.service.FlightService;
import br.edu.ifpb.pp.service.RunwayService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ATC implements ATCMediator {
    private final FlightService flights = new FlightService();
    private final RunwayService runways = new RunwayService();
    private final ArrayList<Flight> fila = new ArrayList<>();
    private final ArrayList<Runway> runwaysOcupadas = new ArrayList<>();
    private Map<Flight, Runway> runwayReservada = new HashMap<>();
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
    public void clearRunway(Runway pista) {
        runways.getRunway(pista)
                .setState(new DisponivelState());
        liberarPista(pista);
        fila.getFirst().setReady();
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
        return runways.getRunwayForLanding() != null;
    }

    @Override
    public boolean solicitarLanding(Flight flight) {
        fila.add(flight);
        if (isLandingOk() && fila.getFirst().equals(flight)) {
            Runway runway = selectRunwayForLanding();
            runwayReservada.put(flight, runway);
            runways.setOtherImseguro(runway);
            return true;
        }
        return false;
    }

    @Override
    public void setLanded(Flight flight) {
        Runway pista = runwayReservada.get(flight);

        if (pista == null) {
            System.out.println("Erro: nenhuma pista reservada para o voo.");
            return;
        }

        fila.remove(flight);
        runwayReservada.remove(flight);

        if (!fila.isEmpty()) {
            Flight proximo = fila.getFirst();

            if (isLandingOk()) {
                Runway pistaLivre = selectRunwayForLanding();
                runwayReservada.put(proximo, pistaLivre);
                runways.setOtherImseguro(pistaLivre);
                proximo.setReady();
            }
        }
    }


    @Override
    public boolean checarFila(Flight flight) {
        return fila.getFirst().equals(flight);
    }

    public void liberarPista(Runway pista) {
        System.out.println("Equipe em ação na pista...");
        pista.setState(new InseguroState());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Equipe terminou inspeção e liberou a pista.");
        pista.setState(new DisponivelState());
    }

    @Override
    public boolean checarPermissao(Flight flight) {
        return checarFila(flight) && runwayReservada.containsKey(flight);
    }
}
