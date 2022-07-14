package toyproject.bank.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.bank.controller.form.AccountTransactionForm;
import toyproject.bank.controller.form.CreateAccountForm;
import toyproject.bank.domain.AccountType;
import toyproject.bank.domain.TransactionType;
import toyproject.bank.dto.AccountDto;
import toyproject.bank.dto.AccountSearch;
import toyproject.bank.dto.AccountTransactionDto;
import toyproject.bank.service.AccountService;
import toyproject.bank.service.AccountTransactionService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountTransactionService accountTransactionService;

    // ModelAttribute
    @ModelAttribute("accountTypes")
    public AccountType[] accountTypes() {
        return AccountType.values();
    }

    @GetMapping("/account/new")
    public String createAccount(@ModelAttribute("accountForm") CreateAccountForm accountForm) {
        return "/account/createAccountForm";
    }

    @PostMapping("/account/new")
    public String create(CreateAccountForm accountForm) {
        AccountDto accountDto = new AccountDto(null, accountForm.getBalance(), accountForm.getAccountType());
        accountService.create(accountForm.getClientId(), accountDto);
        return "redirect:/";
    }

    @GetMapping("/account/reissue")
    public String reissueAccount(@ModelAttribute("accountForm") CreateAccountForm accountForm, @ModelAttribute("oldAccountNumber") String oldAccountNumber) {
        return "/account/reissueForm";
    }

    @PostMapping("/account/reissue")
    public String reissue(CreateAccountForm accountForm, String oldAccountNumber) {
        AccountDto accountDto = new AccountDto(null, accountForm.getBalance(), accountForm.getAccountType());
        accountService.reissue(accountForm.getClientId(), oldAccountNumber, accountDto);
        return "redirect:/";
    }

    @GetMapping("/account/input")
    public String inputAccount(@ModelAttribute("transactionForm") AccountTransactionForm transactionForm) {
        return "/account/inputAccountForm";
    }

    @PostMapping("/account/input")
    public String input(AccountTransactionForm transactionForm) {
        AccountTransactionDto accountTransactionDto = new AccountTransactionDto(null, transactionForm.getAmount(), 0, TransactionType.IN, transactionForm.getContent());
        accountTransactionService.transaction(transactionForm.getAccountNumber(), accountTransactionDto);
        return "redirect:/";
    }

    @GetMapping("/account/output")
    public String outputAccount(@ModelAttribute("transactionForm") AccountTransactionForm transactionForm) {
        return "/account/outputAccountForm";
    }

    @PostMapping("/account/output")
    public String output(AccountTransactionForm transactionForm) {
        AccountTransactionDto accountTransactionDto = new AccountTransactionDto(null, transactionForm.getAmount(), 0, TransactionType.OUT, transactionForm.getContent());
        accountTransactionService.transaction(transactionForm.getAccountNumber(), accountTransactionDto);
        return "redirect:/";
    }

    @GetMapping("/account/trans")
    public String transAccount(@ModelAttribute("transactionForm") AccountTransactionForm transactionForm,
                               @ModelAttribute("outAccountNumber") String outAccountNumber) {
        return "/account/transAccountForm";
    }

    @PostMapping("/account/trans")
    public String trans(AccountTransactionForm transactionForm, String outAccountNumber) {
        AccountTransactionDto accountTransactionDto = new AccountTransactionDto(null, transactionForm.getAmount(), 0, null, transactionForm.getContent());
        accountTransactionService.accountTransaction(outAccountNumber, transactionForm.getAccountNumber(), accountTransactionDto);
        return "redirect:/";
    }

    @GetMapping("/account")
    public String accountList(@ModelAttribute("accountSearch") AccountSearch accountSearch, Model model) {
        List<AccountTransactionDto> accountTransactionDtoList = accountTransactionService.search(accountSearch.getAccountNumber());
        model.addAttribute("accountTransactionDtoList", accountTransactionDtoList);
        return "/account/accountList";
    }
}
