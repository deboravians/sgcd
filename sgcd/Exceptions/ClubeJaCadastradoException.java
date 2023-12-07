package sgcd.Exceptions;
public class ClubeJaCadastradoException extends Exception {
    public ClubeJaCadastradoException() {
        super("Você já cadastrou um clube. Utilize a opção 4 para atualizar os dados.");
    }
}