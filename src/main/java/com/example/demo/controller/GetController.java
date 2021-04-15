package com.example.demo.controller;

import com.example.demo.model.SearchParam;
import com.example.demo.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Localhost:880/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")//Localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id :"+id);
        System.out.println("password : "+password);

        return id+password;
    }

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //{"account" : "", "email" : "", "page" : 0}
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){

        //{"resultCode" : "OK" , "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
