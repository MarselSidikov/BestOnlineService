package models;

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
    private String genre;
    private String country;
    private String producer;
    private int lasting;
    private String description;
    private String actors;
    private String picture;

    public Film(Builder builder){
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

    public static class Builder{
        private int id;
        private String name;
        private String releaseDate;
        private String genre;
        private String country;
        private String producer;
        private int lasting;
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
        public Builder releaseDate(String releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }
        public Builder genre(String genre){
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
        public Builder actors(String actors){
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

    public String getGenre() {
        return genre;
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
        if(obj != null || obj instanceof Film){
            if(obj == this) return true;
            Film that = (Film) obj;
            return this.id == that.id
                    && this.name.equals(that.name)
                    && this.genre.equals(that.genre)
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