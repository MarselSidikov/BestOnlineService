package models;

/**
 * 06.05.2017
 * Message
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Message {

    private int id;
    private String text;
    private int chat;
    private User author;

    public Message(Builder builder){
        this.id = id;
        this.text = text;
        this.chat= chat;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public static class Builder {

        private int id;
        private String text;
        private int chat;
        private User author;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder chat(int chat) {
            this.chat = chat;
            return this;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

        @Override
        public String toString() {
            return id + " "
                    + text + " "
                    + chat + " "
                    + author;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Message message = (Message) o;

            if (id != message.id) return false;
            if (!text.equals(message.text)) return false;
            return author.equals(message.author);
        }


    }
}
