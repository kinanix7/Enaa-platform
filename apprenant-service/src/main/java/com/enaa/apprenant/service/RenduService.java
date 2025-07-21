package com.enaa.apprenant.service;

import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.repository.RenduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RenduService {

    private final RenduRepository renduRepository;

    public Rendu createRendu(Rendu rendu) {
        return renduRepository.save(rendu);
    }

    public Rendu getRenduById(Long id) {
        return renduRepository.findById(id).orElseThrow(() -> new RuntimeException("Rendu not found"));
    }
}
