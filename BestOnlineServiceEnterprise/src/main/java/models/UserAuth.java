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
    private Login login;
    private Login password;
    private Token token;

    public UserAuth(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.token = builder.token;
    }

    public static class Builder {
        private int id;
        private Login login;
        private Login password;
        private Token token;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder login(Login login) {
            this.login = login;
            return this;
        }

        public Builder password(Login password) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getPassword() {
        return password;
    }

    public void setPassword(Login password) {
        this.password = password;
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
            UserAuth that = (UserAuth) obj;
            return this.id == that.id
                    && this.login.equals(that.login)
                    && this.password.equals(that.password)
                    && this.token.equals(that.token);
        } return false;
    }
}
