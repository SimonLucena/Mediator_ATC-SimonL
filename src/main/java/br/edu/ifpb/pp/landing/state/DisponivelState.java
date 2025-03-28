package br.edu.ifpb.pp.landing.state;

import br.edu.ifpb.pp.landing.Runway;

public class DisponivelState implements RunwayState {
    @Override
    public void handle(Runway runway) {
        System.out.println("Landing permission granted.");
        runway.setState(new IndisponivelState());
    }

    @Override
    public String getStatus() {
        return "disponivel";
    }
}
