package com.enaa.apprenant.controller;

import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.service.RenduService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendus")
@RequiredArgsConstructor
public class RenduController {

    private final RenduService renduService;

    @PostMapping
    public Rendu createRendu(@RequestBody Rendu rendu) {
        return renduService.createRendu(rendu);
    }

    @GetMapping("/{id}")
    public Rendu getRenduById(@PathVariable Long id) {
        return renduService.getRenduById(id);
    }
}
