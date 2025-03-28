package br.edu.ifpb.pp.landing;

import br.edu.ifpb.pp.atc.ATCMediator;
import br.edu.ifpb.pp.landing.state.DisponivelState;
import br.edu.ifpb.pp.landing.state.IndisponivelState;
import br.edu.ifpb.pp.landing.state.RunwayState;

public class Runway implements Command {
    private ATCMediator atcMediator;
    private RunwayState state;

    public Runway(ATCMediator atcMediator) {
        this.atcMediator = atcMediator;
        this.state = new DisponivelState();
    }

    @Override
    public void land() {
        System.out.println(">> Consultando situacao da pista....");
        state.handle(this);
        atcMediator.setLandingStatus(state.getStatus().equals("disponivel"));
        System.out.println();
    }

    public void clear() {
        atcMediator.clearRunway(this);
    }

    public void setState(RunwayState state) {
        this.state = state;
    }

    public String getStatus() {
        return state.getStatus();
    }
}
