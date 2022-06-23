package org.fetch.plugin

import com.themillhousegroup.scoup._
import fansi._
import org.jsoup._
import org.jsoup.nodes.Document

import scala.concurrent._
import scala.concurrent.duration._

class HNews extends FetchPlugin {

	val res: Future[Document] = Scoup.parse("https://news.ycombinator.com/")

	try{
		val result = Await.result(res, 10.seconds)
		fetchHNews(result)
	}
	catch {
		case hse: HttpStatusException => println(s"Aww snap, seems like you're offline... (Status Code: ${hse.getStatusCode})")
	}

	def fetchHNews(document: Document): Unit = {
		val links = document.select("td .titlelink")
		val subtext = document.select("td .subtext")

		for(i <- 9 to 0 by -1){
			val aTag = links.get(i)
			val meta = subtext.get(i)
			println(
				Str(f"${i + 1}%3d : ").overlay(Color.Blue ++ Bold.On)
					++
				Str(s"${aTag.text}").overlay(Color.LightMagenta ++ Bold.On)
			)
			println(Str(s"\t${aTag.attr("href")}").overlay(Color.DarkGray))
			println(
				Str(s"\t${meta.select(".score").text}").overlay(Color.LightGreen)
					++ " by " ++
				Str(s"${meta.select(".hnuser").text}").overlay(Color.Red)
					++ " about " ++
				Str(s"${meta.select(".age").text}\n").overlay(Color.Yellow)
			)
		}
	}

	override def name: String = "Hacker News"

	override def parameters: List[String] = "-hnews" :: "--hacker-news" :: Nil

	override def description: String = "Hacker News - Top 10 tech news"
}
