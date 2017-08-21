package co.inventorsoft.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongInfo {

    private Long id;

    private String trackName;

    private String author;

    private Duration duration;

    private List<String> genres;

    private byte[] content;

    @SneakyThrows
    public void setContent(MultipartFile multipartFile) {
        this.content = multipartFile.getBytes();
    }
}
