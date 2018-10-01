package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_detail_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_detail_id_gen", sequenceName = "user_detail_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "surname")
    private String surname;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Pattern(regexp = "([a-zA-Z0-9 -]+)?", message = "Provided zip code is incorrect")
    @Column(name = "zip_code")
    private String zipCode;
    @Pattern(regexp = "([+]?[0-9 -]+)?", message = "Provided phone number is incorrect")
    @Column(name = "phone_number")
    private String phoneNumber;
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
