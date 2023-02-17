package com.greglturnquist.learningspringboot.learningspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 11:47
 **/
@Service
public class ImageService {
    // This is the base folder where images will be stored.
    private static String UPLOAD_ROOT = "upload-dir";
    /*
     * This is a Spring utility class used to manage files. It is created
     * automatically by Spring Boot and injected to our service via constructor
     * injection. This ensures our service starts off with a consistent state
     * */
    private final ResourceLoader resourceLoader;

    public ImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Pre-load some test images
     * *
     *
     * @return Spring Boot {@link CommandLineRunner} automatically
     * run after app context is loaded.
     * @Bean indicates that this method will return back an object to be
     * registered as a Spring bean at the time that ImageService is created
     * <p>
     * The bean returned is a CommandLineRunner. Spring Boot runs ALL
     * CommandLineRunners after the application context is fully realized (but not in any
     * particular order)
     * <p>
     * The method deletes the UPLOAD_ROOT directory, creates a new one, then creates
     * three new files with a little bit of text
     */
    @Bean
    CommandLineRunner setUp() throws IOException {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));
            Files.createDirectory(Paths.get(UPLOAD_ROOT));
            FileCopyUtils.copy("Test file",
                    new FileWriter(UPLOAD_ROOT +
                            "/learning-spring-boot-cover.jpg"));
            FileCopyUtils.copy("Test file2",
                    new FileWriter(UPLOAD_ROOT +
                            "/learning-spring-boot-2nd-edition-cover.jpg"));
            FileCopyUtils.copy("Test file3",
                    new FileWriter(UPLOAD_ROOT + "/bazinga.png"));
        };
    }

    public Flux<Image> findAllImages() {
        try {
            return Flux.fromIterable(
                    Files.newDirectoryStream(Paths.get(UPLOAD_ROOT)))
                    .map(path ->
                            new Image(Integer.toString(path.hashCode()), path.getFileName().toString()));
        } catch (IOException e) {
            return Flux.empty();
        }
    }

    // Mono is a container of one
    // Resource is Spring's abstract type for files
    public Mono<Resource> findOneImage(String filename) {
        return Mono.fromSupplier(() ->
                resourceLoader.getResource(
                        "file:" + UPLOAD_ROOT + "/" + filename));
    }

    public Mono<Void> createImage(Flux<FilePart> files) {
        return files.flatMap(file -> file.transferTo(
                Paths.get(UPLOAD_ROOT, file.filename()).toFile())).then();
    }

    public Mono<Void> deleteImage(String filename) {
        return Mono.fromRunnable(() -> {
            try {
                Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
