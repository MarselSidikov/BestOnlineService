package dao;

import models.Chat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChatDaoJdbcImpl implements ChatDao{

    private JdbcTemplate template;

    public ChatDaoJdbcImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
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

    public void add(Chat chat) {
        template.update(SQL_ADD_CHAT, chatRowMapper);
    }

    public void delete(Integer chatId) {
        template.query(SQL_DELETE_CHAT, chatRowMapper, chatId);

    }

    public void update(Chat chat) {
        template.update(SQL_UPDATE_CHAT, chatRowMapper);

    }
}
