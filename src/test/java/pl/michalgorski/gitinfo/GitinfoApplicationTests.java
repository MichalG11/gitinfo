package pl.michalgorski.gitinfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michalgorski.gitinfo.models.RepositoryModel;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitinfoApplicationTests {

	private final TestRestTemplate testRestTemplate = new TestRestTemplate();
	private final String examplePath = "https://api.github.com/repos/MichalG11/medinfo";

	@Test
	public void httpStatusLoadTest() throws URISyntaxException {

		final URI uri = new URI(examplePath);

		final ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void cloneUrlLoadTest() throws URISyntaxException {

		final URI uri = new URI(examplePath);

		final RepositoryModel repositoryModel = testRestTemplate.getForObject(uri, RepositoryModel.class);

		final String expectedCloneUrl = "https://github.com/MichalG11/medinfo.git";
		final String actualCloneUrl = repositoryModel.getCloneUrl();

		assertEquals(expectedCloneUrl, actualCloneUrl);
	}
}