package co.inventorsoft.springmvc.service;

import co.inventorsoft.springmvc.model.SongInfo;
import co.inventorsoft.springmvc.repository.SongInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultSongService implements SongService {

    private SongInfoRepository songInfoRepository;

    @Override
    public Optional<SongInfo> findById(Long id) {
        return songInfoRepository.findById(id);
    }

    @Override
    public List<SongInfo> getSongs() {
        return songInfoRepository.findAll();
    }

    @Override
    public SongInfo saveSong(final SongInfo songInfo) {
        return songInfoRepository.save(songInfo);
    }

    @Override
    public Optional<SongInfo> findByName(final String name) {
        return songInfoRepository.findByName(name);
    }
}
