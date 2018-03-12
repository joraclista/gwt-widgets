package com.github.joraclista.client.ui.widgets.contact;

import java.util.List;

/**
 * Created by Alisa
 * version 1.0.
 */
public class Person {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String company;
    private List<String> skills;
    private List<String> overview;
    private String position;
    private String website;
    private String skype;
    private String facebook;
    private int numOfProjects;
    private int numOfClients;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (numOfProjects != person.numOfProjects) return false;
        if (numOfClients != person.numOfClients) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (phone != null ? !phone.equals(person.phone) : person.phone != null) return false;
        if (company != null ? !company.equals(person.company) : person.company != null) return false;
        if (skills != null ? !skills.equals(person.skills) : person.skills != null) return false;
        if (overview != null ? !overview.equals(person.overview) : person.overview != null) return false;
        if (position != null ? !position.equals(person.position) : person.position != null) return false;
        if (website != null ? !website.equals(person.website) : person.website != null) return false;
        if (skype != null ? !skype.equals(person.skype) : person.skype != null) return false;
        return facebook != null ? facebook.equals(person.facebook) : person.facebook == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (facebook != null ? facebook.hashCode() : 0);
        result = 31 * result + numOfProjects;
        result = 31 * result + numOfClients;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getOverview() {
        return overview;
    }

    public void setOverview(List<String> overview) {
        this.overview = overview;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getNumOfProjects() {
        return numOfProjects;
    }

    public void setNumOfProjects(int numOfProjects) {
        this.numOfProjects = numOfProjects;
    }

    public int getNumOfClients() {
        return numOfClients;
    }

    public void setNumOfClients(int numOfClients) {
        this.numOfClients = numOfClients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
