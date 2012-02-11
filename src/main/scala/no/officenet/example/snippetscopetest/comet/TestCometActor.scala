package no.officenet.example.snippetscopetest.comet

import net.liftweb._
import http.js.JsCmds.Alert
import http.{S, SHtml, CometActor}
import util.Helpers._
import xml.Text
import no.officenet.example.snippetscopetest.lib.JQueryDialog

class TestCometActor extends CometActor {
	val transientDialogTemplate = "templates-hidden/transientDialog"

	def render = {
		".transientDialogLink *" #> SHtml.a(() => {
			S.runTemplate(List(transientDialogTemplate)).
				map(ns => JQueryDialog(ns, "Dialog inited from comet-actor").open).
				openOr(Alert("Template not found: " + transientDialogTemplate))
		}, Text("Click for comet-initieted popup"))
	}

}
