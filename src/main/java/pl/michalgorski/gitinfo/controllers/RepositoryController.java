package pl.michalgorski.gitinfo.controllers;

import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import pl.michalgorski.gitinfo.models.Config;
import pl.michalgorski.gitinfo.models.RepositoryModel;
import java.util.concurrent.TimeUnit;

@Controller
public class RepositoryController {

    @Throttling(type = ThrottlingType.RemoteAddr, limit = 20, timeUnit = TimeUnit.SECONDS)
    @GetMapping("/repositories/{owner}/{repositoryName}")
    public String showRepositoryInfo(@PathVariable("owner") final String owner,
                                     @PathVariable("repositoryName") final String repositoryName,
                                     final Model model) {

        final RestTemplate restTemplate = getRestTemplate();
        final RepositoryModel repositoryModel = restTemplate
                .getForObject(Config.URL_TO_API + owner + Config.SLASH + repositoryName,
                        RepositoryModel.class);

        model.addAttribute("fullName", repositoryModel.getFullName());

        final String descriptionValue = repositoryModel.getDescription();
        model.addAttribute("description", descriptionValue != null ? descriptionValue : Config.EMPTY_DESCRIPTION);

        model.addAttribute("cloneUrl", repositoryModel.getCloneUrl());
        model.addAttribute("stars", repositoryModel.getStars());
        model.addAttribute("createdAt", repositoryModel.getCreatedAt());

        return "showRepositoryInfo";
    }

    @Bean
    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}