package com.miusicmaker.musmk.repositories;

import com.miusicmaker.musmk.model.Message;
import com.miusicmaker.musmk.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MsgRepositoryImpl implements MsgRepository {

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM messages";

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM messages WHERE m_id = ?";

    //language=SQL
    private final String SQL_SAVE_MSG = "INSERT INTO messages(m_text, m_date, uu_id, sender)  VALUES(?,?,?,?)";

    //language=SQL
    private final String SQL_UPDATE_MSG = "";

    //language=SQL
    private final String SQL_DELETE_MSG = ""; // in next version (

    private DataSource dataSource;

    private Function<ResultSet, Message> messageFunction = row -> {
        try {
            return Message.builder().sender(row.getString("sender"))
                    .mId(row.getLong("m_id"))
                    .msgRoot(row.getString("m_text"))
                    .uId(row.getLong("uU_id"))
                    .date(row.getString("m_date"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public  MsgRepositoryImpl(DataSource dataSource){

        this.dataSource = dataSource;


    }

    @Override
    public List<Message> findAll() {

        List<Message> msgs = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(SQL_FIND_ALL)
        ){

            int countOfRows = 0;

            while (set.next()){

                msgs.add(messageFunction.apply(set));

                countOfRows ++;

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return msgs;

    }

    @Override
    public Message findById(Long id) {

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)
        ){

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            return messageFunction.apply(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Message u) {

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_MSG, Statement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, u.getMsgRoot());
            statement.setString(2, u.getDate());
            statement.setLong(3, u.getUId());
            statement.setString(4, u.getSender());

            System.out.println(u.toString());

            int afRows = statement.executeUpdate();

            if (afRows != 1){

                System.out.println("NO AFFECTED ROWS TROUBLE IN: METHOD SAVE");
                throw new SQLException("NO AFFECTED ROWS TROUBLE IN: METHOD SAVE");
            }

            System.out.println("point: ID GENERATION");
            // ssh like be (:
            ResultSet keygen = statement.getGeneratedKeys();

            if (keygen.next()){

                u.setMId(keygen.getLong("m_id"));

            }else {

                throw new RuntimeException("keygen was failed");

            }

            System.out.println("ID GENERATED: " + u.getMId());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Message u) {

    }

    @Override
    public void delete(Long id) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
