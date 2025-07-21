package com.enaa.apprenant.controller;

import com.enaa.apprenant.entity.Apprenant;
import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.service.ApprenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apprenants")
@RequiredArgsConstructor
public class ApprenantController {

    private final ApprenantService apprenantService;

    @GetMapping
    public List<Apprenant> getAllApprenants() {
        return apprenantService.getAllApprenants();
    }

    @PostMapping
    public Apprenant createApprenant(@RequestBody Apprenant apprenant) {
        return apprenantService.createApprenant(apprenant);
    }

    @GetMapping("/{id}")
    public Apprenant getApprenantById(@PathVariable Long id) {
        return apprenantService.getApprenantById(id);
    }

    @PutMapping("/{id}")
    public Apprenant updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenantDetails) {
        return apprenantService.updateApprenant(id, apprenantDetails);
    }

    @GetMapping("/{apprenantId}/rendus")
    public List<Rendu> getRendusByApprenant(@PathVariable Long apprenantId) {
        return apprenantService.getRendusByApprenant(apprenantId);
    }
}
