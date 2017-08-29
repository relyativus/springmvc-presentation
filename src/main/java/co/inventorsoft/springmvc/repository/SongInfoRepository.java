package co.inventorsoft.springmvc.repository;

import co.inventorsoft.springmvc.model.SongInfo;

import java.util.List;
import java.util.Optional;

public interface SongInfoRepository {

    List<SongInfo> findAll();

    SongInfo save(final SongInfo songInfo);

    Optional<SongInfo> findByName(final String name);

    Optional<SongInfo> findById(final Long id);

    boolean update(final Long id, final SongInfo newInfo);

    boolean remove(final Long id);
}
