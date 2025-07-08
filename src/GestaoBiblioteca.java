import java.io.FileNotFoundException;
import java.util.Scanner;

public class GestaoBiblioteca {
    //onde utilizador ira interagir
    public static void main(String[] args) throws FileNotFoundException {
        //Criar uma variavel  da classe Biblioteca , preciso chamar os métodos da  classe
        Biblioteca biblioteca = new Biblioteca();
        //vou ler os  ficheiros
        biblioteca.lerLivrosDoFicheiro();
        biblioteca.lerLeitoresDoFicheiro();
        biblioteca.lerEmprestimoDoFicheiro();
        // Scanner para ler a entrada do utilizador
        Scanner ler = new Scanner(System.in);
        int escolha;
        do {
        System.out.println("------ MENU ------");
        System.out.println("1. Adicionar livro");
        System.out.println("2. Adicionar leitor");
        System.out.println("3. Emprestar livro");
        System.out.println("4. Devolver livro");
        System.out.println("5. Listar empréstimos");
        System.out.println("6. Listar livros disponíveis");
        System.out.println("7.Apagar leitor");
        System.out.println("8.Apagar livro");
        System.out.println("0. Sair");
        System.out.println("------------------");
        System.out.println("Escolha:");
         escolha = ler.nextInt();
         switch (escolha) {
            case 1:
            //adicionar livro
                ler.nextLine();//limpar o buffer do teclado , quebra de linha
                System.out.println("--- Adicionar novo livro ---");
                System.out.println("Digite o titulo do livro: ");
                String titulo = ler.nextLine();
                System.out.println("Digite o autor do livro: ");
                String autor = ler.nextLine();
                System.out.println("Digite o ISBN do livro: ");
                String isbn = ler.nextLine();
                //criar uma variavel do tipo  livro
                Livro livro = new Livro(titulo, autor, isbn);//vai usar o construtor da classe livro 
                //chamar o metodo criado em Biblioteca para adicionar livro
                biblioteca.adicionarLivro(livro);
                System.out.println("Livro adicionado com sucesso!");
                biblioteca.gravarLivroEmFicheiro(); //colocar o livro adicionado no ficheiro livro.txt
                break;
            case 2:
                //adicionar leitor
                System.out.println("--- Adicionar novo leitor ---");
                System.out.println("Digite o ID do leitor: ");
                Integer id = ler.nextInt();
                ler.nextLine();
                System.out.println("Digite o nome do leitor: ");
                String nome = ler.nextLine();
                System.out.println("Digite o numero de telefone do leitor: ");
                Integer telefone = ler.nextInt();
                Leitor leitor = new Leitor(id, nome, telefone);
                biblioteca.adicionarLeitor(leitor);
                System.out.println("Leitor adicionado com sucesso");
                biblioteca.gravarLeitorEmFicheiro();
                break;
            case 3:
                 System.out.println("--- Emprestar livro ---");
                 System.out.println("Digite o id do leitor: ");
                 Integer idLeitor = ler.nextInt();
                 ler.nextLine();
                 System.out.println("Digite a isbn do livro: ");
                 String isbnLivro = ler.nextLine();
                 biblioteca.emprestarLivro(idLeitor, isbnLivro);
                 biblioteca.gravarEmprestimoEmFicheiro();
                 biblioteca.gravarLivroEmFicheiro();//para mudar o estado do livro como emprestado
                break;
            case 4:
                System.out.println("--- Devolver  livro ---");
                 System.out.println("Digite o id do leitor: ");
                 Integer idLeitor2 = ler.nextInt();
                 ler.nextLine();
                 System.out.println("Digite a isbn do livro: ");
                 String isbnLivro2 = ler.nextLine();
                 biblioteca.devolverLivro(idLeitor2, isbnLivro2);
                 biblioteca.gravarEmprestimoEmFicheiro();//vai atualizar o estado do devolvido
                 biblioteca.gravarLivroEmFicheiro();//vai mostrar que o livro esta disponivel
                break;
            case 5:
                System.out.println("--- Listar emprestimos ---");
                biblioteca.listarEmprestimos();
                break; 
            case 6:
                System.out.println("--- Livros disponíveis ---");
                biblioteca.listarLivrosDisponiveis();
                break;       
            case 7:
                System.out.println("Digite o leitor a remover :");
                int idLeitor3 = ler.nextInt();
                biblioteca.apagarLeitor(idLeitor3);
            break;   
            case 8:
                ler.nextLine();
                System.out.println("Digite o livro a remover: ");
                String isbn3 =ler.nextLine();
                biblioteca.apagarLivro(isbn3);
            break; 
            case 0:
                System.out.println("Sistema encerrado. Obrigado por usar a biblioteca");
                break;    
            default:
                   System.out.println("Opção inválida. Tente novamente.");           
                break;
         }
        } while (escolha != 0);

    }
}
