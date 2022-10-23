package com.miusicmaker.musmk.repositories;

import com.miusicmaker.musmk.model.MusicFile;

import javax.sql.DataSource;
import java.util.List;

public class MusicFileRepositoryImpl implements MusicFileRepository {

    //language=SQL
    public final String SQL_FIND_ALL_BY_USER_ID = "SELECT * FROM music_files WHERE u_id = ?";

    //language=SQL
    public final String SQL_FIND_BY_ID = "SELECT * FROM music_files WHERE f_id = ?";

    //language=SQL
    public final String SQL_FIND_BY_NAME = "SELECT * FROM music_files WHERE m_name = ?";

    //language=SQL
    public final String SQL_SAVE = "INSERT INTO music_files(path_to_file, u_id, m_name) VALUES (?, ?, ?)";

    //language=SQL
    public final String SQL_DELETE = "DELETE FROM music_files WHERE f_id = ?";

    private DataSource dataSource;

    public MusicFileRepositoryImpl(DataSource dataSource){

        this.dataSource = dataSource;

    }

    @Override
    public List<MusicFile> findAll(Long userId) {
        return null;
    }

    @Override
    public MusicFile findById(Long id) {
        return null;
    }

    @Override
    public MusicFile findByName(String name) {
        return null;
    }

    @Override
    public void save(MusicFile musicFile) {

    }

    @Override
    public void update(MusicFile musicFile) {

    }

    @Override
    public void delete(Long id) {

    }
}
