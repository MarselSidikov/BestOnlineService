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

    public Chat(Builder builder){
    this.id = id;
    this.creator = creator;
    this.name = name;
    this.users = users;
    this.messages = messages;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static class Builder {

        private int id;
        private User creator;
        private String name;
        private List<User> users;
        private List<Message> messages;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder creator(User creator) {
            this.creator = creator;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder users(List<User> users) {
            this.users = users;
            return this;
        }
        public Builder messages(List<Message> messages) {
            this.messages = messages;
            return this;
        }
        public Chat build(){
            return new Chat(this);
        }

        @Override
        public String toString() {
            return  id + " "
                    + creator + " "
                     + name + " "
                     + users + " "
                    + messages;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            if (id != builder.id) return false;
            if (!creator.equals(builder.creator)) return false;
            if (!name.equals(builder.name)) return false;
            if (!users.equals(builder.users)) return false;
            return messages.equals(builder.messages);
        }


    }
}
