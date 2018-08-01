package ai.joblaser.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_entity_test_table")
public class CustomerEntity implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "customerId")
    private String customerId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "currentperiodend")
    private Long currentPeriodEnd;

    @Column(name="paymentcount")
    private Long paymentCount;

    public Long getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(Long paymentCount) {
        this.paymentCount = paymentCount;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCurrentPeriodEnd() {
        return currentPeriodEnd;
    }

    public void setCurrentPeriodEnd(Long currentPeriodEnd) {
        this.currentPeriodEnd = currentPeriodEnd;
    }


}
