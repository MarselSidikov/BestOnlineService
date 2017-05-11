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
    private int chat_id;
    private int author_id;


    public Message(Builder builder) {
        this.id = id;
        this.text = text;
        this.chat_id = chat_id;
        this.author_id = author_id;
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

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void seAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return id + " "
                + text + " "
                + chat_id + " "
                + author_id;
    }


    public static class Builder {

        public Builder() {
        }

        private int id;
        private String text;
        private int chat_id;
        private int author_id;


        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder chat_id(int chat_id) {
            this.chat_id = chat_id;
            return this;
        }

        public Builder author_id(int author_id) {
            this.author_id = author_id;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
