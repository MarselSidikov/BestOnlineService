package models;


/**
 * on 10.05.2017.
 *
 * @version v1.0
 */
public class Product {
    private int id;
    private String name;
    private String manufacturer;
    private String dateRelease;
    private int price;

    public static class Builder {

        private int id;
        private String name;
        private String manufacturer;
        private String dateRelease;
        private int price;


        public Builder() {

        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;

        }

        public Builder dateRelease(String dateRelease) {
            this.dateRelease = dateRelease;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }


    }

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.dateRelease = builder.dateRelease;
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return id + " "
                + name + " "
                + manufacturer + " "
                + dateRelease + " "
                + price + " ";
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

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Product) {
            Product that = (Product) obj;
            return this.id == that.id
                    && this.name.equals(that.name)
                    && this.manufacturer.equals(that.manufacturer)
                    && this.dateRelease.equals(that.dateRelease)
                    && this.price == that.price;
        }
        return false;
    }

}

















