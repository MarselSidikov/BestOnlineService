package models;

import java.sql.Date;

/**
 * 10.05.2017
 * UserAuth
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */
public class UserAuth {
    private int id;
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private Date date_created;
    private Date date_modified;

    public UserAuth(Builder builder) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_created = date_created;
        this.date_modified = date_modified;
    }

    public static class Builder {
        private int id;
        private String user_name;
        private String password;
        private String first_name;
        private String last_name;
        private Date date_created;
        private Date date_modified;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder user_name(String user_name) {
            this.user_name = user_name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder first_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public Builder last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public Builder date_created(Date date_created) {
            this.date_created = date_created;
            return this;
        }

        public Builder date_modified(Date date_modified) {
            this.date_modified = date_modified;
            return this;
        }
    }

    public void setId() {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    @Override
    public String toString() {
        return id +
                " " + user_name +
                " " + password +
                " " + first_name +
                " " + last_name +
                " " + date_created +
                " " + date_modified;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null || obj instanceof UserAuth) {
            if (obj == this) return true;
            UserAuth that = (UserAuth obj);
            return this.id == that.id
                    && this.user_name.equals(that.user_name)
                    && this.password.equals(that.password)
                    && this.first_name.equals(that.first_name)
                    && this.last_name.equals(that.last_name)
                    && this.date_created.equals(that.date_created)
                    && this.date_modified.equals(that.date_modified);
        } return false;
    }
}
