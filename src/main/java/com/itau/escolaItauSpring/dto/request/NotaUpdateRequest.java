package com.itau.escolaItauSpring.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class NotaUpdateRequest {
    @Min(0)
    @Max(10)
    private BigDecimal nota;
}
