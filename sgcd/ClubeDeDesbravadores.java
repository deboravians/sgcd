package sgcd;

import sgcd.Exceptions.*;
import java.io.Serializable;
import java.util.*;

public class ClubeDeDesbravadores implements Serializable {
    private String nome;
    private Diretoria diretoria;
    private Date data;
    private Map<String, Unidade> unidades;

    public ClubeDeDesbravadores(String nome, Date data){
        this.nome = nome;
        this.data = data;
        this.unidades = new HashMap<String,Unidade>();
        this.diretoria = new Diretoria();
    }

    public String getNome(){
        return this.nome;
    }

    public Date getData(){
        return this.data;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setData(Date data){
        this.data = data;
    }

    public void addUnidade(Unidade unidade){
        unidades.put(unidade.getNome(), unidade);
    }

    public void removeUnidade(Unidade unidade){
        unidades.remove(unidade.getNome(), unidade);
    }

    public void adicionarMembrosDiretoria(String nome, Genero genero, int idade, String cargo, int lenco) throws IdadeInsuficienteException, EntradaInvalidaException {
        switch (cargo.toLowerCase()) {
            case "diretor":
                Diretor diretor = new Diretor(nome, genero, idade);
                if(lenco == 1){
                    diretor.setPossuiLenco();
                }
                diretoria.addDiretor(diretor);
                break;

            case "secretario":
                Secretario secretario = new Secretario(nome, genero, idade);
                if(lenco == 1){
                    secretario.setPossuiLenco();
                }
                diretoria.addSecretario(secretario);
                break;

            case "sem cargo":
                SemCargo semCargo = new SemCargo(nome, genero, idade);
                if(lenco == 1){
                    semCargo.setPossuiLenco();
                }
                diretoria.addSemCargo(semCargo);
                break;

            default:
                throw new EntradaInvalidaException();
        }
    }

    public Desbravador removerMembrosDiretoria(String nome) {
        for (Desbravador desbravador : diretoria.getMembros().values()) {
            if (desbravador.getNome().equals(nome)) {
                diretoria.removeDesbravador(nome);
                return desbravador;
            }
        }
        return null;
    }

    public Map<String, Desbravador> getMembrosDiretoria() {
        return diretoria.getMembros();
    }

    public Unidade getUnidade(String nome){
        return unidades.get(nome);
    }

    public Map<String, Unidade> getMap(){
        return this.unidades;
    }

    public Unidade getMelhorUnidade() {
        Unidade melhorUnidade = new Unidade(null, null, null);

        for (var a : unidades.entrySet()) {
            if (a.getValue().getPontuacao() >= melhorUnidade.getPontuacao()) {
                melhorUnidade = a.getValue();
            }
        }
        return melhorUnidade;
    }

    public String toString(){
        String saida = ANSI.PURPLE + "\nClube de Desbravadores: " + getNome();
        saida += "\nData de fundação: " + getData() + ANSI.RESET;
        saida += "\n" + diretoria;

        if(!this.unidades.isEmpty()) {
            saida += "\nUnidades:" + ANSI.YELLOW + "\n-----------------------|\n";
            for(var a : unidades.entrySet())
                saida += a.getValue();
        }
        return saida;
    }
}