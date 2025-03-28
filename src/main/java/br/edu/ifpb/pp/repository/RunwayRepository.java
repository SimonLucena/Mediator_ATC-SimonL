package br.edu.ifpb.pp.repository;

import br.edu.ifpb.pp.landing.Runway;
import br.edu.ifpb.pp.landing.state.RunwayState;

import java.util.ArrayList;

public class RunwayRepository {
    private final ArrayList<Runway> runways;

    public RunwayRepository() {
        this.runways = new ArrayList<Runway>();
    }

    public ArrayList<Runway> getRunways() {
        return runways;
    }
    public void addRunway(Runway runway) {
        runways.add(runway);
    }

    public Runway getRunway(Runway ocupada) {
        return runways.get(runways.indexOf(ocupada));
    }

    public ArrayList<Runway> getRunwaysByState(RunwayState state) {
        ArrayList<Runway> retorno = new ArrayList<>();
        for (Runway runway : runways) {
            if (runway.getState().equals(state)){
                retorno.add(runway);
            }
        }
        return retorno.isEmpty() ? null : retorno;
    }
}
