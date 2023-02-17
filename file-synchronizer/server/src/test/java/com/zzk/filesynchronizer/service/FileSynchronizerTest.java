package com.zzk.filesynchronizer.service;

import com.zzk.filesynchronizer.service.FileSynchronizer.FileInfo;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileSynchronizerTest {

    private static final String HOME = System.getProperty("user.home");
    Path testSourcePath = Paths.get(HOME).resolve("test/from");
    Path testTargetPath = Paths.get(HOME).resolve("test/to");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectory(testSourcePath);
        Files.createDirectory(testTargetPath);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteDirectory(testSourcePath);
        deleteDirectory(testTargetPath);
    }

    private void deleteDirectory(Path path) throws IOException {
        //make sure del only test file
        assertThat(path.startsWith(testSourcePath) || path.startsWith(testTargetPath)).isTrue();
        FileUtils.deleteDirectory(path.toFile());
    }

    @Test
    void needSyncFiles_non_subDir_destination_empty() throws IOException {
        final Path sourceFile = Files.createTempFile(testSourcePath, null, null);

        FileSynchronizer synchronizer = new FileSynchronizer();
        final List<FileInfo> result = synchronizer.needSyncFiles(testSourcePath, testTargetPath);

        final FileInfo fileInfo = new FileInfo(testSourcePath.toString(),testSourcePath.relativize(sourceFile).toString(), sourceFile);
        assertThat(result).contains(fileInfo);
    }

    /**
     * 只考虑普通文件，不考虑目录，当目标文件修改时间小于新文件时，应该添加到同步文件列表
     * */
    @Test
    void needSyncFiles_non_subDir_with_existFile_old() throws IOException, InterruptedException {
        //first create a file in target folder, so the target file lastModified time less
        final Path targetFile = Files.createTempFile(testTargetPath, null, null);

        //second create a file in source folder with same file name
        final Path sourceFilePath = testSourcePath.resolve(targetFile.getName(targetFile.getNameCount()-1).toString());
        final Path sourceFile = Files.createFile(sourceFilePath);
        assertThat(sourceFile.toFile().setLastModified(targetFile.toFile().lastModified() + 1000)).isTrue();

        assertThat(targetFile.toFile().lastModified()).isLessThan(sourceFile.toFile().lastModified());

        FileSynchronizer synchronizer = new FileSynchronizer();
        final List<FileInfo> result = synchronizer.needSyncFiles(testSourcePath, testTargetPath);
        final FileInfo fileInfo = new FileInfo(testSourcePath.toString(),testSourcePath.relativize(sourceFile).toString(), sourceFile);

        assertThat(result).contains(fileInfo);
    }

    @Test
    void needSyncFiles_non_subDir_with_existFile_new() throws IOException {
        //create a file in source folder, so the source file older than target
        final Path sourceFile = Files.createTempFile(testSourcePath, null, null);

        //second create a file in target folder, same file name
        final Path targetFilePath = testTargetPath.resolve(sourceFile.getName(sourceFile.getNameCount() - 1));
        final Path targetFile = Files.createFile(targetFilePath);

        FileSynchronizer synchronizer = new FileSynchronizer();
        final List<FileInfo> result = synchronizer.needSyncFiles(testSourcePath, testTargetPath);

        assertThat(result.size()).isEqualTo(0);
    }

    /**
     * 源路径存在子目录
     * */
    @Test
    void needSyncFiles_with_subDir() throws IOException {
        // mkdir in source folder, with one temp file
        final Path sourceTempDirectory = Files.createTempDirectory(testSourcePath, null);
        final Path sourceFile = Files.createTempFile(sourceTempDirectory, null, null);

        FileSynchronizer synchronizer = new FileSynchronizer();
        final List<FileInfo> result = synchronizer.needSyncFiles(testSourcePath, testTargetPath);

        final FileInfo fileInfo = new FileInfo(testSourcePath.toString(),testSourcePath.relativize(sourceFile).toString(), sourceFile);
        assertThat(result).contains(fileInfo);
    }

    @Test
    void needSyncFiles_with_fileList_empty() throws IOException {
        final Path sourceFile = Files.createTempFile(testSourcePath, null, null);

        FileSynchronizer synchronizer = new FileSynchronizer();
        final List<FileInfo> result = synchronizer.needSyncFiles(new ArrayList<>());

        final FileInfo fileInfo = new FileInfo(testSourcePath.toString(),testSourcePath.relativize(sourceFile).toString(), sourceFile);
        assertThat(result).containsExactly(fileInfo);
    }

    @Test
    void needSyncFiles_with_fileList_contains_order_version_file() throws IOException {
        final Path sourceFile = Files.createTempFile(testSourcePath, null, null);
        final String sourceFileRelativePath = testSourcePath.relativize(sourceFile).toString();

        FileSynchronizer synchronizer = new FileSynchronizer();
        List<FileInfo> destinationFiles = new ArrayList<>();
        destinationFiles.add(new FileInfo("whatever", sourceFileRelativePath, sourceFile.toFile().lastModified()-1000, sourceFile.toFile().length()));

        final List<FileInfo> result = synchronizer.needSyncFiles(destinationFiles);

        final FileInfo fileInfo = new FileInfo(testSourcePath.toString(), sourceFileRelativePath, sourceFile);
        assertThat(result).containsExactly(fileInfo);
    }
}