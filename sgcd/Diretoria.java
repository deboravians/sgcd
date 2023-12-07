package sgcd;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sgcd.Exceptions.*;

public class Diretoria implements Serializable {
    private Map<String, Desbravador> membros;

    public Diretoria(){
        membros = new HashMap<String, Desbravador>();
    }

    public void addDiretor(Diretor diretor) throws IdadeInsuficienteException {
        if(diretor.getIdade() < 18)
            throw new IdadeInsuficienteException();

        membros.put(diretor.getNome(), diretor);
    }

    public void addSecretario(Secretario secretario) throws IdadeInsuficienteException {
        if(secretario.getIdade() < 18)
            throw new IdadeInsuficienteException();

        membros.put(secretario.getNome(), secretario);
    }

    public void addSemCargo(SemCargo semCargo) throws IdadeInsuficienteException {
        if(semCargo.getIdade() < 18)
            throw new IdadeInsuficienteException();

        membros.put(semCargo.getNome(), semCargo);
    }

    public boolean removeDesbravador(String nome) {
        for (var a : membros.entrySet()) {
            if (a.getKey().equals(nome)) {
                membros.remove(a.getKey());
                return true;
            }
        }
        return false;
    }

    public Desbravador getDesbravador(String nome) {
        return this.membros.get(nome);
    }

    public Map<String, Desbravador> getMembros(){
        return this.membros;
    }

    public String toString(){
        String saida = "\nDiretoria: " +
                "\nQuantidade de Membros: " + getMembros().size() +
                "\n";

        if (!membros.isEmpty()) {
            saida += ANSI.CYAN + "Membros:" + ANSI.WHITE + "\n=======================|\n";
            for (var a : getMembros().entrySet()) {
                saida += a.getValue();
            }
        }
        return saida + ANSI.RESET;
    }
}