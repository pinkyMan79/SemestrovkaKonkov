package com.miusicmaker.musmk.repositories;


import com.miusicmaker.musmk.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserRepositoryImpl implements UserRepository {


    //language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM tester_bencher";

    //language=SQL
    private final String SQL_FIND_USER_BY_ID = "SELECT * FROM tester_bencher WHERE id = ?";

    //language=SQL
    private final String SQL_FIND_USER_BY_E_A_P = "SELECT * FROM tester_bencher WHERE nickname = ? AND  pass = ?";

    //language=SQL
    private final String SQL_SAVE_NEW_USER = "INSERT INTO tester_bencher(email, pass, nickname) VALUES (?,?,?)";

    //language=SQL
    private final String SQL_UPDATE_USER_INFO = "UPDATE tester_bencher SET email = ?, pass = ?, nickname = ? WHERE id = ?";

    // да, можно было через JDBCTemplate, но это всё-таки спринговая фича, так что не в этот раз
    private final DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final Function<ResultSet, User> mapper = row -> {

        try {

            Long id = row.getLong("id");
            String email = row.getString("email");
            String nickname = row.getString("nickname");
            String password = row.getString("pass");

            return User.builder().id(id).email(email).nickname(nickname).password(password).build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL);
        ) {

            int count = 0;

            while (rs.next()){

                users.add(mapper.apply(rs));

                count++;

            }

            if (count == 0){

                System.out.println("No returns(");

            }else {

                System.out.println("returns count: " + count);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public User findById(Long id) {

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER_BY_ID);
        ){

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                System.out.println("FOUNDED");

                return mapper.apply(rs);

            }else {

                System.out.println("NO RESULTS");
                return null;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String email, String password) {

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_FIND_USER_BY_E_A_P);
        ){

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                System.out.println("FOUNDED");

                return mapper.apply(rs);

            }else {

                System.out.println("NO RESULTS");
                return null;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(User u) {

        System.out.println("START SAVING USER");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_NEW_USER, Statement.RETURN_GENERATED_KEYS);
        ){

            System.out.println("get user with parametres:"
                    + "\n" + u.getNickname()
                    + "\n" + u.getEmail()
                    + "\n" + u.getPassword());

            statement.setString(1, u.getEmail());
            statement.setString(2, u.getPassword());
            statement.setString(3, u.getNickname());

            int afRows = statement.executeUpdate();

            if (afRows != 1){

                System.out.println("NO AFFECTED ROWS TROUBLE IN: METHOD SAVE");
                throw new SQLException("NO AFFECTED ROWS TROUBLE IN: METHOD SAVE");
            }

            System.out.println("point: ID GENERATION");
            // ssh be like:)
            ResultSet keygen = statement.getGeneratedKeys();

            if (keygen.next()){

                u.setId(keygen.getLong("id"));

            }else {

                throw new RuntimeException("keygen was failed");

            }

            System.out.println("ID GENERATED: " + u.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(User u) {

        if (u == null){

            throw new RuntimeException("Ах ты пидарас");

        }

        System.out.println("UPDATE USER " + u.getNickname() + "WAS STARTED");

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_INFO);)
        {

            statement.setString(1, u.getEmail());
            statement.setString(2, u.getPassword());
            statement.setString(3, u.getNickname());
            statement.setLong(4, u.getId());

            int afRows = statement.executeUpdate();

            if (afRows != 1){

                throw new RuntimeException("UPDATE USER INFO WAS FAILED");

            }else {

                System.out.println("INFO ABOUT USER " + u.getNickname() + " UPDATED TO:" + "\n" + u.getEmail() + "\n" + u.getPassword());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {

        // ну такое себе, в моём приложении такое нет смысла реализовывать, добавил чисто из-за CRUD (пока Interface segregation:| )

    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
