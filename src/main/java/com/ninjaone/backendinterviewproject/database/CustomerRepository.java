package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String>  {
    
}
