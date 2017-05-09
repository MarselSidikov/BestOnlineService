import dao.ChatDao;
import dao.ChatDaoJdbcImpl;
import dao.MessageDaoJdbcImpl;
import models.Chat;
import models.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis.spring\\context.xml");
        ChatDaoJdbcImpl chatDao = context.getBean(ChatDaoJdbcImpl.class);
//        List<Chat> chatList = (List<Chat>) chatDao.add();
//        System.out.println(chatList);
        MessageDaoJdbcImpl messageDao = context.getBean(MessageDaoJdbcImpl.class);


    }
}
