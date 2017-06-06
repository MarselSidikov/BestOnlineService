package models;

import java.util.List;

/**
 * 08.05.2017
 * Film @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class Film {
    private int id;
    private String name;
    private String releaseDate;
    private List <Genre> genres;
    private String country;
    private String producer;
    private int lasting;
    private String description;
    private List <Actor> actors;
    private String picture;



    public Film(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.releaseDate = builder.releaseDate;
        this.genres = builder.genre;
        this.country = builder.country;
        this.producer = builder.producer;
        this.lasting = builder.lasting;
        this.description = builder.description;
        this.actors = builder.actors;
        this.picture = builder.picture;
    }

    public static class Builder{
        private int id;
        private String name;
        private String releaseDate;
        private List<Genre> genre;
        private String country;
        private String producer;
        private int lasting;
        private String description;
        private List<Actor> actors;
        private String picture;

        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder releaseDate(String releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }
        public Builder genre(List<Genre> genre){
            this.genre = genre;
            return this;
        }
        public Builder country(String country){
            this.country = country;
            return this;
        }
        public Builder producer(String producer){
            this.producer = producer;
            return this;
        }
        public Builder lasting(int lasting){
            this.lasting = lasting;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder actors(List<Actor> actors){
            this.actors = actors;
            return this;
        }
        public Builder picture(String picture){
            this.picture = picture;
            return this;
        }
        public Film build(){
            return new Film(this);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getCountry() {
        return country;
    }

    public String getProducer() {
        return producer;
    }

    public int getLasting() {
        return lasting;
    }

    public String getDescription() {
        return description;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return  id +
                " " + name +
                " " + releaseDate +
                " " + genres +
                " " + country +
                " " + producer +
                " " + lasting +
                " " + description +
                " " + actors +
                " " + picture;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null || obj instanceof Film){
            if(obj == this) return true;
            Film that = (Film) obj;
            return this.id == that.id
                    && this.name.equals(that.name)
                    && this.genres.equals(that.genres)
                    && this.actors.equals(that.actors)
                    && this.country.equals(that.country)
                    && this.description.equals(that.description)
                    && this.lasting == that.lasting
                    && this.producer.equals(that.producer)
                    && this.picture.equals(that.picture)
                    && this.releaseDate.equals(that.releaseDate);
        }return false;
    }
}