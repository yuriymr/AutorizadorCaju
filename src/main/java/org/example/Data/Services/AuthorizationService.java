package org.example.Data.Services;

import org.example.Application.DTOs.ClientDTO;
import org.example.Data.Repositories.ClientRepository;
import org.example.Domain.AuthorizationEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.example.Domain.*;

@Service
public class AuthorizationService {

    @Autowired
    private ClientRepository clientRepository;

    public String authorizeTransaction(Transaction transaction) {
        ClientDTO client = clientRepository.findById(transaction.getAccountId()).orElse(null);

        String merchantType;

        if(transaction.getMerchant().contains("EATS") || transaction.getMerchant().contains("FOOD")
                || transaction.getMerchant().contains("RESTAURANTE")){
            transaction.setMcc("5812");
        }
        else if(transaction.getMerchant().contains("MERCADO") || transaction.getMerchant().contains("PADARIA")){
            transaction.setMcc("5411");
        }
        else if(transaction.getMerchant().contains("PAY") || transaction.getMerchant().contains("TRIP")
                || transaction.getMerchant().contains("LOJA")){
            transaction.setMcc("9999");
        }

        if (client == null) {
            return "07"; // Cliente não encontrado
        }

        BigDecimal amount = transaction.getTotalAmount();
        BigDecimal remainingAmount = amount;
        boolean approved = false;

        switch (transaction.getMcc()) {
            case "5411":
            case "5412":
                if (client.getFoodAmount().compareTo(amount) >= 0) {
                    client.setFoodAmount(client.getFoodAmount().subtract(amount));
                    client.setTotalAmount(client.getTotalAmount().subtract(amount));
                    approved = true;
                } else {
                    remainingAmount = remainingAmount.subtract(client.getFoodAmount());
                    client.setTotalAmount(client.getTotalAmount().subtract(client.getFoodAmount()));
                    client.setFoodAmount(BigDecimal.ZERO);
                }
                break;
            case "5811":
            case "5812":
                if (client.getMealAmount().compareTo(amount) >= 0) {
                    client.setMealAmount(client.getMealAmount().subtract(amount));
                    client.setTotalAmount(client.getTotalAmount().subtract(amount));
                    approved = true;
                } else {
                    remainingAmount = remainingAmount.subtract(client.getMealAmount());
                    client.setTotalAmount(client.getTotalAmount().subtract(client.getMealAmount()));
                    client.setMealAmount(BigDecimal.ZERO);
                }
                break;
            default:
                if (client.getCashAmount().compareTo(amount) >= 0) {
                    client.setCashAmount(client.getCashAmount().subtract(amount));
                    client.setTotalAmount(client.getTotalAmount().subtract(amount));
                    approved = true;
                } else {
                    remainingAmount = remainingAmount.subtract(client.getCashAmount());
                    client.setTotalAmount(client.getTotalAmount().subtract(client.getCashAmount()));
                    client.setCashAmount(BigDecimal.ZERO);
                }
                break;
        }

        if (!approved && remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            if (client.getCashAmount().compareTo(remainingAmount) >= 0) {
                client.setCashAmount(client.getCashAmount().subtract(remainingAmount));
                client.setTotalAmount(client.getTotalAmount().subtract(remainingAmount));
                approved = true;
            }
        }

        if (approved) {
            clientRepository.save(client);
            return "00"; // Aprovado
        } else {
            return "51"; // Saldo insuficiente
        }
    }
}