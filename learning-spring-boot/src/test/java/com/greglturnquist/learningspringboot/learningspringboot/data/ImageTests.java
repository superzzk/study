package com.greglturnquist.learningspringboot.learningspringboot.data;

import com.greglturnquist.learningspringboot.learningspringboot.Image;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 15:50
 **/
public class ImageTests {
    @Test
    public void imagesManagedByLombokShouldWork() {
        Image image = new Image("id", "file-name.jpg");
        assertThat(image.getId()).isEqualTo("id");
        assertThat(image.getName()).isEqualTo("file-name.jpg");
    }
}