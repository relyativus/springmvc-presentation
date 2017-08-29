package co.inventorsoft.springmvc.service;

import co.inventorsoft.springmvc.model.SongInfo;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<SongInfo> getSongs();

    SongInfo saveSong(final SongInfo songInfo);

    Optional<SongInfo> findByName(final String name);

    Optional<SongInfo> findById(final Long id);

    boolean update(final Long id, final SongInfo updates);

    boolean remove(final Long id);
}
