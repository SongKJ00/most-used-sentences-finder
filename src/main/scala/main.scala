import java.io._
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object Functions {
  def thread[T](item: T) = new ThreadFunctor(item)
}

class ThreadFunctor[T](val item: T) {
  def andThen[A](f: T => A): ThreadFunctor[A] = new ThreadFunctor(f(item))
}

object ThreadFunctor {
  implicit def threadToItem[T](thread: ThreadFunctor[T]): T = thread.item
}

object main {
  def main(args: Array[String]): Unit = {
    val filename = args(0)
    val numToGet = args(1).toInt
    val text = getTextFromPdf(filename)
    text match {
      case Right(x) =>
        val result = Functions.thread(x)
                              .andThen(getSpiltedText(_, '\n'))
                              .andThen(getTextCount)
                              .andThen(sortTextCount)
                              .take(numToGet)

        printResult(result)

      case Left(x) => println(x.getMessage)
    }
  }

  def printResult(result: Seq[(String, Int)]) = {
    result.foreach(println)
  }

  def sortTextCount(textCount: Map[String, Int]) = {
    textCount.toSeq.sortWith(_._2 > _._2)
  }

  def getTextCount(text: List[String]) = {
    text.groupBy(identity).mapValues(_.size)
  }

  def getSpiltedText(text: String, seperatorChar: Char) = {
    text.split(seperatorChar).toList
  }

  def getTextFromPdf(filename: String) = {
    try {
      val pdf = PDDocument.load(new File(filename))
      val stripper = new PDFTextStripper
      Right(stripper.getText(pdf))
    } catch {
      case t: Throwable =>
        Left(t)
    }
  }
}
