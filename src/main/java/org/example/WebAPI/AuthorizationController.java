package org.example.WebAPI;

import org.example.Data.Services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.Domain.Transaction;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/authorize")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;


    @PostMapping
    @ApiOperation(value = "Autoriza a transação", notes = "Retorna o código 00 se a tramsação for aprovada, 51 em caso de fundos insuficientes, e 07 se o cliente não for encontrado")
    public ResponseEntity<String> authorizeTransaction(@RequestBody Transaction transaction) {
        String responseCode = authorizationService.authorizeTransaction(transaction);
        return ResponseEntity.ok(responseCode);
    }
}
