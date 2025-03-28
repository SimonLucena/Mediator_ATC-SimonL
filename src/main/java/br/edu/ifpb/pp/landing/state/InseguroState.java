package br.edu.ifpb.pp.landing.state;

import br.edu.ifpb.pp.landing.Runway;

public class InseguroState implements RunwayState {
    @Override
    public void handle(Runway runway) {
        System.out.println("Landing permission denied. Runway occupied.");
    }

    @Override
    public String getStatus() {
        return "inseguro";
    }
}
