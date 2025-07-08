import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Emprestimo {
    //Representar o ato de emprestar um livro a um leitor.
    //ou seja, guardar os dados de quem pediu emprestado, qual livro, quando, e se já devolveu.
     private Integer id;
     private LocalDate dataEmprestimo;
     private Boolean devolvido;
     private Livro qualLivroEmprestado;//nao foi usado Arraylist pois ele é usado para  guardar vários elementos (uma lista) , e aqui cada objeto Emprestimo representa apenas um evento individua(Cada empréstimo tem um só livro.)
     private Leitor quemEmprestado;
     //constructor
      public Emprestimo(Integer id, LocalDate data_emprestimo, Livro qual_livro_emprestado, Leitor quem_emprestado) {
      this.id = id;
      this.dataEmprestimo = data_emprestimo;
      this.qualLivroEmprestado = qual_livro_emprestado;
      this.quemEmprestado = quem_emprestado;
      this.devolvido=false;
   }
   
     public Leitor getQuemEmprestado() {
         return quemEmprestado;
      }

      public void setQuemEmprestado(Leitor quemEmprestado) {
         this.quemEmprestado = quemEmprestado;
      }

     public Livro getQualLivroEmprestado() {
         return qualLivroEmprestado;
      }

      public void setQualLivroEmprestado(Livro qualLivroEmprestado) {
         this.qualLivroEmprestado = qualLivroEmprestado;
      }
      
     public Integer getId() {
         return id;
      }

      public void setId(Integer id) {
         this.id = id;
      }

      public LocalDate getDataEmprestimo() {
         return dataEmprestimo;
      }

      public void setDataEmprestimo(LocalDate dataEmprestimo) {
         this.dataEmprestimo = dataEmprestimo;
      }

      public Boolean getDevolvido() {
         return devolvido;
      }

      public void setDevolvido(Boolean devolvido) {
         this.devolvido = devolvido;
      }

     //Marcam o empréstimo como devolvido
     public void marcarComoDevolvido(){
        devolvido = true;//alterei o estado do empréstimo para indicar que foi devolvido.
        System.out.println("Emprestimo devolvido com sucesso");
     }
    
     //Verificar se o empréstimo já foi devolvido
     public boolean foiDevolvido(){
        return devolvido;//deve retornar true ou false entao usamos o devolvido pois guarda os dois estados
     }
     //Mostrar os dados do empréstimo
     public void dadosEmprestimo(){
      System.out.println("Nome do leitor : "+ quemEmprestado.getNome()); 
      System.out.println("Titulo do livro : "+ qualLivroEmprestado.getTitulo());
      System.out.println("Data de emprestimo : "+ dataEmprestimo);
      System.out.println("Estado : "+ foiDevolvido());
     }
     // Verificar se o empréstimo está em atraso
     public boolean verificarAtraso(){//nao precisa de parametro pois ele usa os dados que já estão dentro do próprio objeto Emprestimo
      if (devolvido) {
         return false;
      }
      //quero a data de "hoje"
      LocalDate dataHoje = LocalDate.now();
      long diferença = ChronoUnit.DAYS.between(dataEmprestimo, dataHoje);//utilizamos ChronoUnit.DAYS.between(data1, data2) que calcula a diferença entre duas datas que  dá a quantidade de dias entre duas datas.
       if (diferença >7) {
         return true;
       }else{
         return false;
       }
     }
}
