package co.inventorsoft.springmvc.web;

import co.inventorsoft.springmvc.model.SongInfo;
import co.inventorsoft.springmvc.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.List;

/**
 * Spring MVC controller which shows different Spring web support concepts.
 * This controller shows mixed url definition, request parameter handling and rendering
 * different types of response
 */
@AllArgsConstructor
@Controller
@RequestMapping(value = "/songs")
public class SongsLibraryController {

    private SongService songService;

    /**
     * Simple mapping example
     *
     * @return returns all song infos
     */
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SongInfo> getSongs() {
        return songService.getSongs();
    }

    @GetMapping(value = "/index.php")
    public ModelAndView showSongs(final ModelAndView modelAndView) {
        modelAndView.addObject("songs", songService.getSongs());
        modelAndView.setViewName("songs");
        return modelAndView;
    }

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<SongInfo> getSongByName(@RequestParam String name) {
        return renderSongByName(name);
    }

    @GetMapping(value = "/search", headers = "Song-Name")
    public ResponseEntity<SongInfo> getSongByNameInHeader(@RequestHeader("Song-Name") String name) {
        return renderSongByName(name);
    }

    @GetMapping("/{songId:\\d+}")
    public ResponseEntity<SongInfo> getSongById(@PathVariable Long songId) {
        return songService.findById(songId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SongInfo> createSongFromUrlEncoded(@ModelAttribute SongInfo songInfo) {
        final SongInfo createdSong = songService.saveSong(songInfo);
        return renderResponseWithLocation(createdSong);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SongInfo> createSongFromJson(@RequestBody SongInfo songInfo) {
        final SongInfo createdSong = songService.saveSong(songInfo);
        return renderResponseWithLocation(createdSong);
    }


    private ResponseEntity<SongInfo> renderSongByName(@RequestParam String name) {
        return songService.findByName(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<SongInfo> renderResponseWithLocation(SongInfo createdSong) {
        return ResponseEntity.created(URI.create(String.format("/songs/%d", createdSong.getId()))).body(createdSong);
    }

}
