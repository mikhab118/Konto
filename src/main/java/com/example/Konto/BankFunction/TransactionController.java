package com.example.Konto.BankFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public String transfer(@RequestParam String senderUsername, @RequestParam String receiverUsername, @RequestParam double amount) {
        return transactionService.makeTransfer(senderUsername, receiverUsername, amount);
    }
}


