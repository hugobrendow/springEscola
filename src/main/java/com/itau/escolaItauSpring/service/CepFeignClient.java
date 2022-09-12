package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.dto.response.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep", url = "http://viacep.com.br/ws/")
public interface CepFeignClient {

    @GetMapping("/{cep}/json")
    CepResponse getCep(@PathVariable String cep);
}
