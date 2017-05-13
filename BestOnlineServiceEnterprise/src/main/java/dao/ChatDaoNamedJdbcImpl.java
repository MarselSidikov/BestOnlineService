package dao;

import models.Chat;
import models.Message;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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


public class ChatDaoNamedJdbcImpl implements BaseDao<Chat> {

    private NamedParameterJdbcTemplate namedParameterTemplate;

    public ChatDaoNamedJdbcImpl(DataSource dataSource) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
        dataSource = context.getBean(DataSource.class);
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

        private RowMapper<Chat> chatRowMapper = new RowMapper<Chat>() {
        public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
            Chat chat = new Chat.Builder()
                    .id(resultSet.getInt("id"))
                    .creator((User)resultSet.getObject("creator_id"))
                    .name(resultSet.getString("name"))
                    .users((List<User>) resultSet.getObject("users"))
                    .messages((List<Message>) resultSet.getObject("messages"))
                    .build();
            return chat;
        }
    };

    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM chat WHERE id = :id";
    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO chat(name, creator_id) VALUES (:name, :creator)";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM chat WHERE id = :id";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE chat SET name = :name,:creator_id = :creator WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM chat";


    public Chat find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return (Chat)namedParameterTemplate.query(SQL_FIND, params,chatRowMapper);
    }

    public int save(Chat chat) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("creator", chat.getCreator())
                .addValue("name", chat.getName());
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

    public List<Chat> findAll() {
        return namedParameterTemplate.query(SQL_FIND_ALL,chatRowMapper);
    }

    public void update(Chat chat) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", chat.getName());
        params.put("creator", chat.getCreator());
        namedParameterTemplate.update(SQL_UPDATE, params);
    }
}
