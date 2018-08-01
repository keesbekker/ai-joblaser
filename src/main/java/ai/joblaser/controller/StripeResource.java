package ai.joblaser.controller;

import ai.joblaser.domain.CustomerAndPaymentDetails;
import ai.joblaser.service.BodyParser;
import ai.joblaser.service.CustomerService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import com.stripe.net.APIResource;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stripe")
public class StripeResource {

    @Autowired
    CustomerService customerService;



    @RequestMapping(method = RequestMethod.POST)
    public String createCustomerAndAddPaymentData(@RequestBody CustomerAndPaymentDetails customerAndPaymentDetails) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {//        Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
        Stripe.apiKey = "sk_test_LrEbOIgHBn0kMpMay1sUUzO2";
        System.out.println("dit is de token" + customerAndPaymentDetails.getToken().getId());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", customerAndPaymentDetails.getToken().getEmail());
        params.put("source", customerAndPaymentDetails.getToken().getId());
        Customer customer = Customer.create(params);
        Subscription subscription = addCustomerToSubscription(customer);
        customerService.saveCustomer(customer, customerAndPaymentDetails.getCustomerDetails(), subscription );
        return "{\"success\" : true}";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/webhook")
    public String handle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        Stripe.apiKey = "sk_test_LrEbOIgHBn0kMpMay1sUUzO2";
        String requestJson = new BodyParser().getBody(request);
        JSONObject object=new JSONObject(requestJson);
        object.remove("request");
        object.put("request", null);
        requestJson = object.toString();
        Event eventJson = APIResource.GSON.fromJson(requestJson, Event.class);
        customerService.updateCustomerCurrentPeriodEnd(eventJson);
        response.setStatus(200);
        return "";
    }



    public Subscription addCustomerToSubscription(Customer customer) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Stripe.apiKey = "sk_test_LrEbOIgHBn0kMpMay1sUUzO2";
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("plan", "test_basic_plan");
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("0", item);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customer", customer.getId());
        params.put("items", items);
        Subscription subscription = Subscription.create(params);
        return subscription;
    }


}
