package dao;

import models.Chat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatDaoJdbcImpl implements ChatDao {

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedParameterTemplate;

    public ChatDaoJdbcImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

        private RowMapper<Chat> chatRowMapper = new RowMapper<Chat>() {
        public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
            Chat chat = new Chat.Builder()
                    .id(resultSet.getInt("id"))
                    .creator_id(resultSet.getUser("creator_id"))
                    .name(resultSet.getString("name")).build();
            return chat;
        }
    };

    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM chat WHERE :creator_id = :creator_id";
    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO chat(name, creator_id) VALUES (:name, :creator_id)";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM chat WHERE id = :id";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE chat SET name = :name, id = :id WHERE :creator_id = :creator_id";


    //language=SQL
    private static final String SQL_FIND_CHAT = "SELECT * FROM chat WHERE chatId = ?";
    //language=SQL
    private static final String SQL_ADD_CHAT = "INSERT INTO chat(userName, userId) VALUES (?, ?)";
    //language=SQL
    private static final String SQL_DELETE_CHAT = "DELETE FROM chat WHERE chatId = ?";
    //language=SQL
    private static final String SQL_UPDATE_CHAT = "UPDATE chat SET userName = ?, userId = ? WHERE chatId = ?";

    public Chat find(Integer chatId) {
        return  (Chat)template.query(SQL_FIND_CHAT,chatRowMapper, chatId);
    }

    public Chat find(Integer chatId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatId", chatId);
        return (Chat)namedParameterTemplate.query(SQL_FIND_CHAT, params,chatRowMapper);
    }


    @Override
    public Chat find(int id) {
        return null;
    }

    public void save(Chat chat) {
        template.update(SQL_ADD_CHAT, chatRowMapper);
    }
    public void save(Chat chat) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", chat.getUserName());
        params.put("userId", chat.getUserId());
        namedParameterTemplate.update(SQL_ADD_CHAT, params);
    }

    public void delete(Integer chatId) {
        template.query(SQL_DELETE_CHAT, chatRowMapper, chatId);

    }
    public void delete(Integer chatId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatId", chatId);
        namedParameterTemplate.query(SQL_DELETE_CHAT, params,chatRowMapper);

    }
    public void update(Chat chat) {
        template.update(SQL_UPDATE_CHAT, chatRowMapper);

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Chat> findAll() {
        return null;
    }

    public void update(Chat chat) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", chat.getName());
        params.put("userId", chat.getUserId());
        namedParameterTemplate.update(SQL_UPDATE_CHAT, params);
    }
}
