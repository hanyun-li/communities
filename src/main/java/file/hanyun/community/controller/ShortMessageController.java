package file.hanyun.community.controller;

import file.hanyun.community.provider.ShortMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortMessageController {

    @GetMapping("/short_message")
    public String testShortMessage(){
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.sendHttpPost();
        return "success";
    }

}
