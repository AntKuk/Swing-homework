package com.netcracker.ui.swing.main;

import java.util.Objects;

public class Author {
    private String name;
    private String country;
    private String gender;

    public Author(String name, String country, String gender) {
        this.name = name;
        this.country = country;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String email) {
        this.country = country;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        String result = "Author{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                '}';
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) &&
                country.equals(author.country) &&
                gender.equals(author.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, gender);
    }

    public int getFieldsCount() {
        int count = this.getClass().getDeclaredFields().length;
        return count;
    }




}
