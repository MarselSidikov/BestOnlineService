package models;

/**
 * 20.05.2017
 * Friend @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class Friend {
    private int idFriend;
    private String nameFriend;


    public static class Builder {
        private int idFriend;
        private String nameFriend;

        public Builder() {

        }

        public Builder idFrinend(int idFriend) {
            this.idFriend = idFriend;
            return this;
        }

        public Builder nameFriend(String nameFriend) {
            this.nameFriend = nameFriend;
            return this;
        }

        public Friend build() {
            return new Friend(this);
        }


    }

    private Friend(Builder builder) {
        this.idFriend = builder.idFriend;
        this.nameFriend = builder.nameFriend;

    }

    public String toString() {
        return idFriend + " "
                + nameFriend + " ";

    }

    public int getIdFriend() {
        return idFriend;
    }

    public String getNameFriend() {
        return nameFriend;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Friend) {
            Friend that = (Friend) obj;
            return this.idFriend == that.idFriend
                    && this.nameFriend.equals(that.nameFriend);

        } return false;
    }
}
