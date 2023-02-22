package cz.itnetwork.models;

public class Person {

    private int personId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String streetNameABN; // street name and building number
    private String city;
    private String zipCode;

    // complete constructor
    public Person(int personId, String name, String surname, String email, String phoneNumber, String streetNameABN, String city, String zipCode) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetNameABN = streetNameABN;
        this.city = city;
        this.zipCode = zipCode;
    }

    // constructor without personId
    public Person(String name, String surname, String email, String phoneNumber, String streetNameABN, String city, String zipCode) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetNameABN = streetNameABN;
        this.city = city;
        this.zipCode = zipCode;
    }

    // empty constructor
    public Person() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetNameABN() {
        return streetNameABN;
    }

    public void setStreetNameABN(String streetNameABN) {
        this.streetNameABN = streetNameABN;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetNameABN='" + streetNameABN + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
