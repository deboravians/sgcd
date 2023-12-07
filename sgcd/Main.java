package sgcd;

import java.util.*;
import sgcd.Exceptions.*;
import java.io.*;
import java.lang.Thread;

public class Main {
    private static ClubeDeDesbravadores clube;
    public static void main(String[] args){
        int decisao;

        do {
            exibirMenu();
            decisao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            try {
                switch (decisao) {
                    case 1:
                        criarClube();
                        break;

                    case 2:
                        deletarClube();
                        break;

                    case 3:
                        System.out.println(clube);
                        break;

                    case 4:
                        atualizarDados();
                        break;

                    case 5:
                        salvarEstado();
                        break;

                    case 6:
                        carregarEstado();
                        break;

                    case 7:
                        break;

                    default:
                        throw new EntradaInvalidaException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (decisao != 7);
    }
    private static Scanner scanner = new Scanner(System.in);

    private static void salvarEstado() throws FileNotFoundException, IOException, InterruptedException {
        System.out.println("Deseja salvar o estado atual? (Y/n)");
        char dec = scanner.nextLine().charAt(0);

        if(dec == 'n')
            return;

        System.out.println("Salvando Estado...");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sgcd.save"));
        out.writeObject(clube);
        out.flush();
        out.close();
        Thread.sleep(250);
        System.out.println("Salvo!");
        Thread.sleep(500);

    }

    private static void carregarEstado() throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Deseja carregar o estado anterior? (Y/n)");
        char dec = scanner.nextLine().charAt(0);

        if(dec == 'n')
            return;

        System.out.println("Carregando Estado...");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("sgcd.save"));
        clube = (ClubeDeDesbravadores) in.readObject();
        Thread.sleep(250);
        System.out.println("Carregado!");
        Thread.sleep(500);
    }

    private static void exibirMenu(){
        System.out.print(ANSI.CLEAR + "\n+=======================================================================================================================+\n");
        System.out.println("SISTEMA DE GERENCIAMENTO DO SEU CLUBE DE DESBRAVADORES                                                                  |");
        System.out.println("|1 - Cadastre seu clube                                                                                                 |");
        System.out.println("|2 - Deletar seu clube                                                                                                  |");
        System.out.println("|3 - Relatório completo do clube                                                                                        |");
        System.out.println("|4 - Atualizar clube                                                                                                    |");
        System.out.println("|5 - Salvar Estado                                                                                                      |");
        System.out.println("|6 - Carregar Estado                                                                                                    |");
        System.out.println("|" + ANSI.RED + "7 - Encerrar Programa" + ANSI.RESET + "                                                                                                  |");
        System.out.print("+=======================================================================================================================+\n");
        System.out.println("Digite um dos comandos acima: ");
    }

    private static void exibirMenuAtualizarDados() {
        System.out.print("\n\n+=======================================================================================================================+\n");
        System.out.println("ATUALIZE OS DADOS DO SEU CLUBE                                                                                          |");
        System.out.println("|1 - Adicionar Unidade                                                                                                  |");
        System.out.println("|2 - Remover Unidade                                                                                                    |");
        System.out.println("|3 - Adicionar membros à diretoria                                                                                      |");
        System.out.println("|4 - Adicionar membros à unidade                                                                                        |");
        System.out.println("|5 - Remover membros do clube                                                                                           |");
        System.out.println("|6 - Remover membros da diretoria                                                                                       |");
        System.out.println("|7 - Menu de Pontuações                                                                                                 |");
        System.out.println("|8 - Atualizar informações do clube                                                                                     |");
        System.out.println("|9 - Voltar ao menu anterior                                                                                            |");
        System.out.print("+=======================================================================================================================+\n");
        System.out.println("Digite um dos comandos acima: ");
    }

    private static void menuPontuacao(){
        System.out.print("\n\n+=======================================================================================================================+\n");
        System.out.println("MENU DE PONTUAÇÕES                                                                                                      |");
        System.out.println("|1 - Adicionar pontos                                                                                                   |");
        System.out.println("|2 - Remover pontos                                                                                                     |");
        System.out.println("|3 - Ver melhor unidade                                                                                                 |");
        System.out.println("|4 - Voltar ao menu anterior                                                                                            |");
        System.out.print("+=======================================================================================================================+\n");
        System.out.println("Digite um dos comandos acima: ");
    }

    private static void criarClube() throws ClubeJaCadastradoException {
        if(clube == null){
            System.out.println("Digite o nome do clube: ");
            String nomeClube = scanner.nextLine();

            System.out.println("Digite a data de fundação (FORMATO dd mm aaaa): ");
            Date dataFundacao = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

            clube = new ClubeDeDesbravadores(nomeClube, dataFundacao);
        }
        else{
            throw new ClubeJaCadastradoException();
        }
    }

    private static void deletarClube() throws DeletaClubeException, NaoHaClubeException {
        if (clube != null) {
            System.out.println("Tem certeza que deseja deletar o clube? (Digite 1 para confirmar)");
            System.out.println("1. SIM");
            System.out.println("2. NÃO");
            int confirmacao = scanner.nextInt();
            scanner.nextLine();

            if (confirmacao == 1) {
                clube = null;
                System.out.println("Clube deletado com sucesso.");
            } else {
                throw new DeletaClubeException("Operação de deletar cancelada.");
            }
        } else {
            throw new NaoHaClubeException();
        }
    }

    private static void atualizarDados() throws NaoHaClubeException {
        if(clube != null){
            exibirMenuAtualizarDados();
            int dec;
            dec = scanner.nextInt();

            try {
                switch (dec) {
                    case 1:
                        adicionarUnidade();
                        break;

                    case 2:
                        removerUnidade();
                        break;

                    case 3:
                        adicionarMembrosDiretoria();
                        break;

                    case 4:
                        adicionarMembrosUnidade();
                        break;

                    case 5:
                        removerMembrosUnidade();
                        break;

                    case 6:
                        removerMembrosDiretoria();
                        break;

                    case 7:
                        gerenciarPontuacoes();
                        break;

                    case 8:
                        atualizarInforsClube();
                        break;

                    case 9:
                        break;

                    default:
                        throw new EntradaInvalidaException();
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            throw new NaoHaClubeException();
        }
    }

    private static void adicionarMembrosDiretoria() throws EntradaInvalidaException, IdadeInsuficienteException {
            System.out.println("Nome do(a) desbravador(a): ");
            scanner.nextLine();
            String nomeMembro = scanner.nextLine();

            System.out.println("Digite o número referente ao cargo do(a) desbravador(a): ");
            System.out.println("1. Diretor ");
            System.out.println("2. Secretario ");
            System.out.println("3. Sem cargo ");
            int respp = scanner.nextInt();

            String cargo = "";
            if (respp == 1) {
                cargo = "diretor";
            } else if (respp == 2) {
                cargo = "secretario";
            } else if (respp == 3) {
                cargo = "sem cargo";
            } else {
                throw new EntradaInvalidaException();
            }

            System.out.println("Digite o número correspondente ao gênero do(a) desbravador(a): ");
            System.out.println("1. FEMININO ");
            System.out.println("2. MASCULINO ");

            int dec = scanner.nextInt();

            Genero generoDbvDirecao = null;
            if(dec == 1) {
                generoDbvDirecao = Genero.FEMININO;
            }
            else if(dec == 2) {
                generoDbvDirecao = Genero.MASCULINO;
            }
            else {
                throw new EntradaInvalidaException();
            }

            System.out.println("Digite a idade do(a) Desbravador(a) (É preciso ter mais que 18 anos): ");
            int idade = scanner.nextInt();
            if(idade < 18){
                throw new IdadeInsuficienteException();
            }

            System.out.println("O(A) Desbravador é investido em lenço?");
            System.out.println("1. SIM ");
            System.out.println("2. NÃO ");
            int decLenco = scanner.nextInt();

            if(decLenco < 1 || decLenco > 2){
                throw new EntradaInvalidaException();
            }

            clube.adicionarMembrosDiretoria(nomeMembro, generoDbvDirecao, idade, cargo, decLenco);

            System.out.println("Desbravador(a) adicionado à Diretoria com sucesso");
    }

    private static void adicionarMembrosUnidade() throws EntradaInvalidaException, IdadeInsuficienteException, UnidadeNaoEncontradaException, GeneroDiferenteException{
        if(clube.getMap().isEmpty()){
            System.out.println("O clube não tem unidades!");
            return;
        }

        System.out.println("Digite o cargo do(a) desbravador(a): ");
        System.out.println("1. Capitão ");
        System.out.println("2. Secretario ");
        System.out.println("3. Conselheiro ");
        System.out.println("4. Sem cargo ");
        int resp = scanner.nextInt();

        if(resp < 1 || resp > 4){
            throw new EntradaInvalidaException();
        }

        scanner.nextLine();

        System.out.println("Nome do(a) desbravador(a): ");
        String nomeDbv = scanner.nextLine();

        System.out.println("Digite o número correspondente ao gênero do(a) desbravador(a): ");
        System.out.println("1. FEMININO ");
        System.out.println("2. MASCULINO ");
        int dec = scanner.nextInt();

        Genero generoDbv = null;
        if(dec == 1) {
            generoDbv = Genero.FEMININO;
        }
        else if(dec == 2) {
            generoDbv = Genero.MASCULINO;
        }
        else {
            throw new EntradaInvalidaException();
        }

        System.out.println("Digite a idade do(a) Desbravador(a): ");
        int idadeDbv = scanner.nextInt();

        System.out.println("O(A) Desbravador é investido em lenço?");
        System.out.println("1. SIM ");
        System.out.println("2. NÃO ");
        int lenco = scanner.nextInt();

        if(lenco < 1 || lenco > 2){
            throw new EntradaInvalidaException();
        }


        System.out.println("O(A) Desbravador(a) precisa ser adicionado a uma unidade. ");
        for(Unidade u : clube.getMap().values()){
            System.out.println("-> " + u.getNome());
        }
        System.out.println("Digite o nome da unidade: ");
        scanner.nextLine();
        String nomeUnidade = scanner.nextLine();
        Unidade unidade = clube.getUnidade(nomeUnidade);

        if (unidade == null) {
            throw new UnidadeNaoEncontradaException();
        }

        if(generoDbv != unidade.getUnidadeGenero()){
            throw new GeneroDiferenteException();
        }

        if(resp == 1){
            unidade.addCapitao(nomeDbv, generoDbv, idadeDbv, lenco);
        }
        else if(resp == 2){
            unidade.addSecretario(nomeDbv, generoDbv, idadeDbv, lenco);
        }
        else if(resp == 3){
            unidade.addConselheiro(nomeDbv, generoDbv, idadeDbv, lenco);
        }
        else if(resp == 4){
            unidade.addSemCargo(nomeDbv, generoDbv, idadeDbv, lenco);
        }

        System.out.println("Desbravador(a) adicionado(a) com sucesso!");
    }

    private static void removerMembrosUnidade() {
        boolean desbravadoresPresentes = false;

        System.out.println("Lista de Desbravadores no Clube:");
        for (Unidade u : clube.getMap().values()) {
            for (Desbravador d : u.getMembros().values()) {
                System.out.println(d.getNome());
                desbravadoresPresentes = true;
            }
        }

        if (!desbravadoresPresentes) {
            System.out.println("Não há Desbravadores no Clube para remover.");
            return;
        }
        System.out.println("Digite o nome do Desbravador que deseja remover da Unidade: ");
        scanner.nextLine();
        String nomeDesbravador = scanner.nextLine();

        boolean removido = false;

        for (Unidade u : clube.getMap().values()) {
            for (Desbravador d : u.getMembros().values()) {
                if (d.getNome().equals(nomeDesbravador)) {
                    u.removeDesbravador(d);
                    removido = true;
                    System.out.println("Desbravador removido com sucesso da Unidade.");
                    break;
                }
            }
            if (removido) {
                break;
            }
        }

        if (!removido) {
            System.out.println("Desbravador não encontrado no Clube.");
        }
    }

    private static void removerMembrosDiretoria() {
        Map<String, Desbravador> membrosDiretoria = clube.getMembrosDiretoria();

        if (membrosDiretoria.isEmpty()) {
            System.out.println("Não há desbravadores na Diretoria para remover.");
            return;
        }

        System.out.println("Lista de Desbravadores na Diretoria:");
        for (Desbravador desbravador : membrosDiretoria.values()) {
            System.out.println(desbravador.getNome());
        }

        System.out.println("Digite o nome do Desbravador que deseja remover da Diretoria: ");
        scanner.nextLine();
        String nomeDesbravador = scanner.nextLine();

        Desbravador desbravadorRemovido = clube.removerMembrosDiretoria(nomeDesbravador);

        if (desbravadorRemovido != null) {
            System.out.println("Desbravador(a) removido(a) da Diretoria com sucesso!");
        } else {
            System.out.println("Desbravador não encontrado na Diretoria.");
        }
    }

    private static void adicionarUnidade() throws EntradaInvalidaException{
        System.out.println("Digite o nome da unidade: ");
        scanner.nextLine();
        String nomeNovaUnidade = scanner.nextLine();

        System.out.println("Digite a cor da unidade: ");
        String corNovaUnidade = scanner.nextLine();

        System.out.println("Digite o número correspondente ao gênero da unidade: ");
        System.out.println("1. FEMININO ");
        System.out.println("2. MASCULINO ");
        int d = scanner.nextInt();

        Genero generoNovaUnidade = null;
        if(d == 1) {
            generoNovaUnidade = Genero.FEMININO;
        }
        else if(d == 2) {
            generoNovaUnidade = Genero.MASCULINO;
        }
        else {
            throw new EntradaInvalidaException();
        }

        Unidade novaUnidade = new Unidade(nomeNovaUnidade, corNovaUnidade, generoNovaUnidade);
        clube.addUnidade(novaUnidade);

        System.out.println("Unidade " + nomeNovaUnidade + " adicionada com sucesso!");
    }

    private static void removerUnidade() throws UnidadeNaoEncontradaException {
        int j = 1;
        for(Unidade u : clube.getMap().values()){
            System.out.println(j + ". " + u);
            j++;
        }

        System.out.println("Digite o nome da unidade que deseja remover");
        System.out.println("ATENÇÃO: OS DESBRAVADORES SERÃO TAMBÉM DELETADOS.");

        scanner.nextLine();
        String nomeDaUnidade = scanner.nextLine();
        Unidade unidad = clube.getUnidade(nomeDaUnidade);

        if (unidad == null) {
            throw new UnidadeNaoEncontradaException();
        }

        clube.removeUnidade(unidad);
        System.out.println("Unidade removida com sucesso!");
    }

    private static void gerenciarPontuacoes() throws UnidadeNaoEncontradaException {
        if(clube.getMap().isEmpty()){
            System.out.println("Clube sem unidades!");
            return;
        }
        for(Unidade u : clube.getMap().values()){
            System.out.println("-> " + u.getNome());
        }

        System.out.println("Digite o nome da unidade que deseja gerenciar a pontuação: ");
        scanner.nextLine();
        String unidadePontuacao = scanner.nextLine();
        Unidade unidade = clube.getUnidade(unidadePontuacao);

        if (unidade == null) {
            throw new UnidadeNaoEncontradaException();
        }

        menuPontuacao();

        int decP = scanner.nextInt();
        switch (decP){
            case 1:
                System.out.println("Quantos pontos deseja adicionar à unidade?");
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Qual o motivo da pontuação?");
                String motivo = scanner.nextLine();
                Ponto ponto = new Ponto(motivo, quantidade);
                unidade.addPontos(ponto);
                break;

            case 2:
                System.out.println("Quantos pontos deseja remover da unidade?");
                int quantidadee = scanner.nextInt();
                quantidadee *= -1;
                scanner.nextLine();
                System.out.println("Qual o motivo da remoção de pontos?");
                String motivoo = scanner.nextLine();
                Ponto pontoo = new Ponto(motivoo, quantidadee);
                unidade.addPontos(pontoo);
                break;

            case 3:
                System.out.println(clube.getMelhorUnidade());
                break;
        }
    }

    private static void atualizarInforsClube() throws NaoHaClubeException {
        System.out.println("Digite qual informação geral do clube você deseja mudar");
        System.out.println("1. Nome do clube");
        System.out.println("2. Data de Fundação");
        System.out.println("3. Voltar para o menu principal");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if(escolha == 1){
            System.out.println("Digite um novo nome para o clube");
            String novoNome = scanner.nextLine();

            System.out.println("Tem certeza que deseja alterar o nome do clube?");
            System.out.println("1. SIM");
            System.out.println("2. NÃO");
            int confirma = scanner.nextInt();
            scanner.nextLine();

            if (confirma == 1) {
                clube.setNome(novoNome);
                System.out.println("Nome do clube alterado com sucesso!");
            } else if(confirma == 2){
                return;
            }
        }

        if(escolha == 2){
            System.out.println("Digite uma nova data de fundação (FORMATO: aa mm dddd): ");
            Date novaDataF = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            scanner.nextLine();

            System.out.println("Tem certeza que deseja alterar a data de fundação do clube?");
            System.out.println("1. SIM");
            System.out.println("2. NÃO");
            int confirmaa = scanner.nextInt();
            scanner.nextLine();

            if (confirmaa == 1) {
                clube.setData(novaDataF);
                System.out.println("Data de fundação alterada com sucesso!");
            } else if(confirmaa == 2){
                return;
            }
        }
    }
}