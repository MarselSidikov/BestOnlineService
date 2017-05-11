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
        chatDao.delete(1);
//        List<Chat> chatList = chatDao.add();
//        System.out.println(chatList);
//        MessageDaoJdbcImpl messageDao = context.getBean(MessageDaoJdbcImpl.class);
//        List<Message> messageList = messageDao.add();
//        System.out.println(messageList);


    }
}