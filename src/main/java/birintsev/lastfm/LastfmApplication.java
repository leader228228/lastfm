package birintsev.lastfm;

import birintsev.lastfm.restclient.RESTClient;
import birintsev.lastfm.restclient.RESTClientImpl;
import birintsev.lastfm.services.AlbumService;
import birintsev.lastfm.services.impl.AlbumServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.time.Duration;

@SpringBootApplication
public class LastfmApplication {

    private static final Logger LOGGER = LogManager.getLogger(LastfmApplication.class); // todo log4j.properties

    public static void main(String[] args) {
        SpringApplication.run(LastfmApplication.class, args);
    }

    @Bean(name = "RestClient")
    public RESTClient restClient
    (@Value("${lastfm.username}") String username,
     @Value("${lastfm.password}") String password,
     @Value("${lastfm.email}") String email,
     @Value("${lastfm.apikey}") String APIKey,
     @Value("${lastfm.sharedsecret}") String sharedSecret,
     @Value("${lastfm.appname}") String appName,
     @Value("${lastfm.api.url.root}") String APIRootURL,
     @Value("${lastfm.dataformat}") String dataFormat,
     @Autowired HttpClient httpClient,
     @Autowired AlbumService albumService) throws MalformedURLException {
        URL _APIRootURL = new URL(APIRootURL);
        RESTClient restClient = new RESTClientImpl(
                username,
                password,
                email,
                APIKey,
                sharedSecret,
                appName,
                _APIRootURL,
                httpClient,
                albumService
        );
        return restClient;
    }

    @Bean(name = "HttpClient")
    public HttpClient httpClient(@Value("${lastfm.api.timeout}") String timeout) throws IOException {
        int _timeout;
        _timeout = Integer.parseInt(timeout);
        if (_timeout <= 0) {
            throw new IOException("Timeout interval must be greater than 0, found: " + _timeout);
        }
        HttpClient httpClient = HttpClient.newBuilder().
                connectTimeout(Duration.ofMillis(_timeout))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        return httpClient;
    }

    @Bean(name = "AlbumServiceHttp")
    public AlbumService albumService() {
        AlbumService albumService = new AlbumServiceImpl();

        return albumService;
    }
}
