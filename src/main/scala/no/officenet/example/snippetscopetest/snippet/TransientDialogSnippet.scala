package no.officenet.example.snippetscopetest.snippet

import net.liftweb._
import http._
import js.JsCmd
import js.JsCmd._
import js.JsCmds.SetHtml
import util.Helpers._
import java.util.Date
import xml.Text

object fiskVar extends RequestVar[A](A(null))

case class A(var name: String)

class TransientDialogSnippet extends TransientSnippet {

	val callbackFunc = callbackFuncVar.get
	val listItemNum = listItemNumber.get

	println("** Constructing new " + getClass.getSimpleName + " for listItem: " + listItemNum + " " + this)

	def fisk = fiskVar.get

	fisk.name = "Andreas"

	val timeId = nextFuncName
	def render = {
		println("1: this(" + System.identityHashCode(this) + "):fisk("+System.identityHashCode(fisk)+").name: " + fisk.name)
		println("* " + getClass.getSimpleName + ".render for listItem: " + listItemNum + " " + this)
		".itemNum *" #> listItemNum &
		".time [id]" #> timeId &
		".updateTime" #> SHtml.ajaxButton("Update time " + listItemNum, () => {
			println("* " + getClass.getSimpleName + ": Updating time for : " + listItemNum + " " + this)
			SetHtml(timeId, Text(new Date().toString))
		}) &
		".updateList" #> SHtml.ajaxButton("Update listItem " + listItemNum, () => {
			println("Updating background listItem: " + listItemNum + " by calling callbackFunc")
			callbackFunc()
		}) &
		".editBlogEntry" #> SHtml.ajaxSubmit ("Send", () => save)
	}

	private def save: JsCmd = {
		println("save: this(" + System.identityHashCode(this) + "):fisk("+System.identityHashCode(fisk)+").name: " + fisk.name)

	}

}