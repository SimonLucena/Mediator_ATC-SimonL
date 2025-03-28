package br.edu.ifpb.pp;

import br.edu.ifpb.pp.atc.ATC;
import br.edu.ifpb.pp.atc.ATCMediator;
import br.edu.ifpb.pp.entity.Equipe;
import br.edu.ifpb.pp.entity.Flight;
import br.edu.ifpb.pp.landing.Runway;

public class MainMediator {
    public static void main(String[] args) {
        ATCMediator atcMediator = new ATC();

        //Componente 1
        Flight f1 = new Flight(atcMediator, "LATAM", "LA4542");
        Flight f2 = new Flight(atcMediator, "GOL", "GL1273");
        Flight f3 = new Flight(atcMediator, "Emirates", "PA3562");

        //Componente 2
        Runway mainRunway = new Runway(atcMediator);

        // Componente 3
        Equipe equipe = new Equipe(atcMediator);

        atcMediator.registerFlight(f1);
        atcMediator.registerFlight(f2);
        atcMediator.registerFlight(f3);

        atcMediator.registerRunway(mainRunway);

        atcMediator.regsiterTeam(equipe);

        // SIMULAÇÃO

        System.out.println(">> Contato da aeronave 1....");
        f1.getReady();

        System.out.println(">> Contato da aeronave 2....");
        f2.getReady();

        System.out.println(">> Contato da aeronave 3....");
        f3.getReady();

        f1.land();

        f2.land();

        mainRunway.clear();

        f3.land();

        f2.land();

        mainRunway.clear();

        f3.land();
    }
}