package models;

import javafx.util.Builder;

import java.nio.Buffer;

/**
 * 09.05.2017
 @author Shaikhutdinov Artur (First Software Engineering Platform)
 @version v1.0
 */
public class Book {

    private int id;
    private String name;
    private String author;
    private String type;
    private String genre;
    private String publishingHouse;
    private int yearOfIssue;
    private int numberOfPages;
    private String language;
    private boolean translatedOnRussian;
    private String descriprion;

    public Book(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.author = builder.author;
        this.type = builder.type;
        this.genre = builder.genre;
        this.publishingHouse = builder.publishingHouse;
        this.yearOfIssue = builder.yearOfIssue;
        this.numberOfPages = builder.numberOfPages;
        this.language = builder.language;
        this.translatedOnRussian = builder.translatedOnRussian;
        this.descriprion = builder.description;
    }


    private static class Builder {
        private int id;
        private String name;
        private String author;
        private String type;
        private String genre;
        private String publishingHouse;
        private int yearOfIssue;
        private int numberOfPages;
        private String language;
        private boolean translatedOnRussian;
        private String description;


        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder publishingHouse(String publishingHouse) {
            this.publishingHouse = publishingHouse;
            return this;
        }

        public Builder yearOfIssue(int yearOfIssue) {
            this.yearOfIssue = yearOfIssue;
            return this;
        }

        public Builder numberOfPages(int numberOfPages) {
            this.numberOfPages = numberOfPages;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder translatedOnRussian(boolean translatedOnRussian) {
            this.translatedOnRussian = translatedOnRussian;
            return this;
        }

        public Builder descrniption(String description) {
            this.description = description;
            return this;
        }

        public Book build() {
            return new Book(this);
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

        public String getAuthor() {
            return author;
        }

        public String getType() {
            return type;
        }

        public String getGenre() {
            return genre;
        }

        public String getPublishingHouse() {
            return publishingHouse;
        }

        public int getYearOfIssue() {
            return yearOfIssue;
        }

        public int getNumberOfPages() {
            return numberOfPages;
        }

        public String getLanguage() {
            return language;
        }

        public boolean isTranslatedOnRussian() {
            return translatedOnRussian;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return id +
                    " " + name +
                    " " + author +
                    " " + type +
                    " " + genre +
                    " " + publishingHouse +
                    " " + yearOfIssue +
                    " " + numberOfPages +
                    " " + language +
                    " " + translatedOnRussian +
                    " " + description;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Book) {
                Book that  = (Book)obj;
                return this.id == that.id
                        && this.name.equals(that.name)
                        && this.author.equals(that.author)
                        && this.type.equals(that.type)
                        && this.genre.equals(that.genre)
                        && this.publishingHouse.equals(that.publishingHouse)
                        && this.yearOfIssue == that.yearOfIssue
                        && this.numberOfPages == that.numberOfPages
                        && this.language.equals(that.language)
                        && this.translatedOnRussian == that.translatedOnRussian
                        && this.description.equals(that.descriprion);
            }

            return false;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + name.hashCode();
            result = 31 * result + author.hashCode();
            result = 31 * result + type.hashCode();
            result = 31 * result + genre.hashCode();
            result = 31 * result + publishingHouse.hashCode();
            result = 31 * result + yearOfIssue;
            result = 31 * result + numberOfPages;
            result = 31 * result + language.hashCode();
            result = 31 * result + (translatedOnRussian ? 1 : 0);
            result = 31 * result + description.hashCode();
            return result;
        }
    }
}
