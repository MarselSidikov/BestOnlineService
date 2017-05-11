package models;

/**
 * 06.05.2017
 * Chat
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Chat {
<<<<<<< HEAD

    private int chatId;
    private String userName;
    private int userId;


    public Chat(Builder builder) {
        this.chatId = chatId;
        this.userName = userName;
        this.userId = userId;

    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return chatId + " "
                + userName + " "
                + userId;
    }

    public static class Builder {

        private int chatId;
        private String userName;
        private int userId;

        public Builder() {
        }

        public Builder chatId(int chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Chat build() {
            return new Chat(this);
        }
    }
=======
>>>>>>> TASK_POSTER_01
}
