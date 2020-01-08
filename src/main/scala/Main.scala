import scala.collection.mutable.ListBuffer

object Main extends App {

  val livro = Livro("Livro do du", "2019", 100, 10, 10, "1", false)

  val bookCollection = Seq(
    Livro("Livro do du 1", "2000", 100, 10, 10, "1", false),
    Livro("Livro do du 1", "2000", 100, 10, 6, "1", true),
    Livro("Livro do du 1", "2000", 100, 10, 8, "1", true),
    Livro("Livro do du 2", "2001", 101, 9, 10, "1", true),
    Livro("Livro do du 3", "2002", 102, 8, 10, "1", false),
    Livro("Livro do du 4", "2003", 103, 7, 10, "1", true),
    Livro("Livro do du 5", "2004", 104, 6, 10, "1", false),
    Livro("Livro do du 6", "2005", 105, 5, 10, "1", true)
  )

  imprimeLivros(ObterUmaCopiaPorTitulo(bookCollection.groupBy(_.nome)))

  println("--------------")

  imprimeLivros(obterTodasAsCopiasDeUmTitulo(bookCollection.groupBy(_.nome), "Livro do du 1"))

  println("--------------")

  //mostra todas as copias de um livro
  def obterTodasAsCopiasDeUmTitulo(colecao: Map[String, Seq[Livro]], nome: String): Seq[Livro] = {
    colecao(nome)
      .filter(livro => livro.nome == nome)
      .sortWith(_.qualidade > _.qualidade)
  }

  //mostra todos os tipos livros em ordem decrescente
  def ObterUmaCopiaPorTitulo(colecao: Map[String, Seq[Livro]]) = {
    val cpy = new ListBuffer[Livro]()
    colecao.foreach(col => cpy.addOne(col._2(0)))
    cpy.toSeq.sortWith(_.nota > _.nota)
  }

  //remove os livros
  def removeLivro(colecao : Seq[Livro], funcao:(Seq[Livro]) => Map[String, Seq[Livro]]) : Map[String, Seq[Livro]] = {
    funcao(colecao)
  }


//imprime os livros
  def imprimeLivros(seq: Seq[Livro]): Unit = {
    seq.foreach(livro => println(livro.codigo + "|| NOME: " + livro.nome + "|| LANCAMENTO: " + livro.anoLancamento + "|| QUALIDADE: " + livro.qualidade + "|| LOCADO: " + livro.locado + "||"))
  }
}


case class Livro(nome: String, anoLancamento: String, numPag: Int, nota: Float, qualidade: Int, codigo: String, locado: Boolean)
