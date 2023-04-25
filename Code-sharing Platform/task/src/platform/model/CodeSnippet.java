package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CodeSnippet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @JsonIgnore
  private String uuid;

  private String code;

  private String date;

  @JsonIgnore
  private LocalDateTime createdAt;

  @JsonIgnore
  private Long restrictedTime;

  private Long time; // Time in seconds

  private Long views;

  @JsonIgnore
  @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean hasViewsRestriction;

  @JsonIgnore
  @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean hasTimeRestriction;

  @JsonIgnore
  @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean isVisible;

  public CodeSnippet() {}

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(final String uuid) {
    this.uuid = uuid;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getDate() {
    return date;
  }

  public void setDate(final String date) {
    this.date = date;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getRestrictedTime() {
    return restrictedTime;
  }

  public void setRestrictedTime(final Long restrictedTime) {
    this.restrictedTime = restrictedTime;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(final Long time) {
    this.time = time;
  }

  public Long getViews() {
    return views;
  }

  public void setViews(final Long views) {
    this.views = views;
  }

  public boolean isHasViewsRestriction() {
    return hasViewsRestriction;
  }

  public void setHasViewsRestriction(final boolean hasViewsRestriction) {
    this.hasViewsRestriction = hasViewsRestriction;
  }

  public boolean isHasTimeRestriction() {
    return hasTimeRestriction;
  }

  public void setHasTimeRestriction(final boolean hasTimeRestriction) {
    this.hasTimeRestriction = hasTimeRestriction;
  }

  @JsonIgnore
  public boolean isVisible() {
    return isVisible;
  }

  public void setVisible(final boolean visible) {
    isVisible = visible;
  }

  @Override
  public String toString() {
    return "CodeSnippet{" +
        "id=" + id +
        ", uuid='" + uuid + '\'' +
        ", code='" + code + '\'' +
        ", date='" + date + '\'' +
        ", createdAt=" + createdAt +
        ", time=" + time +
        ", views=" + views +
        ", hasViewsRestriction=" + hasViewsRestriction +
        ", hasTimeRestriction=" + hasTimeRestriction +
        ", isVisible=" + isVisible +
        '}';
  }
}
