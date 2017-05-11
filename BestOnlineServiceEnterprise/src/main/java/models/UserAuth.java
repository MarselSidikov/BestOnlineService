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
    private String login;
    private String password;
    private Token token;

    public UserAuth(Builder builder) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public static class Builder {
        private int id;
        private String login;
        private String password;
        private Token token;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

    public void setId() {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return id +
                " " + login +
                " " + password +
                " " + token;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null || obj instanceof UserAuth) {
            if (obj == this) return true;
            UserAuth that = (UserAuth obj);
            return this.id == that.id
                    && this.login.equals(that.login)
                    && this.password.equals(that.password)
                    && this.token.equals(that.token);
        } return false;
    }
}
