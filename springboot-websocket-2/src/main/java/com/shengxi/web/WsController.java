package com.shengxi.web;

import com.shengxi.entity.WiselyMessage;
import com.shengxi.entity.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yan
 * 快速访问方案
 */
@Controller
public class WsController {

    /**
     * #@MessageMapping@RequestMapping类似
     * 监听 welcome路径
     * 向/topic/getResponse发送消息
     *
     * @param message 客户端发回来的信息
     * @return 因为是广播式测试，所以直接返回加工后的信息
     * @throws InterruptedException ex
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse say(WiselyMessage message) throws InterruptedException {
        //暂停一下，模仿网络延时
        Thread.sleep(3000);
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }

    @GetMapping("/ws")
    public String index() {
        return "/ws";
    }
}
