package br.edu.ifpb.pp.atc;

import br.edu.ifpb.pp.entity.Equipe;
import br.edu.ifpb.pp.entity.Flight;
import br.edu.ifpb.pp.landing.Runway;

public interface ATCMediator {
    void registerRunway(Runway runway);
    void registerFlight(Flight flight);
    void regsiterTeam(Equipe equipe);
    boolean isLandingOk();
    void setLandingStatus(boolean status);
    void setLandingOcupied(Runway runway);
    Runway selectRunwayForLanding();
    void clearRunway(Runway runway);
    boolean solicitarLanding(Flight flight);
    void setLanded(Flight flight);
    boolean checarFila(Flight flight);
}
