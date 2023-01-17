package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.controller.responses.TotalResponse;
import com.ninjaone.backendinterviewproject.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/total")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<TotalResponse> getTotalCosts() {
        return new ResponseEntity<>(new TotalResponse(reportService.getTotalAmount()), HttpStatus.OK);
    }
}
