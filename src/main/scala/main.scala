import java.io._
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object main {
  def main(args: Array[String]): Unit = {
    val filename = args(0)
    val numToGet = args(1).toInt
    val text = getTextFromPdf(filename)
    text match {
      case Right(x) =>
        printResult(
          sortTextCount(
            getTextCount(
              getSpiltedText(x, '\n'))).take(numToGet))
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
