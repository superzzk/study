package com.zzk.filesynchronizer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO:
 *
 * @author zhangzhongkun02
 * @date 2022/5/4 11:45 AM
 */
@RestController
@RequestMapping("download")
public class FileDownloadController {

    Logger log = LoggerFactory.getLogger(FileDownloadController.class);

    @Value("${base_dir}")
    private String baseDir;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam String dir) throws IOException {
        log.info("enter download: param:{}", dir);
        Path path = Paths.get(baseDir + dir);
        log.info("download file path: {}", path);
        HttpHeaders headers = new HttpHeaders();
        /*
         * In a regular HTTP response, the Content-Disposition response header is a
         * header indicating if the content is expected to be displayed inline in the
         * browser, that is, as a Web page or as part of a Web page, or as an
         * attachment, that is downloaded and saved locally.
         *
         */
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName() + "\"");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(path.toFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
