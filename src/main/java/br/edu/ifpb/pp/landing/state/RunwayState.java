package br.edu.ifpb.pp.landing.state;

import br.edu.ifpb.pp.landing.Runway;

public interface RunwayState {
    void handle(Runway runway);
    String getStatus();
}
