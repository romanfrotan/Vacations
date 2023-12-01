package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }


    @Override
    public void run(String... args) throws Exception{

        if(customerRepository.count()<=1) {
            Division okemos = new Division();
            okemos.setCountry_id(1L);

            divisionRepository.save(okemos);

            Customer roman = new Customer("Roman", "Frotan", "4427 Okemos", "48864", "517", okemos);
            okemos.getCustomer().add(roman);
            customerRepository.save(roman);

            Customer haroon = new Customer("Nancy", "Freeman", "4347 Okemos", "48234", "518", okemos);
            okemos.getCustomer().add(haroon);
            customerRepository.save(haroon);

            Customer andrew = new Customer("Mary", "Spire", "4127 Willaimston", "4864", "519", okemos);
            okemos.getCustomer().add(andrew);
            customerRepository.save(andrew);

            Customer chris = new Customer("Chris", "Kowa", "4137 Mason", "47864", "520", okemos);
            okemos.getCustomer().add(chris);
            customerRepository.save(chris);

            Customer david = new Customer("David", "Bay", "4217 Jackson", "48164", "521", okemos);
            okemos.getCustomer().add(david);
            customerRepository.save(david);
        }





    }



}
