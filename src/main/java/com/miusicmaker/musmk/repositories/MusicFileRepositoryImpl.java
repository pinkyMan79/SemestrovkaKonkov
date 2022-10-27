package com.miusicmaker.musmk.repositories;

import com.miusicmaker.musmk.model.MusicFile;
import com.miusicmaker.musmk.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    private static final Function<ResultSet, MusicFile> mapper = row -> {

        try {

            Long id = row.getLong("f_id");
            String path_to_file = row.getString("path_to_file");
            Long u_id = row.getLong("u_id");
            String m_name = row.getString("m_name");

            return MusicFile.builder().uId(u_id).fId(id).name(m_name).path(path_to_file).build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };

    @Override
    public List<MusicFile> findAll(Long userId) {

        List<MusicFile> musicFiles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BY_USER_ID);
            ResultSet set = statement.executeQuery();
        ){

            statement.setLong(1, userId);

            while (set.next()){

                musicFiles.add(mapper.apply(set));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musicFiles;

    }

    @Override
    public MusicFile findById(Long id) {

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        ){

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();

            if (set.next()){

                return mapper.apply(set);

            }else {

                throw new RuntimeException();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public MusicFile findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME);
        ){

            statement.setString(1, name);

            ResultSet set = statement.executeQuery();

            if (set.next()){

                return mapper.apply(set);

            }else {

                throw new RuntimeException();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(MusicFile musicFile) {

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, musicFile.getPath());
            statement.setLong(2, musicFile.getUId());
            statement.setString(3, musicFile.getName());


            int afRows = statement.executeUpdate();

            if (afRows != 1){

                throw new SQLException("NO AFFECTED ROWS TROUBLE IN: U ");
            }



            ResultSet keygen = statement.getGeneratedKeys();

            if (keygen.next()){

                musicFile.setFId(keygen.getLong("f_id"));

            }else {

                throw new RuntimeException("keygen was failed");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(MusicFile musicFile) {

    }

    @Override
    public void delete(Long id) {

    }
}
