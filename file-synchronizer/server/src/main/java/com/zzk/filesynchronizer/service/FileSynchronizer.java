package com.zzk.filesynchronizer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileSynchronizer {

    private static final String REPO = "/Users/kun/test/from";

    public List<FileInfo> needSyncFiles(Path origin, Path target) {
        List<FileInfo> result = new ArrayList<>();
        String baseDir = Paths.get(REPO).toString();
        try {
            Files.walk(origin).forEach(path -> {
                if (Files.isRegularFile(path)) {
                    final Path targetFile = path.resolve(path.relativize(target)).resolve(path.getName(path.getNameCount() - 1)).normalize();
                    if (!Files.exists(targetFile) ||
                            targetFile.toFile().lastModified() < path.toFile().lastModified()) {
                        result.add(new FileInfo(baseDir,Paths.get(REPO).relativize(path).toString(), path));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<FileInfo> needSyncFiles(List<FileInfo> sourceFiles) {
        List<FileInfo> result = new ArrayList<>();
        String baseDir = Paths.get(REPO).toString();
        try {
            Files.walk(Paths.get(REPO)).forEach(path -> {
                if(Files.isRegularFile(path)){
                    String relativePath = Paths.get(REPO).relativize(path).toString();
                    final Optional<FileInfo> any = sourceFiles.stream().filter(f -> f.equals(new FileInfo(baseDir,relativePath,path))).findAny();
                    if(!any.isPresent() || any.get().getModifiedTime() < path.toFile().lastModified()){
                        result.add(new FileInfo(baseDir, relativePath, path));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @AllArgsConstructor
    public static class FileInfo {
        private String baseDir;
        private String relativePath;
        private long modifiedTime;
        private long len;

        public FileInfo(String baseDir, String relativePath, Path path) {
            this.baseDir = baseDir;
            this.relativePath = relativePath;
            modifiedTime = path.toFile().lastModified();
            len = path.toFile().length();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof FileInfo)) return false;
            return relativePath.equals(((FileInfo) obj).getRelativePath()) &&
                    modifiedTime == (((FileInfo) obj).getModifiedTime()) &&
                    len == ((FileInfo) obj).getLen();
        }

        @Override
        public String toString() {
            return "FileInfo{" +
                    "name='" + relativePath + '\'' +
                    ", modifiedTime=" + modifiedTime +
                    ", len=" + len +
                    '}';
        }

        public String getRelativePath() {
            return relativePath;
        }

        public void setRelativePath(String relativePath) {
            this.relativePath = relativePath;
        }

        public long getModifiedTime() {
            return modifiedTime;
        }

        public void setModifiedTime(long modifiedTime) {
            this.modifiedTime = modifiedTime;
        }

        public long getLen() {
            return len;
        }

        public void setLen(long len) {
            this.len = len;
        }
    }

}


