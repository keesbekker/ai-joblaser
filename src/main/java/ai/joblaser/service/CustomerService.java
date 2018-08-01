package ai.joblaser.service;

import ai.joblaser.dao.CustomerEntityRepository;
import ai.joblaser.domain.CustomerDetails;
import ai.joblaser.domain.CustomerEntity;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    @Autowired
    CustomerEntityRepository customerEntityRepository;

    public void saveCustomer(Customer stripeCustomer, CustomerDetails customerDetails, Subscription subscription){
        System.out.println("hier komt tie nu");
        CustomerEntity customerEntity = createCustomerEntity(stripeCustomer, customerDetails, subscription);
        customerEntityRepository.save(customerEntity);
    }

    public CustomerEntity createCustomerEntity(Customer stripeCustomer, CustomerDetails customerDetails, Subscription subscription){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(stripeCustomer.getId());
        customerEntity.setFirstName(customerDetails.getFirstName());
        customerEntity.setLastName(customerDetails.getLastName());
        customerEntity.setEmail(customerDetails.getEmail());
        customerEntity.setPhoneNumber(customerDetails.getPhoneNumber());
        customerEntity.setUserName(customerDetails.getUserName());
        customerEntity.setPassword(customerDetails.getPassword());
        customerEntity.setCompanyName(customerDetails.getCompanyName());
        customerEntity.setCurrentPeriodEnd(subscription.getCurrentPeriodEnd());
        customerEntity.setPaymentCount((long) 0);
        return customerEntity;
    }

    public void updateCustomerCurrentPeriodEnd(Event event){
        if("invoice.payment_succeeded".equalsIgnoreCase(event.getType())){
            Invoice invoice = (Invoice) event.getData().getObject();
            CustomerEntity customerEntity = customerEntityRepository.findCustomerByID(invoice.getCustomer());
            if(customerEntity != null && customerEntity.getPaymentCount() > 0 ) {
                customerEntity.setPaymentCount(customerEntity.getPaymentCount() + 1);
                customerEntity.setCurrentPeriodEnd(customerEntity.getCurrentPeriodEnd() + (15 * 3600 * 24));
                customerEntityRepository.save(customerEntity);
            }
            else {
                customerEntity.setPaymentCount((long) 1);
                customerEntityRepository.save(customerEntity);
            }
        }
    }
}
