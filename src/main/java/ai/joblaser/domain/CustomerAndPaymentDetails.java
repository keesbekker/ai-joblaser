package ai.joblaser.domain;

import com.stripe.model.Token;

public class CustomerAndPaymentDetails {

    private CustomerDetails customerDetails;
    private Token token;

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }


}
