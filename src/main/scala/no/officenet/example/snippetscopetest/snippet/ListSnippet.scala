package no.officenet.example.snippetscopetest.snippet

import net.liftweb._
import http._
import util.Helpers._
import js._
import js.JsCmds._

import org.joda.time.DateTime
import xml.Text
import no.officenet.example.snippetscopetest.lib.JQueryDialog

object listItemNumber extends RequestVar[Int](0)
object callbackFuncVar extends RequestVar[() => JsCmd](() => Noop)

class ListSnippet {

	val dialogTemplate = "templates-hidden/dialog"

	private def getCallbackFunc(idMem:  IdMemoizeTransform) = {
		() => idMem.setHtml()
	}

	def render = {
		"tr" #> List(1,2,3).map(v =>
									".numberInList *" #> ("Number: " + v) &
									".lastCell" #> SHtml.idMemoize(idMem => {
										".timestamp *" #> new DateTime().toString("dd.MM.yyy HH:mm:ss") &
										".link *" #> SHtml.a(() => {
											listItemNumber.set(v)
											callbackFuncVar.set(getCallbackFunc(idMem))
											S.runTemplate(List(dialogTemplate)).
												map(ns => JQueryDialog(ns, "I'm hipp!").open).
												openOr(Alert("Template not found: " + dialogTemplate))
										}, Text("Click for popup"))
									})
		)
	}

}