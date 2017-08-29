package co.inventorsoft.springmvc.repository;

import co.inventorsoft.springmvc.model.SongInfo;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultSongInfoRepository implements SongInfoRepository {

    private static final List<SongInfo> storage;

    static {
        storage = new ArrayList<>();
        storage.add(new SongInfo(1L, "Місячне колесо", "Іво Бобул", Duration.ofMinutes(4),
                Arrays.asList("Треш", "Угар", "Содомія"), null));
        storage.add(new SongInfo(2L, "Золото карпат", "Іван Попович", Duration.ofMinutes(4),
                Arrays.asList("Гуцульський поп", "Фист"), null));
        storage.add(new SongInfo(3L, "Цей сон", "Степан Гіга", Duration.ofMinutes(3),
                Arrays.asList("Вусатий поп", "К.А.Л."), null));
        storage.add(new SongInfo(3L, "Yomaha yomaso", "Modern Talking", Duration.ofMinutes(3),
                Arrays.asList("Disco", "Classic"), null));
    }

    @Override
    public List<SongInfo> findAll() {
        return storage;
    }

    @Override
    public SongInfo save(SongInfo songInfo) {
        songInfo.setId((long) storage.size());
        storage.add(songInfo);
        return songInfo;
    }

    @Override
    public Optional<SongInfo> findByName(String name) {
        return storage.stream().filter(info -> info.getTrackName().equals(name)).findAny();
    }

    @Override
    public Optional<SongInfo> findById(Long id) {
        return storage.stream().filter(info -> info.getId().equals(id)).findAny();
    }

    @Override
    public boolean update(Long id, SongInfo updates) {
        final Optional<SongInfo> matchSongOptional = findById(id);
        matchSongOptional.ifPresent(info -> info.update(updates));
        return matchSongOptional.isPresent();
    }

    @Override
    public boolean remove(Long id) {
        return storage.removeIf(info -> info.getId().equals(id));
    }
}
