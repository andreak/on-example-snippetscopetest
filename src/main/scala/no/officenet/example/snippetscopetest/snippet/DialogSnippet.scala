package no.officenet.example.snippetscopetest.snippet

import net.liftweb._
import http._
import util.Helpers._

class DialogSnippet {

	val callbackFunc = callbackFuncVar.get
	val listItemNum = listItemNumber.get

	println("** Constructing new DialogSnippet for listItem: " + listItemNum + " " + this)

	def render = {
		println("* DialogSnippet.render for listItem: " + listItemNum + " " + this)
		".itemNum *" #> listItemNum &
		".updateList" #> SHtml.ajaxButton("Update listItem " + listItemNum, () => {
			println("Updating background listItem: " + listItemNum + " by calling callbackFunc")
			callbackFunc()
		})
	}

}