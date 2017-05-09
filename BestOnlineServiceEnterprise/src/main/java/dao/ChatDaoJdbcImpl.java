package dao;

import models.Chat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChatDaoJdbcImpl implements ChatDao{

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedParameterTemplate;

    public ChatDaoJdbcImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

        private RowMapper<Chat> chatRowMapper = new RowMapper<Chat>() {
        public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
            Chat chat = new Chat.Builder()
                    .chatId(resultSet.getInt("chat_id"))
                    .userName(resultSet.getString("userName"))
                    .userId(resultSet.getInt("user_id")).build();
            return chat;
        }
    };
    //language=SQL
    private static final String SQL_FIND_CHAT = "SELECT * FROM chat WHERE chat_id = :chatId";
    //language=SQL
    private static final String SQL_ADD_CHAT = "INSERT INTO chat(userName, user_id) VALUES (:userName, :userId)";
    //language=SQL
    private static final String SQL_DELETE_CHAT = "DELETE FROM chat WHERE chat_id = :chatId";
    //language=SQL
    private static final String SQL_UPDATE_CHAT = "UPDATE chat SET userName = :userName, user_id = :userId WHERE chat_id = :chatId";

//    //language=SQL
//    private static final String SQL_FIND_CHAT = "SELECT * FROM chat WHERE chat_id = ?";
//    //language=SQL
//    private static final String SQL_ADD_CHAT = "INSERT INTO chat(userName, user_id) VALUES (?, ?)";
//    //language=SQL
//    private static final String SQL_DELETE_CHAT = "DELETE FROM chat WHERE chat_id = ?";
//    //language=SQL
//    private static final String SQL_UPDATE_CHAT = "UPDATE chat SET userName = ?, user_id = ? WHERE chat_id = ?";

    public Chat find(Integer chatId) {
//        return  template.query(SQL_FIND_CHAT,chatRowMapper, chatId);
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
//        template.query(SQL_DELETE_CHAT, chatRowMapper, chatId);
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
