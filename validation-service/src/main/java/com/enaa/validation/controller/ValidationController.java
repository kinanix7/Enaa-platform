package com.enaa.validation.controller;

import com.enaa.validation.dto.DashboardDTO;
import com.enaa.validation.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.enaa.validation.entity.Validation;

@RestController
@RequestMapping("/api/validations")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @GetMapping
    public List<Validation> getAllValidations() {
        return validationService.getAllValidations();
    }

    @PostMapping
    public Validation createValidation(@RequestBody Validation validation) {
        return validationService.createValidation(validation);
    }

    @PutMapping("/{id}")
    public Validation updateValidation(@PathVariable Long id, @RequestBody Validation validationDetails) {
        return validationService.updateValidation(id, validationDetails);
    }

    @GetMapping("/apprenants/{apprenantId}/dashboard")
    public ResponseEntity<DashboardDTO> getDashboard(@PathVariable Long apprenantId) {
        return ResponseEntity.ok(validationService.getDashboard(apprenantId));
    }
}
