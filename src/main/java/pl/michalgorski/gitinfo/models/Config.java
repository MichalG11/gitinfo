package pl.michalgorski.gitinfo.models;

import lombok.Data;

@Data
public class Config {

    public static final String URL_TO_API = "https://api.github.com/repos/";
    public static final String SLASH = "/";

    public static final String EMPTY_DESCRIPTION = "Brak opisu!";
}
