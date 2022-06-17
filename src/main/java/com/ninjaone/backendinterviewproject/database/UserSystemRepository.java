package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.UserSystem;

@Repository
public interface UserSystemRepository extends PagingAndSortingRepository<UserSystem, String>  {
}
