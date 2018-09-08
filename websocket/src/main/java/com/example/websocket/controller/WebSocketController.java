package com.example.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dingyabin
 * @date 2018/4/20
 * Time: 16:16
 * function:
 */
@Slf4j
@Controller
public class WebSocketController {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/response")
    public Map<String,String> hello(Message<String> message) {
        String payload = message.getPayload();
        log.info("收到消息:{}",payload);
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("data",payload);
        return map;
    }


    @RequestMapping("/welcome")
    @ResponseBody
    public Map<String,String> hello2() {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("data","ok");
        messagingTemplate.convertAndSend("/topic/response", map);
        return map;
    }


}
