import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    //para gerir os dados , Guardar as listas de livros, leitores e empréstimos
    //Oferecer os métodos que o main() vai chamar
    //o que a biblioteca precisa guardar? livros , leitores e emprestimo
    private ArrayList<Livro> livros;
    private ArrayList<Leitor> leitores;
    private ArrayList<Emprestimo> emprestimos;
    
    public Biblioteca() {// ficou sem paramentro pois vou começar com listas vazias e adicionar livros, leitores e empréstimos ao longo do programa.
        this.livros = new ArrayList<>();
        this.leitores = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }
    //Adicionar um objeto Livro à lista de livros da biblioteca.
    public void adicionarLivro(Livro addLivro){//metodo so adiciona , nao retorna , recebe objeto da classe livro
          //Verifica se o livro já existe na lista, para não repetir
       for (Livro li : livros) {
           if (li.getIsbn().equals(addLivro.getIsbn())) {
            System.out.println("Livro ja existe");
            return;
           }
       }
       livros.add(addLivro);
       System.out.println("Livro adicionado com sucesso");
    }
    //Adicionar um leitor à lista de leitores da biblioteca
    public void adicionarLeitor(Leitor novoLeitor){
        //Verificar se o leitor já existe na lista:
        for (Leitor l : leitores) {//Percorre a lista leitores
            //Para cada leitor, compara os id
            if (l.getId().equals(novoLeitor.getId())) {
                System.out.println("Leitor ja registrado");
                return ;
            }
        }
        leitores.add(novoLeitor);//sera adiconado fora do for pois ele foi feito para verificar na lista se ja existe esse leitor
        System.out.println("Leitor registrado com sucesso");
    }
    //metodo para permitir que um leitor empreste um livro da biblioteca.
    public void emprestarLivro(Integer idLeitor , String isbnLivro ){ //no parametro passei os atributos das clases pois eu preciso saber do id e do isbn para fazer um emprestimo 
      //Verificar se o livro existe na lista livros
      Livro livroEncontrado = null;//variavel para armazenar o livro encontrado , null pois no inicio nao tem valor
      for (Livro liv : livros) {
        if (liv.getIsbn().equals(isbnLivro)) {
            livroEncontrado = liv;
            break;//Após encontrar o livro , para a busca
        }
      }     
      if (livroEncontrado == null) {
        System.out.println("Livro nao existe");
        return;
      }
      //Verificar se o leitor existe na lista leitores
      Leitor leitorEncontrado = null;
      for (Leitor le : leitores) {
        if (le.getId().equals(idLeitor)) {
            leitorEncontrado = le;
            break;
        }
      }
      if (leitorEncontrado == null) {
        System.out.println("Leitor nao exsite ");
        return;
      }
      // Verificar se o livro já está emprestado , para evitar que um livro seja emprestado duas vezes ao mesmo tempo.
      if (livroEncontrado.getEmprestado()  == true) {
        System.out.println("Livro ja esta emprestado");
        return;//para sair do "metodo";
      }
      //Verificar se o leitor pode pegar mais livros
      //Um leitor só pode ter no máximo 3 livros emprestados ao mesmo tempo.
     // para fazer isso criei um getter para a lista livrosEmprestados na classe leitor
     if (leitorEncontrado.getLivrosEmprestados().size() >= 3) {
      System.out.println("Limite de livros emprestados atingidos");
      return;
     }
     // vamos criar o empréstimo, marcando o livro como emprestado e ligando-o ao leitor.
     // usando o constuctor da classe Emprestimo(A classe Emprestimo representa o ato de emprestar um livro)
     //Criar a data do empréstimo(hoje pois nao esta definido na classe biblioteca)
     LocalDate hoje = LocalDate.now();
     //criar id 
     Integer id = emprestimos.size() + 1;
     //criar um objeto novoEmprestimo atraves da classe Emprestimo 
     Emprestimo novEmprestimo = new Emprestimo(id, hoje, livroEncontrado, leitorEncontrado);
     //adicionar a lista de emprestimos
     emprestimos.add(novEmprestimo);
     //Vamos agora atualizar o estado do livro e do leitor depois que o empréstimo foi criado e adicionado à lista.
     //Marcar o livro como emprestado
     livroEncontrado.emprestar();
     //Adicionar o livro à lista do leitor
     leitorEncontrado.adicionarlivroemprestado(livroEncontrado);//// associa ao leitor
    }
    //Criar o método devolverLivro()
    //Permitir que um leitor devolva um livro que ele tinha emprestado.
    public void devolverLivro(Integer idLeitor , String isbnLivro){//só executa ação, não retorna valor , preciso saber quem é o leitor e qual livro está a ser devolvido
     //Procurar o leitor na lista
     Leitor  leitorEncontrado2 =  null;
      for (Leitor lei : leitores) {
        if (lei.getId().equals(idLeitor)) {
          //se forem iguais vou guardar esses leitores numa varivavel
           leitorEncontrado2 = lei;
        }
      }    
      //se nao encontrar ninguem 
      if (leitorEncontrado2 == null) {
        System.out.println( "Leitor não encontrado");
        return;
      }
      // Procurar o livro na lista
       Livro livroEncontrado2 = null;
      for(Livro livr :livros){
       if (livr.getIsbn().equals(isbnLivro)) {
        livroEncontrado2 = livr;
       }
      }
      if (livroEncontrado2 == null) {
        System.out.println( "Livro não encontrado");
        return;
      }
      //Verificar se o leitor realmente tem o livro
      if (leitorEncontrado2.temlivro(livroEncontrado2) == false) {
        System.out.println("Este leitor não tem este livro");
        return;
      }
      //Remover o livro da lista do leitor usando o metodo ja criado na classe leitor
       leitorEncontrado2.devolverlivro(livroEncontrado2);
       //Marcar o empréstimo como devolvido
       //primeiro rocurar o objeto Emprestimo correspondente a este leitor e este livro
       // ou seja na lista emprestimo feito procurar o registro do empréstimo feito por esse leitor com esse livro, para poder marcá-lo como devolvido.
       // sendo mais claro Mesmo depois de o leitor remover o livro da sua lista, o empréstimo ainda existe registrado na lista da biblioteca.
       // entao vou marcar esse emprestimo que o leitor fez como devolvido
       // encontrando esse emprestimo
       Emprestimo emprestimoEncontrado = null;
       for(Emprestimo emprestimo :emprestimos){
        if (emprestimo.getQuemEmprestado().getId().equals(idLeitor)) {//crieigetter e setter na classe emprrestimo para poder acessar ao get
         if (emprestimo.getQualLivroEmprestado().getIsbn().equals(isbnLivro)) {
          emprestimoEncontrado = emprestimo;
          break;
         }
        }
       }
       if (emprestimoEncontrado == null) {
        System.out.println("Empréstimo não encontrado");
        return;
       }
        emprestimoEncontrado.marcarComoDevolvido();
        System.out.println("Livro devolvido com sucesso!");
    }
    //listar os emprestimos
    public void listarEmprestimos(){
      //. Verificar se a lista de empréstimos está vazia
      if (emprestimos.isEmpty()) {
        System.out.println("Nenhum empréstimo registrado.");
        return;
      }
      // Percorrer a lista de emprestimos
      for(Emprestimo emprestimo2 :emprestimos){
        System.out.println("Nome do leitor :"+emprestimo2.getQuemEmprestado().getNome());//quememprestado é a classe leitor na classe emprestimo
        System.out.println("Titulo do livro :"+emprestimo2.getQualLivroEmprestado().getTitulo());//mesma coisa mas so que com a classe livro
        System.out.println("Data de emrestimo :"+emprestimo2.getDataEmprestimo());
        System.out.println("Estado :"+emprestimo2.foiDevolvido());
      }
    }
    //listar livros disponiveis
    //Mostrar no ecrã apenas os livros que estão disponíveis, ou seja, que ainda não foram emprestados.
    public void listarLivrosDisponiveis(){
      // Verificar se a lista de livros está vazia
      if (livros.isEmpty()) {
        System.out.println("Nenhum livro disponivel.");
        return;
      }
      //Percorrer a lista de livros
        boolean encontrou = false;
      for (Livro disponivel : livros) {
        //Verifica se ele não está emprestado
        if (!disponivel.getEmprestado()) {
          System.out.println("Titulo do livro : "+disponivel.getTitulo());
          System.out.println("Autor do livro : "+disponivel.getAutor());
          System.out.println("ISBN so livro : "+disponivel.getIsbn());
          encontrou = true;
        }
      }
      if (encontrou == false) {
        System.out.println("Nenhum livro encontrado");
      }
    }
    //implementar a gravação dos livros no ficheiro
    //Quando adicionado um livro, gravá-lo automaticamente em livros.txt
    public void gravarLivroEmFicheiro() throws FileNotFoundException{//nao passei o parametro pois eu ja tenho definido a classe livros como arraylist de Livro 
      File ficheiro = new File("livros.txt");//abrir o ficheiro 
      PrintWriter escrita = new PrintWriter(ficheiro);//vai escrever no nosso ficheiro 
      for(int c = 0;c<livros.size();c++){//vai intender o livro como arraylist pois foi definido no inicio 
         Livro livro = livros.get(c); //criar uma variavel do tipo Livro onde ela recebe os livros 
         escrita.println(livro.getTitulo() + ";" + 
                        livro.getAutor() + ";" + 
                        livro.getIsbn() + ";" + 
                        livro.getEmprestado());//estamos a obter as infromações do livro adicionado , titulo;autor;isbn;emprestado
      }
      escrita.close();//apos sair do ciclo nao vai escrever mais nada , fechar o ficheiro 
      System.out.println("--> Ficheiro de livros foi guardado com sucesso");
    }
     //implementar a gravação dos leitores no ficheiro
     //Quando adicionado um leitor, gravá-lo automaticamente em leitores.txt
     public void gravarLeitorEmFicheiro() throws FileNotFoundException{
      File ficheiro2 = new File("leitores.txt");
      PrintWriter escrita2 = new PrintWriter(ficheiro2);
      for(int i= 0; i<leitores.size();i++){
        Leitor leitor = leitores.get(i);
        escrita2.println(leitor.getId() + ";" + 
                        leitor.getNome() + ";" + 
                        leitor.getTelefone() );
      }
      escrita2.close();
      System.out.println("--> Ficheiro de leitores foi guardado com sucesso");
     }
     public void gravarEmprestimoEmFicheiro() throws FileNotFoundException{
      File ficheiro3 = new File("Emprestimo.txt");
      PrintWriter escrita3 = new PrintWriter(ficheiro3);
      for(int j =0;j<emprestimos.size();j++){
        Emprestimo emprestimo = emprestimos.get(j);
        escrita3.println(emprestimo.getId() + ";" + 
                        emprestimo.getDataEmprestimo() + ";" + 
                        emprestimo.getQuemEmprestado().getId() + ";" +//para pegar so o id 
                        emprestimo.getQualLivroEmprestado().getIsbn() + ";" + // para pegar so o isbn
                        emprestimo.foiDevolvido());
      }
      escrita3.close();
      System.out.println("--> Ficheiro de emprestimo foi guardado com sucesso");
     }
     //ler o ficheiro livros.txt
     public void lerLivrosDoFicheiro() throws FileNotFoundException{
      File ficheiro4 = new File("livros.txt");//abrir o ficheiro
      if (ficheiro4.exists()) {//verificar se o ficheiro existe
        Scanner ler = new Scanner(ficheiro4);
        while (ler.hasNextLine()) {
          String  linha = ler.nextLine();//ler linha por linha
          String [] sep = linha.split(";"); //vai separar apos encontrar ponto e virgula
          String titulo = sep[0];
          String autor = sep[1];
          String isbn = sep[2];
          Boolean emprestado = Boolean.parseBoolean(sep[3]);
          Livro liv = new Livro(titulo, autor, isbn);
          liv.setEmprestado(emprestado);//para os livros nao serem marcados todos como nao emprestados (false) , pois n meu contructor eu defini que sempre que adicionar um livro sera marcado como nao emprestado , chamando o setEmprestado vai atualizar o estado de emprestado conforme o ficheiro
          livros.add(liv);//adicionei o livro a lista
        }
        ler.close();
      }
     }
     //ler o ficheiro leitores.txt
     public void lerLeitoresDoFicheiro() throws FileNotFoundException{
      File ficheiro5 = new File("leitores.txt");
      if (ficheiro5.exists()) {
        Scanner ler2 = new Scanner(ficheiro5);
        while (ler2.hasNextLine()) {
          String linha2 = ler2.nextLine();
          String[] sep2 = linha2.split(";");
          Integer id = Integer.parseInt(sep2[0]);
          String nome = sep2[1];
          Integer telefone = Integer.parseInt(sep2[2]);
          Leitor lei = new Leitor(id, nome, telefone);
          leitores.add(lei);
        }
        ler2.close();
      }
     }
     //vou fazer 2 metodos para me auxiliar a ler o ficheiro emprestimo.txt pois vou precisar do id do leitor e do isbn do livro entao vou ter de prourar nas suas respetivas listas
     //buscar o leitor pelo seu id
     public Leitor buscarLeitorPorId(int id){//como vai retornar o leitor com esse id entao o nosso metodo sera do tipo Leitor
      for(int e =0;e<leitores.size();e++){
        Leitor leitor2 = leitores.get(e);
        if (leitor2.getId() == id) {
          return leitor2;
        }
      }
       return null; // fica fora do for e do if , pois se fica-se dentro iria terminar na primeira volta mesmo se tivesse outros elementos para verificar
     }
     //buscar o livro pelo seu isbn 
     public Livro buscarLivroPorIsbn(String isbn){
      for(int a =0;a<livros.size();a++){
        Livro liv3 = livros.get(a);
        if (liv3.getIsbn().equals(isbn)) {
          return liv3;
        }
      }
      return null; //caso nao for encontrado 
     }
     //ler o ficheiro emprestimo.txt
     public void lerEmprestimoDoFicheiro() throws FileNotFoundException{
      File ficheiro5 = new File("Emprestimo.txt");
      if (ficheiro5.exists()) {
        Scanner ler3 = new Scanner(ficheiro5);
        while (ler3.hasNextLine()) {
          String linha3 = ler3.nextLine();
          String[] sep3 = linha3.split(";");
          Integer idEmprestimo = Integer.parseInt(sep3[0]);
          LocalDate data = LocalDate.parse(sep3[1]);
          Integer idLeitor = Integer.parseInt(sep3[2]);
          String isbnLivro = sep3[3];
          boolean devolvido2 = Boolean.parseBoolean(sep3[4]);
          //agora vou procurar o leitor e o livro nas suas listas
          Leitor lei = buscarLeitorPorId(idLeitor); // e como é o leitor entao a variavel sera do tipo Leitor
          Livro li = buscarLivroPorIsbn(isbnLivro);//mesma coisa mas so que a variavel do tipo Livro
          if (lei != null && li != null) {//verificar que tanto o leitor e o livro foram encontrados n lista
            Emprestimo empre = new Emprestimo(idEmprestimo, data, li, lei);
          if (devolvido2 == true) {
            empre.marcarComoDevolvido();
          }
          emprestimos.add(empre);
          }
        }
        ler3.close();
      }
     }
     public void apagarLeitor(int id) throws FileNotFoundException{
      boolean encontrado =false;
      for(int d =0;d<leitores.size();d++){
          Leitor leit = leitores.get(d);
          if (leit.getId() == id) {
            leitores.remove(d);
            encontrado = true;
            System.out.println("Leitor removido com sucesso ");
           // Atualiza o ficheiro com a nova lista
           gravarLeitorEmFicheiro();
           break;
          }
          
      }
      if (!encontrado) {
        System.out.println("Leitor nao encontrado");
      }
     }
     public void apagarLivro(String isbn) throws FileNotFoundException{
      boolean encontrado2 = false;
      for(int y =0;y<livros.size();y++){
        Livro livr = livros.get(y);
        if (livr.getIsbn().equals(isbn)) {
          livros.remove(y);
          encontrado2 =true;
          System.out.println("Livro removido com sucesso");
          gravarLivroEmFicheiro();
          break;
        }
      }
      if (!encontrado2) {
        System.out.println("Livro nao encontrado");
      }
     }
}
