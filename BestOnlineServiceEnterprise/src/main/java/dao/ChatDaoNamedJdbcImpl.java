package dao;

import models.Chat;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChatDaoNamedJdbcImpl {

    private NamedParameterJdbcTemplate namedParameterTemplate;

    public ChatDaoNamedJdbcImpl(DataSource dataSource) {
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Chat> chatRowMapper = new RowMapper<Chat>() {
        public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
            Chat chat = new Chat.Builder()
                    .chatId(resultSet.getInt("chatId"))
                    .userName(resultSet.getString("userName"))
                    .userId(resultSet.getInt("userId")).build();
            return chat;
        }
    };

    //language=SQL
    private static final String SQL_FIND_CHAT = "SELECT * FROM chat WHERE chatId = :chatId";
    //language=SQL
    private static final String SQL_ADD_CHAT = "INSERT INTO chat(userName, userId) VALUES (:userName, :userId)";
    //language=SQL
    private static final String SQL_DELETE_CHAT = "DELETE FROM chat WHERE chatId = :chatId";
    //language=SQL
    private static final String SQL_UPDATE_CHAT = "UPDATE chat SET userName = :userName, userId = :userId WHERE chatId = :chatId";

    public Chat find(Integer chatId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatId", chatId);
        return (Chat)namedParameterTemplate.query(SQL_FIND_CHAT, params,chatRowMapper);
    }

    public void add(Chat chat) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", chat.getUserName());
        params.put("userId", chat.getUserId());
        namedParameterTemplate.update(SQL_ADD_CHAT, params);
    }

    public void delete(Integer chatId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatId", chatId);
        namedParameterTemplate.query(SQL_DELETE_CHAT, params,chatRowMapper);

    }

    public void update(Chat chat) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", chat.getUserName());
        params.put("userId", chat.getUserId());
        namedParameterTemplate.update(SQL_UPDATE_CHAT, params);
    }
}
