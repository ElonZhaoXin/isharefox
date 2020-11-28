package com.isharefox.share.web.controller;
import com.google.gson.Gson;
import com.isharefox.share.user.regist.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class HeartBeatController {
    final IUserService userService;
    @GetMapping("/heartbeat")
    public String say() {
        return "xyssss11啊啊啊";
    }

    @GetMapping("/datasouce")
    public String datasouceConnection() {
        return new Gson().toJson(userService.list());
    }
}
