package models;

/**
 * 10.05.2017
 * UserAuth
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */
public class UserAuth {

    private int id;
    private User user;
    private Token token;

    public UserAuth(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.token = builder.token;
    }

    public static class Builder {
        private int id;
        private User user;
        private Token token;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder token(Token token) {
            this.token = token;
            return this;
        }

        public UserAuth build() {
            return new UserAuth(this);
        }
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return id +
                " " + user +
                " " + token;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null || obj instanceof UserAuth) {
            if (obj == this) return true;
            UserAuth that = (UserAuth) obj;
            return this.id == that.id
                    && this.user.equals(that.user)
                    && this.token.equals(that.token);
        } return false;
    }
}
