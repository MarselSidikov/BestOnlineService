package dao;

import models.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDaoJdbcImpl implements MessageDao{

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedParameterTemplate;

    public MessageDaoJdbcImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            Message message = new Message.Builder()
                    .messageId(resultSet.getInt("message_id"))
                    .text(resultSet.getString("text"))
                    .chatId(resultSet.getInt("chat_id"))
                    .userId(resultSet.getInt("user_id")).build();

            return message;
        }
    };
    //language=SQL
    private static final String SQL_ADD_MESSAGE = "INSERT INTO message(text, chat_id, user_id) VALUES (:text, :chatId, :userId)";
    //language=SQL
    private static final String SQL_UPDATE_MESSAGE = "UPDATE message SET text = :text WHERE message_id = :messageId";
    //language=SQL
    private static final String SQL_FIND_MESSAGE_BY_CHAT_ID = "SELECT * FROM message JOIN chat ON message.chat_id = chat.chat_id WHERE chat.chat_id = :chatId";
    //language=SQL
    private static final String SQL_FIND_MESSAGE_BY_ID = "SELECT * FROM message WHERE message_id = :messageId";

//    //language=SQL
//    private static final String SQL_ADD_MESSAGE = "INSERT INTO message(text, chat_id, user_id) VALUES (?, ?, ?)";
//    //language=SQL
//    private static final String SQL_UPDATE_MESSAGE = "UPDATE message SET text = ? WHERE message_id = ?";
//    //language=SQL
//    private static final String SQL_FIND_MESSAGE_BY_CHAT_ID = "SELECT * FROM message JOIN chat ON message.chat_id = chat.chat_id WHERE chat.chat_id = ?";
//    //language=SQL
//    private static final String SQL_FIND_MESSAGE_BY_ID = "SELECT * FROM message WHERE message_id = ?";

    public void add(Message message) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", message.getText());
        params.put("chatId", message.getChatId());
        params.put("userId", message.getUserId());
        namedParameterTemplate.update(SQL_ADD_MESSAGE, params);
    }

    public void update(Message message) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", message.getText());
        params.put("messageId", message.getMessageId());
        namedParameterTemplate.update(SQL_UPDATE_MESSAGE, params);
    }

    public Message findById(Integer messageId) {
        Map<String, Object> params = new HashMap<>();
        params.put("messageId", messageId);
        return (Message) namedParameterTemplate.query(SQL_FIND_MESSAGE_BY_ID, params,messageRowMapper);
    }

    public List<Message> findByChatId(Integer chatId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatId", chatId);
        return namedParameterTemplate.query(SQL_FIND_MESSAGE_BY_CHAT_ID, params,messageRowMapper);
    }
}
