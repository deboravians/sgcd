package sgcd;

import java.io.Serializable;

public class Ponto implements Serializable {
    private String motivo;
    private int quantidade;

    public Ponto(String motivo, int quantidade){
        this.motivo = motivo;
        this.quantidade = quantidade;
    }

    public String getMotivo(){
        return motivo;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString(){
        return "Motivo - " + getMotivo() + " : Pontuação - " + getQuantidade();
    }
}
