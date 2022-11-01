package com.ninjaone.backendinterviewproject.repository;

import com.ninjaone.backendinterviewproject.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {

    @Query(value ="SELECT count(s.id) FROM SERVICE s WHERE s.ID = :id OR s.NAME = :name",  nativeQuery = true)
    Long countRecordByIdAndName(@Param("id") int id, @Param("name") String name);

}
