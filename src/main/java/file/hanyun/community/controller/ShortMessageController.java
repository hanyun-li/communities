package file.hanyun.community.controller;

import file.hanyun.community.provider.ShortMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShortMessageController {

    @GetMapping("/short_message")
    public void testShortMessage(){
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.sendHttpPost();
    }

}
