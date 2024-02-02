package model;

import java.time.LocalTime;

public class Parada {
    
    private int onibus;
    private int ponto;
    private LocalTime horario;
    private boolean atrasado;

    public int getOnibus() {
        return onibus;
    }

    public void setOnibus(int onibus) {
        this.onibus = onibus;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public boolean getAtrasado() {
        return this.atrasado;
    }

    public void setAtrasado(boolean atrasado) {
        this.atrasado = atrasado;
    }
}
