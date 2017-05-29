package models;

/**
 * 29.05.2017
 * Genre
 *
 * @author Ayupov Ayaz (First Software Engineering Platform)
 * @version v1.0
 */
public class Genre {
    private int id;
    private String genre;

    public Genre(Builder builder){
        this.id = builder.id;
        this.genre = builder.genre;
    }
    public static class Builder{
        private int id;
        private String genre;

        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder genre(String genre){
            this.genre = genre;
            return this;
        }
        public Genre build(){
            return new Genre(this);
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
