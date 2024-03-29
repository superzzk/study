package ttt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttt.service.TestService;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public Object index() {
        return "ribbon client";
    }

    @RequestMapping("/ti")
    public Object ti() {
        return testService.index();
    }

    @RequestMapping("/plus")
    public Object plus(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        return testService.plus(numA, numB);
    }
}