package pl.michalgorski.gitinfo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryModel {

    @JsonProperty(value = "full_name")
    private String fullName;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "clone_url")
    private String cloneUrl;

    private int stars = 0;

    @JsonProperty(value = "created_at")
    private String createdAt;

}