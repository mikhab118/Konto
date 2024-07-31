package com.example.Konto.BankFunction;

import com.example.Konto.user.User;
import com.example.Konto.user.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Transactional
    public String makeTransfer(String senderUsername, String receiverUsername, double amount) {
        User sender = accountUserRepository.findByUsername(senderUsername);
        User receiver = accountUserRepository.findByUsername(receiverUsername);

        if (sender == null || receiver == null) {
            return "User not found";
        }

        if (sender.getBalance() < amount) {
            return "Insufficient funds";
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        accountUserRepository.save(sender);
        accountUserRepository.save(receiver);

        return "Transfer successful";
    }
}


