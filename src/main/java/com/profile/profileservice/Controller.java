package com.profile.profileservice;

import com.common.model.Customer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Controller {
    private final CustomerRepository customerRepository;
    private final Environment environment;

    public Controller(CustomerRepository customerRepository, Environment environment) {
        this.customerRepository = customerRepository;
        this.environment = environment;
    }

    @GetMapping("/profile/all")
    public List<Customer> getAll(){

        System.out.println("Port :" + environment.getProperty("local.server.port"));
        return customerRepository.findAll();
    }

    @PostMapping("/profile/save")
    public void save(@RequestBody Customer c){
        customerRepository.save(c);
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') ")
    public Customer getOne(@PathVariable long id){
        return customerRepository.findById(id).get();
    }

    @GetMapping("/profile/test")
    public String test(){
        return "Hi";
    }
}
