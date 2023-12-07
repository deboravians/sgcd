package sgcd;

import java.io.Serializable;

public abstract class Desbravador implements Serializable {
    private String nome;
    private Genero genero;
    private String cargo;
    private int idade;
    private boolean possuiLenco;

    public Desbravador(String nome, Genero genero, int idade, String cargo){
        this.nome = nome;
        this.genero = genero;
        this.idade = idade;
        this.possuiLenco = false;
        this.cargo = cargo;
    }

    public Desbravador(String nome, Genero genero, int idade) {
        this.nome = nome;
        this.genero = genero;
        this.idade = idade;
        this.possuiLenco = false;
        this.cargo = "Desbravador";
    }

    public String getNome(){
        return this.nome;
    }

    public String getCargo() {
        return this.cargo;
    }

    public int getIdade(){
        return this.idade;
    }

    public boolean getPossuiLenco(){
        return this.possuiLenco;
    }

    public void setPossuiLenco(){
        this.possuiLenco = true;
    }

    public String toString(){
        String saida = ANSI.BLUE + getNome();
        saida += "\nIdade: " + getIdade()
               + "\nCargo: " + getCargo();
        if(getPossuiLenco()){
            saida += "\nInvestido(a) em lenço!" + ANSI.RESET;
        }
        return saida + ANSI.WHITE + "\n=======================|\n";
    }
}