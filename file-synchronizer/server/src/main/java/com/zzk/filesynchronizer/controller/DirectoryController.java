package com.zzk.filesynchronizer.controller;

import com.zzk.filesynchronizer.service.FileSynchronizer;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/directories")
public class DirectoryController {

    @Autowired
    private FileSynchronizer fileSynchronizer;

    @GetMapping("")
    public List<FileInfo> getFiles(@RequestParam("dir") String dir) {
        System.out.println("yyy");
        final Path path = Paths.get(dir);
        try (final Stream<Path> stream = Files.walk(path)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
