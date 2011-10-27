package no.officenet.example.snippetscopetest.lib

import net.liftweb._
import http._
import util.Helpers._
import js._
import js.jquery.JqJE
import js.JsCmds._
import xml.NodeSeq

private[lib] object CustomJqJE {

	case class InsertAtCaret(text: String) extends JsExp with JsMember {
		override val toJsCmd = "insertAtCaret(" + text.encJs + ")"
	}
	case class ReplaceWith(content: NodeSeq) extends JsExp with JsMember {
		override val toJsCmd = "replaceWith(" + fixHtmlFunc ("inline", content)(str => str) + ")"
	}
}

object CustomJsCmds{
	object JqInsertAtCaret {
		def apply(uid: String, text: String): JsCmd =
			(JqJE.JqId(JE.Str(uid)) ~> CustomJqJE.InsertAtCaret(text))
	}

	object JqReplaceWith {
		def apply(uid: String, content: NodeSeq): JsCmd =
			(JqJE.JqId(JE.Str(uid)) ~> CustomJqJE.ReplaceWith(content))
	}
}