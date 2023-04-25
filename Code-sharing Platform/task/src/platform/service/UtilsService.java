package platform.service;


import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {
  public String getCurrentDateTime() {
    Instant now = Instant.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.withZone(
        ZoneId.systemDefault());
    return formatter.format(now);
  }
}
