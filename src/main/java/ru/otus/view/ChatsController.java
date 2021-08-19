package ru.otus.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.exception.EntityNotFoundException;
import ru.otus.service.UserService;

@Controller
public class ChatsController {
    private final UserService userService;

    @Autowired
    public ChatsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listChats() {
        return "main";
    }

    @PostMapping("/")
    public String listChatsPost() {
        return "main";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "redirect:/";
    }

    @GetMapping("/user")
    public String openChat(@RequestParam("to") Long recipientId, @RequestParam("from") Long senderId , Model model) {
        String username = userService.getUserById(recipientId).orElseThrow(EntityNotFoundException::new).getLogin();
        model.addAttribute("id", recipientId);
        model.addAttribute("username", username);
        return "chat";
    }

    @GetMapping("/create")
    public String createChat() {
        return "createChat";
    }

}
