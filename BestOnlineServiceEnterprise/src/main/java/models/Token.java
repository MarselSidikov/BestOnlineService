package models;

/**
 * 11.05.2017
 * Token
 *
 * @author Aivar Yusupov
 * @version v0.1
 */
public class Token {

    private int id;
    private String token;

    public Token(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
    }

    public static class Builder {

        private int id;
        private String token;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return id +
                " " + token;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null || obj instanceof Token) {
            if (obj == this) return true;
            Token that = (Token) obj;
            return this.id == that.id &&
                    this.token.equals(that.token);
        } return false;
    }
}
