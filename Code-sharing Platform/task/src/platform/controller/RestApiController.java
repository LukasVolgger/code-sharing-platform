package platform.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;
import platform.service.CodeSnippetService;
import platform.service.UtilsService;

@RestController
public class RestApiController {

  private final CodeRepository codeRepository;
  private final CodeSnippetService codeSnippetService;
  private final UtilsService utilsService;

  @Autowired
  public RestApiController(final CodeRepository codeRepository,
      final CodeSnippetService codeSnippetService, final UtilsService utilsService) {
    this.codeRepository = codeRepository;
    this.codeSnippetService = codeSnippetService;
    this.utilsService = utilsService;
  }

  @RequestMapping(value = "/api/code/{uuid}", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getApiCode(@PathVariable final String uuid) {

    final Optional<CodeSnippet> codeSnippetOptional = codeRepository.findByUuid(uuid);

    if (codeSnippetOptional.isPresent()) {
      final CodeSnippet codeSnippet = codeSnippetOptional.get();

      if (!codeSnippet.isVisible()) {
        return new ResponseEntity<>("No CodeSnippet found!", HttpStatus.NOT_FOUND);
      } else {
        if (codeSnippet.getViews() > 0 && codeSnippet.isHasViewsRestriction()) {
          codeSnippetService.updateViews(codeSnippet);
        }

        if (codeSnippet.getTime() > 0 && codeSnippet.isHasTimeRestriction()) {
          codeSnippetService.updateTime(codeSnippet);
        }

        if (codeSnippet.getTime() >= 0 && codeSnippet.getViews() >= 0) {
          return new ResponseEntity<>(codeSnippet, HttpStatus.OK);
        } else {
          return new ResponseEntity<>("No CodeSnippet found!", HttpStatus.NOT_FOUND);
        }

      }
    }

    return new ResponseEntity<>("No CodeSnippet found!", HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/api/code/new", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String createCodeSnippet(@RequestBody CodeSnippet codeSnippet) {
    final String uuid = UUID.randomUUID().toString();

    codeSnippet.setUuid(uuid);
    codeSnippet.setDate(utilsService.getCurrentDateTime());
    codeSnippet.setCreatedAt(LocalDateTime.now());
    codeSnippet.setHasViewsRestriction(codeSnippet.getViews() != 0);
    codeSnippet.setHasTimeRestriction(codeSnippet.getTime() != 0);
    codeSnippet.setRestrictedTime(codeSnippet.getTime());

    if (codeSnippet.getViews() >= 0 && codeSnippet.getTime() >= 0) {
      codeSnippet.setVisible(true);
    }

    codeRepository.save(codeSnippet);

    return "{ \"id\": \"" + codeSnippet.getUuid() + "\" }";
  }

  @RequestMapping(value = "/api/code/latest", method = {RequestMethod.GET})
  public ResponseEntity<Object[]> getApiCodeLatest() {
    List<CodeSnippet> responseList = new ArrayList<>() ;
    List<CodeSnippet> responseList10 = new ArrayList<>() ;
    Iterable<CodeSnippet> iterable = codeRepository.finAllNotRestricted();


    iterable.forEach(responseList::add);
    responseList.sort(Comparator.comparing(CodeSnippet::getDate).reversed());

    int limit = responseList.size() < 10 ? responseList.size() : 10;

    for (int i = 0; i < limit; i++) {
      responseList10.add(responseList.get(i));
    }

    return new ResponseEntity<>(responseList10.toArray(), HttpStatus.OK);
  }

}
