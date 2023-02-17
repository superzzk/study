package com.zzk.filesynchronizer;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        Path origin = Paths.get("/Users/Kun/Downloads/file-synchronizer");
        Path target = Paths.get("/Users/Kun/Downloads/new");

        Files.copy(origin, target.resolve(origin.getFileName()));

    }
}
