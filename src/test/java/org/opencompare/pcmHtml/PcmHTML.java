package org.opencompare.pcmHtml;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * Created by Ismail on 17/12/15.
 */

public class PcmHTML {

	@Test
	public void homePage() throws Exception {
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		try (final WebClient webClient1 = new WebClient()) {
			//test sur le titre de la page
			final HtmlPage page = webClient1.getPage("file:./out/pcm.html");
			System.out.println(page);
			assertEquals("Ma super pcm !!", page.getTitleText());

			//test sur l balise title
			final HtmlElement head = page.getHead();
			page.getByXPath("//head").get(0); 
			//System.out.println(head.asXml());
			assertTrue(head.asXml().contains("<title>"));

			//test sur la balise table dans body
			final HtmlElement body = page.getBody();
			//System.out.println(body.asXml());
			assertTrue(body.asXml().contains("<table "));
			
			//test sur les lignes et le cellules 
			final HtmlTable table = page.getHtmlElementById("matrix_Ma super pcm !!");
		for (final HtmlTableRow row : table.getRows()) {
			    System.out.println("<tr> :");
		    for (final HtmlTableCell cell : row.getCells()) {
			        System.out.println("      cell: " + cell.asText());
		    }
		}
		
			//test sur la balise caption de la table
			final String caption = table.getCaptionText();
			System.out.println("caption de la matrice :" +caption);

//			final String pageAsXml = page.asXml();
//			assertTrue(pageAsXml.contains("<body class=\"composite\">"));
//
//			final String pageAsText = page.asText();
//			assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		}
	}


}
