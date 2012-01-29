package no.officenet.example.snippetscopetest.snippet

import net.liftweb._
import http._
import js.JsCmds.SetHtml
import util.Helpers._
import java.util.Date
import xml.Text

class TransientDialogSnippet extends TransientSnippet {

	val callbackFunc = callbackFuncVar.get
	val listItemNum = listItemNumber.get

	println("** Constructing new " + getClass.getSimpleName + " for listItem: " + listItemNum + " " + this)

	val timeId = nextFuncName
	def render = {
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
		".identityHashCode" #> fluff
	}

	def fluff = Text("this: " + System.identityHashCode(this))

}