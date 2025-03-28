package br.edu.ifpb.pp.entity;

import br.edu.ifpb.pp.atc.ATCMediator;

public class Equipe {
    private ATCMediator atcMediator = null;
    private boolean ativo;


    public Equipe(ATCMediator atcMediator){
        this.atcMediator = atcMediator;
        this.ativo = false;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
