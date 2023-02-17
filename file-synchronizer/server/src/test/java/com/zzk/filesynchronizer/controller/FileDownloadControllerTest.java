package com.zzk.filesynchronizer.controller;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileDownloadControllerTest {

    @LocalServerPort
    private int port;



    private static final String HOME = System.getProperty("user.home");
    Path testSourcePath = Paths.get(HOME).resolve("test/from");
    Path testDestPath = Paths.get(HOME).resolve("test/to");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectory(testSourcePath);
        Files.createDirectory(testDestPath);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteDirectory(testSourcePath);
        deleteDirectory(testDestPath);
    }

    private void deleteDirectory(Path path) throws IOException {
        //make sure del only test file
        assertThat(path.startsWith(testSourcePath) || path.startsWith(testDestPath)).isTrue();
        FileUtils.deleteDirectory(path.toFile());
    }

    @Test
    void download() throws IOException, URISyntaxException {
        String prefix = "http://localhost:" + port;
        // create a temp file in the repository
        final Path sourceFile = Files.createTempFile(testSourcePath, null, null);

        File myFile = new File(testDestPath.resolve(sourceFile.getFileName()).toString());

        CloseableHttpClient client = HttpClients.createDefault();
        final HttpGet request = new HttpGet(prefix+"/download");
        final URI uri = new URIBuilder(request.getUri()).addParameter("dir", sourceFile.getFileName().toString()).build();
        System.out.println(uri);

        request.setUri(uri);

        try (CloseableHttpResponse response = client.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try (FileOutputStream os = new FileOutputStream(myFile)) {
                    entity.writeTo(os);
                }
            }
        }
    }
}