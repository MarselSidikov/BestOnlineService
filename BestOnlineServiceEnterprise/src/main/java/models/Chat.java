package models;

/**
 * 06.05.2017
 * Chat
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Chat {

    private int id;
    private User creator_id;
    private String name;

    public Chat(Builder builder) {
        this.creator_id = creator_id;
        this.name = name;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(User creator_id) {
        this.creator_id = creator_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return creator_id + " "
                + name + " "
                + id;
    }

    public static class Builder {

        private int creator_id;
        private String name;
        private int id;


        public Builder() {
        }

        public Builder creator_id(int creator_id) {
            this.creator_id = creator_id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }


        public Chat build() {
            return new Chat(this);
        }
    }
}
