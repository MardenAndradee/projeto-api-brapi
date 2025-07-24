package com.marden.FinUp.Controllers;

import com.marden.FinUp.Services.BrapiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ativos")
public class BrapiController {

    BrapiService brapiService;

    public BrapiController(BrapiService brapiService){
        this.brapiService = brapiService;
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<String> buscarAtivo(@PathVariable String ticker){
        String resultado = brapiService.buscarAtivoPorTicker(ticker);
        return ResponseEntity.ok(resultado);
    }

}
