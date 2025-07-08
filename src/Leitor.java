import java.util.ArrayList;

public class Leitor {
    //Representar uma pessoa que pode pegar livros emprestados na biblioteca. Cada leitor deve ter seus dados básicos e estar ligado aos livros que ele pediu emprestado.
    //guardando os dados do leitor
    private Integer id;
    private String nome;
    private Integer telefone;
    //como é o leitor que empresta o livro vamos associalo a clase livro 
    private ArrayList <Livro> livrosEmprestados; // cada objeto da classe Leitor guarde dentro de si uma lista de objetos Livro. Arraylist porque tem tamanho indefinido

    //constructor
     public Leitor(Integer id, String nome, Integer telefone) {
      this.id = id;
      this.nome = nome;
      this.telefone = telefone;
      this.livrosEmprestados = new ArrayList<>();
    }
    public Integer getId() {
      return id;
    }
     public void setId(Integer id) {
       this.id = id;
     }
     public String getNome() {
       return nome;
     }
     public void setNome(String nome) {
       this.nome = nome;
     }
     public Integer getTelefone() {
       return telefone;
     }
     public void setTelefone(Integer telefone) {
       this.telefone = telefone;
     }
     public ArrayList<Livro> getlivrosEmprestados() {
    return livrosEmprestados;
     }
    //metodo adicionar livro que foi emprestado
    public void adicionarlivroemprestado(Livro livroadicionar){//como é um metodo ou procedimento vou ter que passar um parametro (tipo de parametro nome variavel)
      //LivRo - tipo de parametro - o método está à espera de receber um objeto da classe Livro.
      if (livroadicionar.getEmprestado()) {// Verificar se o livro já está emprestado , getEmprestado Lê o estado atual do livro (true-O livro já está emprestado , se nao esta disponivel )
        System.out.println("Este livro ja esta emprestado");
        return;// parar aqui! Não faz sentido continuar
      }
      //Verificar se o livro já está na lista do leitor
      if (livrosEmprestados.contains(livroadicionar)) {//evita que o leitor tenha o mesmo livro duas vezes.
           System.out.println("Este livro já está emprestado por este leitor.");
          return;
      }
      //Verificar se o leitor pode emprestar mais livros
      if (livrosEmprestados.size() >= 3) {
        System.out.println("Ja atingiu o limite");
        return;
      }
      //Adicionar o livro à lista(Arraylist-livrosemprestados )
      livrosEmprestados.add(livroadicionar);
      //Marcar o livro como emprestado
      livroadicionar.emprestar();
      System.out.println("Livro emprestado com sucesso!");
    }

   //metodo Remover (devolver) um livro
   public void devolverlivro(Livro livrodevolver){
      if (!livrosEmprestados.contains(livrodevolver)) {//livrosemprestados não contem o livro
        System.out.println("Este livro não está emprestado por este leitor");
        return;
      }
      //"Devolver" o livro da lista
      livrosEmprestados.remove(livrodevolver);
      //Marcar o livro como devolvido
      livrodevolver.devolver();
      System.out.println("Livro devolvido com sucesso");
   }
   //Listar todos os livros emprestados
   public void listarLivrosEmprestados(){//Apenas imprime no ecrã os dados dos livros da lista livrosEmprestados.
    // Verificar se a lista está vazia  
    if (livrosEmprestados.isEmpty()) {
        System.out.println("Nenhum livro emprestado no momento.");
        return;
      }
      // Percorrer a lista e imprimir os dados de cada livro
      for (Livro livro : livrosEmprestados) {
        System.out.println("Titulo do livro : "+ livro.getTitulo());
        System.out.println("Autor do livro : "+ livro.getAutor());
        System.out.println("ISBN do livro : "+ livro.getIsbn());
      }
   }
   //Verificar se Este leitor tem este livro na sua lista de livros emprestados
   public boolean temlivro(Livro livroparametro){
    //percorrer a lista livrosEmprestados
    for (Livro livros : livrosEmprestados) {
      if (livros.getIsbn().equals(livroparametro.getIsbn())) {
        //Se encontrar um livro igual
        return true;
      }
    }
    //Se terminar o for sem encontrar nenhum igual
    return false;
   }
   public ArrayList<Livro> getLivrosEmprestados() {
     return livrosEmprestados;
   }
}
