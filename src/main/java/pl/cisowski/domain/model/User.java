package pl.cisowski.domain.model;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

public class User {
    private Integer id;
    private String email;
    private String password;
    private Boolean isEnabled;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private Collection<Authority> authorities;
    private ZonedDateTime dateOfCreation;
    private ZonedDateTime dateOfUpdate;
    private Address address;
    private boolean isHide;

    public User() {}

    public User(Integer id, String email, String password, Boolean isEnabled, String phoneNumber, String firstName, String lastName, Date birthDate, Gender gender, Collection<Authority> authorities, ZonedDateTime dateOfCreation, ZonedDateTime dateOfUpdate, Address address, boolean isHide) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.authorities = authorities;
        this.dateOfCreation = dateOfCreation;
        this.dateOfUpdate = dateOfUpdate;
        this.address = address;
        this.isHide = isHide;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public ZonedDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(ZonedDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public ZonedDateTime getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(ZonedDateTime dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }


    @Override
    public String toString() {
        return String.format("ID: %s, First name: %s, Last name: %s, Email: %s, IsEnabled: %s",  this.id, this.firstName, this.lastName, this.email, this.isEnabled.toString());
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
