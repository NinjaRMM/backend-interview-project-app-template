package com.ninjaone.backendinterviewproject.repository;

import com.ninjaone.backendinterviewproject.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {


}
