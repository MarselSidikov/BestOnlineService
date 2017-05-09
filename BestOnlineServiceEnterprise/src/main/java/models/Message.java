package models;

/**
 * 06.05.2017
 * Message
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Message {

    private int messageId;
    private String text;
    private int chatId;
    private int userId;


    public Message(Builder builder) {
        this.messageId = messageId;
        this.text = text;
        this.chatId = chatId;
        this.userId = userId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return messageId + " "
                + text + " "
                + chatId + " "
                + userId;
    }


    public static class Builder {

        public Builder() {
        }

        private int messageId;
        private String text;
        private int chatId;
        private int userId;


        public Builder messageId(int messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder chatId(int chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
