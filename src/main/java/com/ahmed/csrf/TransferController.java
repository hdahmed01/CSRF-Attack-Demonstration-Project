package com.ahmed.csrf;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransferController {

    private final AccountService accountService;

    public TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/transfer";
    }

    @GetMapping("/transfer")
    public String transferForm(Model model, HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        model.addAttribute("user", user);
        model.addAttribute("balance", accountService.getBalance(user));
        return "transfer";
    }

    @PostMapping("/transfer")
    public String doTransfer(@RequestParam String to,
                             @RequestParam int amount,
                             Model model,
                             HttpServletRequest req) {
        String user = req.getUserPrincipal().getName();
        boolean ok = accountService.transfer(user, to, amount);
        if (ok) {
            model.addAttribute("message", "Transfer of " + amount + " from " + user + " to " + to + " succeeded.");
        } else {
            model.addAttribute("message", "Transfer failed (insufficient funds or invalid amount).");
        }
        model.addAttribute("balance", accountService.getBalance(user));
        return "result";
    }

}
