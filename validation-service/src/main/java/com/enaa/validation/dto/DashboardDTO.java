package com.enaa.validation.dto;

import com.enaa.validation.dto.ApprenantDTO;
import com.enaa.validation.dto.ValidationDTO;
import lombok.Data;
import java.util.List;

/**
 * DTO for representing dashboard information.
 */
@Data
public class DashboardDTO {
    private ApprenantDTO apprenant;
    private List<ValidationDTO> validations;
}
