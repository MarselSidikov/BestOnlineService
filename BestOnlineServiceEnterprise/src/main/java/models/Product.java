package models;

import services.BooksService;

import java.util.List;


/**


 * on 10.05.2017.
 * @version v1.0
 */
public class Product   {
    private int id;
    private String name;
    private String manufacturer;
    private String data_release;
    private int price;



    public static class Builder{

        private int id;
        private String name;
        private String manufacturer;
        private String data_release;
        private int price;


        public Builder(){

        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder manufacturer(String manufacturer){
            this.manufacturer = manufacturer;
            return this;

        }

        public Builder data_release(String data_release) {
            this.data_release = data_release;
            return this;
        }
        public Builder Price(int price) {
            this.price = price;
            return this;
        }
        public Product build(){
            return new Product (this);
        }


    }

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.data_release = builder.data_release;
        this.price = builder.price;
    }

    @Override
    public String toString(){
        return id +" "
                + name +" "
                + manufacturer +" "
                + data_release +" "
                + price +" ";
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

    public String getData_release() {
        return data_release;
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
                    && this.data_release.equals(that.data_release)
                    && this.price == that.price ;
        }return false;
    }

}

















