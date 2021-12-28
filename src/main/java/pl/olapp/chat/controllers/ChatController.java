package pl.olapp.chat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.olapp.chat.dto.ChatMessage;

@Controller
@SessionAttributes("loggedUser")
public class ChatController {
    /*
        @RequestMapping(value = "/chat")
        public String chat(Model model){
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(new ChatMessage("none", "Hallo"));
            messages.add(new ChatMessage("none", "Hi!"));

            model.addAttribute("messages", messages);
            return "chat";
        }
    */
    //private List<ChatMessage> messages = new ArrayList<>();

    /*
    @PostMapping(value = "/chat")
    public String postChat(Model model,@ModelAttribute("message") ChatMessage chatMessage, @ModelAttribute("loggedUser") User user){
        chatMessage.setUser(user.getLogin());
        messages.add(chatMessage);
        return "redirect:chat";
    }
    */

    @GetMapping(value = "/chat")
    public String getChat(Model model){
        if(!model.containsAttribute("loggedUser"))
            return "redirect:login";
        return "chat";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {

        //dodać datę?
        //final String time = new SimpleDateFormat("HH:mm").format(new Date());
        //return new ChatMessage(message.getUser(), message.getValue(), time);
        return message;
    }
}
