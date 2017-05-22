package models;

import javafx.geometry.Pos;

/**
 * 11.05.2017
 * Post @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class Post {
    private int idPost;
    private User author;
    private String data;
    private String textPost;
    private String imagePost;

    public static class Builder {
        private int idPost;
        private User author;
        private String data;
        private String textPost;
        private String imagePost;

        public Builder() {

        }

        public Builder idPost(int idPost) {
            this.idPost = idPost;
            return this;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }

        public Builder data(String data) {
            this.data = data;
            return this;
        }

        public Builder textPost(String textPost) {
            this.textPost = textPost;
            return this;
        }

        public Builder imagePost(String imagePost) {
            this.imagePost = imagePost;
            return this;
        }

        public Post build() {
            return new Post(this);

        }

    }

    private Post(Post.Builder builder) {
        this.idPost = builder.idPost;
        this.author = builder.author;
        this.data = builder.data;
        this.textPost = builder.textPost;
        this.imagePost = builder.imagePost;

    }

    public int getIdPost() {
        return idPost;
    }

    public User getAuthor() {
        return author;
    }

    public String getData() {
        return data;
    }

    public String getTextPost() {
        return textPost;
    }

    public String getImagePost() {
        return imagePost;
    }

    public String toString() {
        return idPost + " "
                + author + " "
                + data + " "
                + textPost + " "
                + imagePost + " ";
    }


    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Post) {
            Post that = (Post) obj;
            return this.idPost == that.idPost
                    && this.author.equals(that.author)
                    && this.data.equals(that.data)
                    && this.textPost.equals(that.textPost)
                    && this.imagePost.equals(that.imagePost);

        } return false;
    }
}