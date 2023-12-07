package sgcd;

import sgcd.Exceptions.*;
import java.io.Serializable;
import java.util.*;

public class Unidade implements Serializable {
    private String nome;
    private String cor;
    private Genero unidadeGenero;
    private Map<String, Desbravador> membros;
    private ArrayList<Ponto> pontuacao;

    public Unidade(String nome, String cor, Genero unidadeGenero){
        this.nome = nome;
        this.cor = cor;
        this.unidadeGenero = unidadeGenero;
        this.membros = new TreeMap<String,Desbravador>();
        this.pontuacao = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void addCapitao(String nome, Genero genero, int idade, int lenco) throws IdadeInsuficienteException, EntradaInvalidaException {
        if(idade < 10)
            throw new IdadeInsuficienteException();

        Capitao capitao = new Capitao(nome, genero, idade);
        membros.put(capitao.getNome(), capitao);
    }

    public void addSecretario(String nome, Genero genero, int idade, int lenco) throws IdadeInsuficienteException, EntradaInvalidaException {
        if(idade < 10)
            throw new IdadeInsuficienteException();

        Secretario secretario = new Secretario(nome, genero, idade);
        if(lenco == 1){
            secretario.setPossuiLenco();
        }
        membros.put(secretario.getNome(), secretario);
    }

    public void addConselheiro(String nome, Genero genero, int idade, int lenco) throws IdadeInsuficienteException, EntradaInvalidaException {
        if(idade < 16)
            throw new IdadeInsuficienteException();

        Conselheiro conselheiro = new Conselheiro(nome, genero, idade);
        if(lenco == 1){
            conselheiro.setPossuiLenco();
        }
        membros.put(conselheiro.getNome(), conselheiro);
    }

    public void addSemCargo(String nome, Genero genero, int idade, int lenco) throws IdadeInsuficienteException, EntradaInvalidaException {
        if(idade < 10)
            throw new IdadeInsuficienteException();

        SemCargo semCargo = new SemCargo(nome, genero, idade);
        if(lenco == 1){
            semCargo.setPossuiLenco();
        }
        membros.put(semCargo.getNome(), semCargo);
    }

    public void removeDesbravador(Desbravador desbravador){
        membros.remove(desbravador.getNome(), desbravador);
    }

    public Desbravador getDesbravador(String nome){
        return membros.get(nome);
    }

    public void addPontos(Ponto pontos) {
        this.pontuacao.add(pontos);
    }

    public int getPontuacao(){
        int total = 0;
        for(var a : pontuacao)
            total += a.getQuantidade();
        return total;
    }

    public Map<String, Desbravador> getMembros(){
        return this.membros;
    }

    public String getCor(){
        return this.cor;
    }

    public Genero getUnidadeGenero(){
        return this.unidadeGenero;
    }

    public String toString(){
        String saida =  ANSI.GREEN + "Unidade: " + getNome() +
                        "\nCor: " + getCor() +
                        "\nGênero: " + getUnidadeGenero().name() +
                        "\nQuantidade de Membros: " + getMembros().size() +
                        "\nPontuação: " + getPontuacao() + "\n";

        if(!membros.isEmpty()) {
            saida += ANSI.CYAN + "Membros:" + ANSI.WHITE + "\n=======================|\n";
            for (var a : getMembros().entrySet())
                saida += a.getValue();
        }
        return saida + ANSI.YELLOW + "-----------------------|\n" + ANSI.RESET;
    }
}