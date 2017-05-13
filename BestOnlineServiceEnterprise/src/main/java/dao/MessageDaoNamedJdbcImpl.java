package dao;

import models.Chat;
import models.Message;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MessageDaoNamedJdbcImpl implements MessageDao{


    private NamedParameterJdbcTemplate namedParameterTemplate;

    public MessageDaoNamedJdbcImpl(DataSource dataSource) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
        dataSource = context.getBean(DataSource.class);
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            Message message = new Message.Builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .chat(resultSet.getInt("id"))
                    .build();
            User user = findUserById(resultSet.getInt("id"));
            message.setAuthor(user);
            return message;

        }
    };

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User.Builder()
                    .id(resultSet.getInt("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .build();
            return user;
        }
    };

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO message(text, chat_id, author_id) VALUES (:text, :chat, :author)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE message SET text = :text WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM message  WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM message";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM message  WHERE id = :id";
    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM service_user WHERE id=:id";


    public Message find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return (Message)namedParameterTemplate.query(SQL_FIND, params,messageRowMapper);
    }

    public int save(Message message) {
         MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("text", message.getText())
                .addValue("chat", message.getChat())
                .addValue("author", message.getAuthor());

        final KeyHolder holder = new GeneratedKeyHolder();
        namedParameterTemplate.update(SQL_SAVE, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterTemplate.update(SQL_DELETE, params);
    }

    public List<Message> findAll() {
        return namedParameterTemplate.query(SQL_FIND_ALL,messageRowMapper);
    }

    public void update(Message message) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", message.getText());
        namedParameterTemplate.update(SQL_UPDATE, params);
    }
    private User findUserById(int id ){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        List<User> users = namedParameterTemplate.query(SQL_SELECT_USER_BY_ID,userRowMapper);
        return users.get(0);
    }
}
