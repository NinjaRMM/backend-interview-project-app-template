package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.ITService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITServiceRepository extends JpaRepository<ITService, Long> {


    /**
     * Cystom query using NativeQuery to delegate the responsibility of calculating the totals to the database.
     * @return The Total Cost of the services Provided
     */
    @Query(value = "SELECT (COALESCE(SUM (COST),0) +  (SELECT count(*) * COALESCE((SELECT COST FROM ITSERVICE WHERE NAME = 'DEVICE FEE'),0) from device )) as TOTAL_FEE " +
            "FROM DEVICES_SERVICED ds JOIN ITSERVICE i on i.ID = ds.SERVICE_ID", nativeQuery = true)
    Double getTotalFromServices();
}
