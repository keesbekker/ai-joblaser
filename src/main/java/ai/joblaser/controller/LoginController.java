package ai.joblaser.controller;


import ai.joblaser.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        if(request.getHeader("userName") != null && request.getHeader("userName") != "" && request.getHeader("password") != null && request.getHeader("password") != ""){
            boolean credentialsValid = loginService.checkLoginCredentials(request.getHeader("userName"), request.getHeader("password"));
            boolean subscriptionValid = loginService.checkSubscriptionCurrentEnd(request.getHeader("userName"));
            if(credentialsValid && subscriptionValid){
                String jwtToken = loginService.createJwtToken(request.getHeader("userName"), request.getHeader("password"));
                return "{\"token\" : \"" + jwtToken + "\"}";
            }
            if(credentialsValid && !subscriptionValid){
                return "{\"message\" : \"subscription not valid\"}";

            }
            if(!credentialsValid && subscriptionValid){
                return "{\"message\" : \"credentials not valid\"}";

            }
        }
        if(request.getHeader("jwt_token") != null && request.getHeader("jwt_token") != ""){
            boolean jwtTokenValid = loginService.checkIfJwtTokenIsValid(request.getHeader("jwt_token"));
            if(jwtTokenValid){
                String jwtToken = request.getHeader("jwt_token");
                return "{\"token\" : \"" + jwtToken + "\"}";
            }
            else{
                return "{\"token\" : \"not valid\"}";
            }
        }
        return "{\"token\" : \"not valid\"}";
    }


    @RequestMapping(method = RequestMethod.POST, path="/user")
    public String checkIfUserNameisAvailable(@RequestParam(value="username") String username){
            boolean userNameIsAvailable = loginService.checkIfUserNameisAvailable(username);
            if(userNameIsAvailable){
                return "{\"available\" :\"yes\"}";
            }
        return "{\"available\" :\"no\"}";
    }

}
