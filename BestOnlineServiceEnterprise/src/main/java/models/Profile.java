package models;

import dao.BaseDao;

import java.util.List;

/**
 * 07.05.2017
 * Profile
 *
 * @author Zavidonov Denis (First Software Engineering Platform)
 * @version v1.0
 */
public class Profile {
    private int id ;
    private String firstNameUser;
    private String lastNameUser;
    private int ageUser;
    private String city;
    private String image;
    private List<Post> posts;
    private List<User>friends;


    public static class Builder {
        private int id ;
        private String firstNameUser;
        private String lastNameUser;
        private int ageUser;
        private String city;
        private String image;
        private List<Post> posts;
        private List<User>friends;

        public Builder (){

        }

        public Builder id (int id){
            this.id = id;
            return this;
        }


        public Builder firstNameUser (String firstNameUser){
            this.firstNameUser = firstNameUser;
            return this;
        }
        public Builder lastNameUser (String lastNameUser){
            this.lastNameUser = lastNameUser;
            return this;
        }
        public Builder ageUser (int ageUser){
            this.ageUser = ageUser;
            return this;
        }
        public Builder city (String city){
            this.city = city;
            return this;
        }
        public Builder image (String image){
            this.image = image;
            return this;
        }
        
        public Builder posts (List<Post> posts){
            this.posts = posts;
            return this;
        }
        public Builder friends (List<User>friends){
            this.friends = friends;
            return this;
        }

        public Profile build() {
            return new Profile (this);
        }

    }
    private Profile(Profile.Builder builder){
        this.id = builder.id;
        this.firstNameUser = builder.firstNameUser;
        this.lastNameUser = builder.lastNameUser;
        this.ageUser = builder.ageUser;
        this.city = builder.city;
        this.image = builder.image;
        this.posts = builder.posts;
        this.friends = builder.friends;

    }


    public String toString() {
        return  id + " "
                + firstNameUser + " "
                + lastNameUser + " "
                + ageUser + " "
                + city + " "
                + image + " "
                + posts + " "
                + friends + " ";

    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstNameUser() {
        return firstNameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public int getAgeUser() {
        return ageUser;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<User> getFriends() {
        return friends;
    }


    public boolean equals(Object obj){
        if (obj != null && obj instanceof Profile){
            Profile that = (Profile)obj;
            return this.id == that.id
                    && this.firstNameUser.equals(that.firstNameUser)
                    && this.lastNameUser.equals(that.lastNameUser)
                    && this.ageUser == that.ageUser
                    && this.city.equals(that.city)
                    && this.image.equals(that.image)
                    && this.posts.equals(that.posts)
                    && this.friends.equals(that.friends);

        } return false;

    }


}
