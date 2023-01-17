package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ITServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Hashtable;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private ITServiceRepository serviceRepository;

    @InjectMocks
    private  ReportService reportService;

    @Test
    public void givenAnEmptyCacheWhenTryingToGetTheReportThenDoTheCalculation() {
        //GIVEN
        //WHEN
        when(serviceRepository.getTotalFromServices()).thenReturn(10.0);
        //THEN
        Double response = reportService.getTotalAmount();
        Mockito.verify(serviceRepository, Mockito.times(1)).getTotalFromServices();
        Assertions.assertEquals(10.0, response);
    }

    @Test
    public void givenAFilledCacheWhenTryingToGetTheReportThenUseTheCache() {
        //GIVEN
        HashMap<String, Double> table = new HashMap<String, Double>();
        table.put("total", 10.0);

        ReflectionTestUtils.setField(reportService, "cache", table );
        //WHEN
        Double response = reportService.getTotalAmount();
        //THEN
        Mockito.verify(serviceRepository, Mockito.times(0)).getTotalFromServices();

        Assertions.assertEquals(10.0, response);
    }
}
