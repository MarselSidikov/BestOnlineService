package models;

/**
 * 07.05.2017
 * Profile
 *
 * @author Zavidonov Denis (First Software Engineering Platform)
 * @version v1.0
 */
public class Profile {
    private int id ;
    private String users;
    private String posts;
    private int friend;


    public static class Builder {
        private int id ;
        private String users;
        private String posts;
        private int friend;

        public Builder (){

        }
        public Builder id (int id){
            this.id = id;
            return this;
        }
        public Builder users (String users){
            this.users = users;
            return this;
        }
        public Builder posts (String posts){
            this.posts = posts;
            return this;
        }
        public Builder friends (int friends){
            this.friend = friends;
            return this;
        }

        public Profile build() {
            return new Profile (this);
        }

    }
    private Profile(Profile.Builder builder){
        this.id = builder.id;
        this.users = builder.users;
       // this.interests = builder.interests;
        this.posts = builder.posts;
        this.friend = builder.friend;
    }

    public String toString() {
        return  id + " "
                + users + " "
               // + interests + " "
                + posts + " "
                + friend + " ";

    }

    public int getId() {
        return id;
    }

    public String getUsers() {
        return users;
    }

    public String getPosts() {
        return posts;
    }

    public int getFriends() {
        return friend;
    }

    public boolean equals(Object obj){
        if (obj != null && obj instanceof Profile){
            Profile that = (Profile)obj;
            return this.id == that.id
                    && this.users.equals(that.users)
                    && this.posts.equals(that.posts)
                    && this.friend == that.friend;

        } return false;

    }


}
