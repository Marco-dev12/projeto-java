public class Livro {
    //guardando os dados do livro
    private String titulo;
    private String autor;
    private String isbn;
    private Boolean emprestado;
   
   //criar construtor com argumentos
    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.emprestado = false;//Sempre que um livro for criado, vai começar como disponível , nenhum livro entra já emprestado no sistema.
    }

    //criar metodos  GEETERS E SETERS
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(Boolean emprestado) {
        this.emprestado = emprestado;
    }
    //fim geter e seter
    
    public void emprestar(){
        emprestado = true;//marca o livro como emprestado , quando for chamado(livro.emprestar();) significa que o livro agora está emprestado para um leitor.
    }
    
    public void devolver(){
        emprestado = false;//marca o livro como disponível, quando for chamado(livro.devolver();), significa que o livro foi devolvido à biblioteca, e agora está disponível para ser emprestado novamente.
    }
}
