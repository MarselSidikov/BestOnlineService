package models;

/**
 * 13.05.2017
 * Login
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */
public class Login {

    private int id;
    private String login;
    private String password;

    public Login(Builder builder) {

        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;

    }

    public static class Builder {

        private int id;
        private String login;
        private String password;

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

        public Login build(){
            return new Login(this);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Override
    public String toString() {
        return id +
                " " + login +
                " " + password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null || obj instanceof Login) {
            Login that = (Login) obj;
            return this.id == that.id &&
                    this.login.equals(that.login) &&
                    this.password.equals(that.login);
        } return false;
    }
}
