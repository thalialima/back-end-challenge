package br.com.alura.aluraflix.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alura.aluraflix.model.Video;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VideoRepositoryTest {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    public void shouldGetAVideoByName(){
        String videoName = "O que são os Beans do Spring?";
        Video video = videoRepository.findByName(videoName).get();
        assertNotNull(video);
        assertEquals(videoName, video.getName());
    }

    @Test
    public void shouldGetAListVideoByCategoryId(){
        Long categoryId = Long.parseLong(String.valueOf(4));
        List<Video> list = videoRepository.findByCategoryId(categoryId);
        assertNotNull(list);
    }
}
