package com.miusicmaker.musmk.repositories;

import com.miusicmaker.musmk.model.Message;
import com.miusicmaker.musmk.model.MusicFile;

import java.util.List;

public interface MusicFileRepository {

    List<MusicFile> findAll(Long userId);

    MusicFile findById(Long id);

    MusicFile findByName(String name);

    void save(MusicFile musicFile);

    void update(MusicFile musicFile);

    void delete(Long id);


}
