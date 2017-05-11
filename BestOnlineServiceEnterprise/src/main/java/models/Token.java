package models;

/**
 * 11.05.2017
 * Token
 *
 * @author Aivar Yusupov
 * @version v0.1
 */
public class Token {

    private String token;

    @Override
    public String toString() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null || obj instanceof Token) {
            if (obj == this) return true;
            UserAuth that = (UserAuth obj);
            return this.token.equals(that.token);
        } return false;
    }
}
