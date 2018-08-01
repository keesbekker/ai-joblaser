package ai.joblaser.service;

import ai.joblaser.dao.CustomerEntityRepository;
import ai.joblaser.domain.CustomerEntity;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class LoginService {

    @Autowired
    CustomerEntityRepository customerEntityRepository;

    @Autowired
    TokenService tokenService;


    public boolean checkLoginCredentials(String userName, String password) {
            CustomerEntity customerEntity = customerEntityRepository.findCustomerByUserName(userName);
            if(customerEntity != null && password.equalsIgnoreCase(customerEntity.getPassword())){
                    return true;
                }
            return false;
    }

    public boolean checkSubscriptionCurrentEnd(String userName){
        CustomerEntity customerEntity = customerEntityRepository.findCustomerByUserName(userName);
        if(customerEntity != null){
            Instant instant = Instant.now();
            long timeStampSeconds = instant.getEpochSecond();
            System.out.println("This is timestampseconds:" + timeStampSeconds);
            if(customerEntity.getCurrentPeriodEnd() > timeStampSeconds){
                return true;
            }
        }
        return false;
    }


    public boolean checkIfJwtTokenIsValid(String jwtToken) {
        return tokenService.checkIfJwtTokenIsValid(jwtToken);
    }


    public String createJwtToken(String userName, String password) {
        CustomerEntity customerEntity = customerEntityRepository.findCustomerByUserName(userName);
        return tokenService.createJwtToken(customerEntity.getCustomerId(), "joblaser.ai", customerEntity.getUserName(), 604800000);
    }


    public boolean checkIfUserNameisAvailable(String userName) {
        CustomerEntity customerEntity = customerEntityRepository.findCustomerByUserName(userName);
        if (customerEntity == null) {
            return true;
        }
        return false;
    }

}

