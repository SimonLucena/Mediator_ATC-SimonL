package br.edu.ifpb.pp.entity;

import br.edu.ifpb.pp.atc.ATCMediator;
import br.edu.ifpb.pp.landing.Command;
import br.edu.ifpb.pp.landing.Runway;

public class Flight implements Command {
    private ATCMediator atcMediator = null;
    private String flightNumber = null;
    private String airline = null;
    private boolean permissao;

    public Flight(ATCMediator atcMediator, String airline, String flight ) {
        this.atcMediator = atcMediator;
        this.flightNumber = flight;
        this.airline = airline;
        this.permissao = false;
    }

    @Override
    public void land() {
        this.permissao = atcMediator.checarPermissao(this);
        if (permissao) {
            System.out.println("Pousando...");
//            atcMediator.solicitarLanding(this);
            System.out.println("Flight " + flightNumber + " Successfully Landed on runway.");
            System.out.println();
            atcMediator.setLanded(this);
        } else {
            System.out.println("Voo " + this.flightNumber + " Esperando autorização.");
            System.out.println();
        }
    }

    public void getReady() {
        System.out.println("Voo " + this.flightNumber + " da " + this.airline + " solicitando autorizacao para pouso...");
        this.permissao = atcMediator.solicitarLanding(this);
        if (permissao) {
            System.out.println("Permissão concedida.");
        }else {
            System.out.println("Permissão rejeitada. Na lista de espera.");
        }
        System.out.println();
    }

    public void setReady() {
        this.permissao = true;
    }

    public boolean isPermissao() {
        return permissao;
    }
}
