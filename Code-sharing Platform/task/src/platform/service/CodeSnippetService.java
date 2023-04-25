package platform.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;

@Service
public class CodeSnippetService {

  private final CodeRepository codeRepository;

  public CodeSnippetService(final CodeRepository codeRepository) {
    this.codeRepository = codeRepository;
  }

  public void updateViews(CodeSnippet codeSnippet) {
    Long views = codeSnippet.getViews();
    codeSnippet.setViews(--views);

    if (views <= 0) {
      codeSnippet.setVisible(false);
      codeRepository.delete(codeSnippet);
    }
    codeRepository.save(codeSnippet);
  }

  public void updateTime(final CodeSnippet codeSnippet) {
    Long timeRestriction = codeSnippet.getRestrictedTime();

    LocalDateTime createdAt = codeSnippet.getCreatedAt();
    LocalDateTime now = LocalDateTime.now();

    long passedSeconds = ChronoUnit.SECONDS.between(createdAt, now);
    Long time = timeRestriction - passedSeconds;
    codeSnippet.setTime(time);

    if (passedSeconds >= timeRestriction) {
      codeSnippet.setVisible(false);
      codeRepository.delete(codeSnippet);
    }

    codeRepository.save(codeSnippet);
  }
}
