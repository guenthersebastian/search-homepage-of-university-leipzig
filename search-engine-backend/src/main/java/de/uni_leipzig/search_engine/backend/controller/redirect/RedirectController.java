package de.uni_leipzig.search_engine.backend.controller.redirect;

import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import de.uni_leipzig.search_engine.backend.controller.search.dto.SearchResult;
import de.uni_leipzig.search_engine.backend.lucene.SearcherComponent;

@Controller
public class RedirectController
{
	@Autowired
	private SearcherComponent searcherComponent;
	
	@RequestMapping(method=RequestMethod.GET, path="/url")
	public RedirectView redirect(@RequestParam Integer documentId, @RequestParam(defaultValue="-1") Integer duplicate)
	{
		if(duplicate < 0)
		{
			return new RedirectView(searcherComponent.doc(documentId).get(SearchResult.INDEX_FIELD_LINK));
		}
		else
		{
			Document doc = searcherComponent.doc(documentId);
			
			return new RedirectView(doc.getValues(SearchResult.INDEX_FIELD_LINKS_TO_DUPLICATES)[duplicate]);
		}
	}
}
