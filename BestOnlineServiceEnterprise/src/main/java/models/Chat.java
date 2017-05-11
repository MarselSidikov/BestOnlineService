package models;

import java.util.List;

/**
 * 06.05.2017
 * Chat
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Chat {

    private int id;
    private User creator;
    private String name;
    private List<User> users;
    private List<Message> messages;


}
