package dao;

import models.Chat;

public interface ChatDao {

    void find(Integer chatId);
    void add(Chat chat);
    void delete(Integer chatId);
    void update(Chat chat);

}
