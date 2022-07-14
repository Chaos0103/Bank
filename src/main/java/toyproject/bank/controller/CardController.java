package toyproject.bank.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.bank.controller.form.CreateCardForm;
import toyproject.bank.domain.AccountType;
import toyproject.bank.domain.CardType;
import toyproject.bank.dto.CardDto;
import toyproject.bank.dto.CardSearch;
import toyproject.bank.dto.CardTransactionDto;
import toyproject.bank.service.CardService;
import toyproject.bank.service.CardTransactionService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardTransactionService cardTransactionService;

    // ModelAttribute
    @ModelAttribute("cardTypes")
    public CardType[] cardTypes() {
        return CardType.values();
    }

    @GetMapping("/card/new")
    public String createCard(@ModelAttribute("cardForm") CreateCardForm cardForm) {
        return "/card/createCardForm";
    }

    @PostMapping("/card/new")
    public String create(CreateCardForm cardForm) {
        CardDto cardDto = new CardDto(null, null, null, cardForm.getCardType(), cardForm.getLimitAmount());
        cardService.create(cardForm.getAccountNumber(), cardDto);
        return "redirect:/";
    }

    @GetMapping("/card")
    public String cardList(@ModelAttribute("cardSearch") CardSearch cardSearch, Model model) {
        List<CardTransactionDto> cardTransactionDtoList = cardTransactionService.search(cardSearch.getCardNumber());
        model.addAttribute("cardTransactionDtoList", cardTransactionDtoList);
        return "/card/cardList";
    }
}
