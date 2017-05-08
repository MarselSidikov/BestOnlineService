package models;

import java.sql.Time;
import java.util.Date;

/**
 * 08.05.2017
 * Movie @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class Movie {
    private int id;
    private String name;
    private Date releaseDate;
    private String genre;
    private String country;
    private String producer;
    private Time lasting;
    private String description;
    private String actors;
    private String picture;

    public Movie(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.releaseDate = builder.releaseDate;
        this.genre = builder.genre;
        this.country = builder.country;
        this.producer = builder.producer;
        this.lasting = builder.lasting;
        this.description = builder.description;
        this.actors = builder.actors;
        this.picture = builder.picture;
    }

    private static class Builder{
        private int id;
        private String name;
        private Date releaseDate;
        private String genre;
        private String country;
        private String producer;
        private Time lasting;
        private String description;
        private String actors;
        private String picture;

        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder releaseDate(Date releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }
        public Builder genry(String genre){
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
        public Builder lasting(Time lasting){
            this.lasting = lasting;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder actors(String actors){
            this.actors = actors;
            return this;
        }
        public Builder picture(String picture){
            this.picture = picture;
            return this;
        }
        public Movie build(){
            return new Movie(this);
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public String getProducer() {
        return producer;
    }

    public Time getLasting() {
        return lasting;
    }

    public String getDescription() {
        return description;
    }

    public String getActors() {
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
                " " + genre +
                " " + country +
                " " + producer +
                " " + lasting +
                " " + description +
                " " + actors +
                " " + picture;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null || obj instanceof Movie){
            if(obj == this) return true;
            Movie that = (Movie) obj;
            return this.id == that.id
                    && this.name.equals(that.name)
                    && this.genre.equals(that.genre)
                    && this.actors.equals(that.actors)
                    && this.country.equals(that.country)
                    && this.description.equals(that.description)
                    && this.lasting.equals(that.lasting)
                    && this.producer.equals(that.producer)
                    && this.picture.equals(that.picture)
                    && this.releaseDate.equals(that.releaseDate);
        }return false;
    }
}