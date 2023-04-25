package platform.controller;

import exception.CodeNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;
import platform.service.CodeSnippetService;
import platform.service.UtilsService;


@Controller
public class WebApiController {

  private final CodeRepository codeRepository;
  private final CodeSnippetService codeSnippetService;
  private final UtilsService utilsService;

  @Autowired
  public WebController(CodeRepository codeRepository,
      final CodeSnippetService codeSnippetService, final UtilsService utilsService) {
    this.codeRepository = codeRepository;
    this.codeSnippetService = codeSnippetService;
    this.utilsService = utilsService;
  }

  @RequestMapping(value = "/code/{uuid}", method = {RequestMethod.GET})
  public String getHtmlCode(@PathVariable final String uuid, Model model) {
    final Optional<CodeSnippet> codeSnippetOptional = codeRepository.findByUuid(uuid);

    if (codeSnippetOptional.isPresent()) {
      final CodeSnippet codeSnippet = codeSnippetOptional.get();

      if (codeSnippet.isVisible()) {
        if (codeSnippet.getViews() > 0 && codeSnippet.isHasViewsRestriction()) {
          codeSnippetService.updateViews(codeSnippet);
        }

        if (codeSnippet.getTime() > 0 && codeSnippet.isHasTimeRestriction()) {
          codeSnippetService.updateTime(codeSnippet);
        }

        if (codeSnippet.getTime() >= 0 && codeSnippet.getViews() >= 0) {
          model.addAttribute(codeSnippet);
        } else {
          throw new CodeNotFoundException();
        }

      } else {
        throw new CodeNotFoundException();
      }

    } else {
      throw new CodeNotFoundException();
    }

    return "code_snippet";
  }

  @RequestMapping(value = "/code", method = {RequestMethod.GET})
  public String getHtmlCode() {

    return "code_snippet";
  }

  @RequestMapping(value = "/code/new", method = {RequestMethod.GET}, produces = "text/html")
  public String getHtmlCodeNew() {
    return "new_code_snippet";
  }

  @RequestMapping(value = "/code/new", method = {RequestMethod.POST}, produces = "text/html")
  public String htmlCodeNew() {
    return "new_code_snippet";
  }

  @RequestMapping(value = "/code/latest", method = {RequestMethod.GET}, produces = MediaType.TEXT_HTML_VALUE)
  public String getApiCodeLatest(Model model) {
    List<CodeSnippet> responseList = new ArrayList<>() ;
    List<CodeSnippet> responseList10 = new ArrayList<>() ;
    Iterable<CodeSnippet> iterable = codeRepository.finAllNotRestricted();

    iterable.forEach(responseList::add);
    responseList.sort(Comparator.comparing(CodeSnippet::getDate).reversed());

    int limit = responseList.size() < 10 ? responseList.size() : 10;

    for (int i = 0; i < limit; i++) {
      responseList10.add(responseList.get(i));
    }

    model.addAttribute(responseList10);

    return "code_latest";
  }

}
