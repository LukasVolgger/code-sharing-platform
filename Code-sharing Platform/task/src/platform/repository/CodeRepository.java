package platform.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import platform.model.CodeSnippet;

public interface CodeRepository extends CrudRepository<CodeSnippet, Long> {

  Optional<CodeSnippet> findByUuid(String uuid);

  @Query(value = "SELECT  * FROM CODE_SNIPPET WHERE HAS_TIME_RESTRICTION = FALSE AND HAS_VIEWS_RESTRICTION = FALSE", nativeQuery = true)
  Iterable<CodeSnippet> finAllNotRestricted();
}
