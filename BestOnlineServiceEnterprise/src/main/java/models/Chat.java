package models;

/**
 * 06.05.2017
 * Chat
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Chat {

    private int chatId;
    private String userName;
    private int userId;

    public Chat(){}

    public Chat(int chatId, String userName,int userId) {
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
    public static Builder newBuilder() {
        return new Chat().new Builder();
    }
    public class Builder {

        private int chatId;
        private String userName;
        private int userId;

        public Builder() {
        }

        public Builder ChatId(int chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder UserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder UserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Chat build() {
            return Chat.this;
        }
    }
}
