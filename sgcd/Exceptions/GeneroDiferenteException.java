package sgcd.Exceptions;

public class GeneroDiferenteException extends Exception {
    public GeneroDiferenteException() {
        super("O desbravador não pode ser adicionado a uma unidade do gênero oposto ao dele!");
    }
}