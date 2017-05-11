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
                    .messageId(resultSet.getInt("messageId"))
                    .text(resultSet.getString("text"))
                    .chatId(resultSet.getInt("chatId"))
                    .userId(resultSet.getInt("userId")).build();

            return message;
        }
    };

    //language=SQL
    private static final String SQL_ADD_MESSAGE = "INSERT INTO message(text, chatId, userId) VALUES (:text, :chatId, :userId)";
    //language=SQL
    private static final String SQL_UPDATE_MESSAGE = "UPDATE message SET text = :text WHERE messageId = :messageId";
    //language=SQL
    private static final String SQL_FIND_MESSAGE_BY_CHAT_ID = "SELECT * FROM message JOIN chat ON message.chatId = chat.chatId WHERE chat.chatId = :chatId";
    //language=SQL
    private static final String SQL_FIND_MESSAGE_BY_ID = "SELECT * FROM message WHERE messageId = :messageId";


    //language=SQL
    private static final String SQL_ADD_MESSAGE = "INSERT INTO message(text, chatId, userId) VALUES (?, ?, ?)";
    //language=SQL
    private static final String SQL_UPDATE_MESSAGE = "UPDATE message SET text = ? WHERE messageId = ?";
    //language=SQL
    private static final String SQL_FIND_MESSAGE_BY_CHAT_ID = "SELECT * FROM message JOIN chat ON message.chatId = chat.chatId WHERE chat.chatId = ?";
    //language=SQL
    private static final String SQL_FIND_MESSAGE_BY_ID = "SELECT * FROM message WHERE messageId = ?";

    public void save(Message message) {
       template.update(SQL_ADD_MESSAGE, messageRowMapper);
    }
    public void save(Message message) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", message.getText());
        params.put("chatId", message.getChatId());
        params.put("userId", message.getUserId());
        namedParameterTemplate.update(SQL_ADD_MESSAGE, params);
    }

    public void update(Message message) {
        template.update(SQL_UPDATE_MESSAGE, messageRowMapper);
    }
    public void update(Message message) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", message.getText());
        params.put("messageId", message.getMessageId());
        namedParameterTemplate.update(SQL_UPDATE_MESSAGE, params);
    }

    public Message findById(Integer messageId) {
        return (Message) template.query(SQL_FIND_MESSAGE_BY_ID, messageRowMapper);
    }
    public Message findById(Integer messageId) {
        Map<String, Object> params = new HashMap<>();
        params.put("messageId", messageId);
        return (Message) namedParameterTemplate.query(SQL_FIND_MESSAGE_BY_ID, params,messageRowMapper);
    }

    }
}
