package com.facebook.facebookclone.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MessageController {


    @MessageMapping("/message")
    public String message(String message) {
        return message;
    }
}
