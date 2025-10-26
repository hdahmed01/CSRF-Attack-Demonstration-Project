package com.ahmed.csrf;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {
    private final Map<String, Integer> balances = new ConcurrentHashMap<>();

    public AccountService() {
        // initial balance for demo user "user"
        balances.put("user", 1000);
        // attacker account
        balances.put("attacker", 0);
    }

    public int getBalance(String username) {
        return balances.getOrDefault(username, 0);
    }

    public synchronized boolean transfer(String from, String to, int amount) {
        int fromBal = balances.getOrDefault(from, 0);
        if (amount <= 0 || fromBal < amount) return false;
        balances.put(from, fromBal - amount);
        balances.put(to, balances.getOrDefault(to, 0) + amount);
        return true;
    }
}
