package br.edu.ifpb.pp.repository;

import br.edu.ifpb.pp.landing.Runway;
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
}
