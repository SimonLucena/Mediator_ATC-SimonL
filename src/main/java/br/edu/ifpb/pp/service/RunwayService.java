package br.edu.ifpb.pp.service;

import br.edu.ifpb.pp.landing.Runway;
import br.edu.ifpb.pp.landing.state.IndisponivelState;
import br.edu.ifpb.pp.landing.state.InseguroState;
import br.edu.ifpb.pp.repository.RunwayRepository;

import java.util.ArrayList;

public class RunwayService {
    private final RunwayRepository runwayRepository;

    public RunwayService() {
        this.runwayRepository = new RunwayRepository();
    }

    public void addRunway(Runway runway) {
        runwayRepository.addRunway(runway);
    }

    public void land() {
        Runway runway = getRunwayDisponivel();
        if(runway != null) {
            runway.land();
        }
    }

    public Runway getRunwayDisponivel() {
        for (Runway runway : runwayRepository.getRunways()) {
            if (runway.getStatus().equalsIgnoreCase("disponivel")) {
                return runway;
            }
        }
        return null;
    }

    public boolean isRunwayDisponivel() {
        for (Runway runway : runwayRepository.getRunways()) {
            if (runway.getStatus().equalsIgnoreCase("disponivel")) {
                runway.setState(new IndisponivelState());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Runway> getAll() {
        return runwayRepository.getRunways();
    }

    public void setOtherImseguro(Runway reserva) {
        for (Runway runway : runwayRepository.getRunways()) {
            if(runway != reserva){
                runway.setState(new InseguroState());
            }
        }
    }
}
