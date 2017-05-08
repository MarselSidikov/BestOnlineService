package dao;

import models.Message;

import java.util.List;

public interface MessageDao {

    void add(Message message);
    void update(Message message);
    Message findById(Integer messageId);
    List<Message> findByChatId(Integer chatId);
}
