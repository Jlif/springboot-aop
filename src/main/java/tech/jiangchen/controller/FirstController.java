package tech.jiangchen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jiangchen.aop.Jlif;

@RestController
public class FirstController {

    @RequestMapping("/first")
    public Object first() {
        return "first controller";
    }

    @RequestMapping("/second")
    @Jlif(desc = "哈哈哈")
    public Object second() {
        return "second controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }
}