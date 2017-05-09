package dao;

import models.Chat;

public interface ChatDao {

    Chat find(Integer chatId);
    void add(Chat chat);
    void delete(Integer chatId);
    void update(Chat chat);

}
