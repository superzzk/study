package ttt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttt.model.Plus;
import ttt.service.TestService;
import ttt.service.TestServiceZuul;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestServiceZuul testServiceZuul;

    @RequestMapping("/")
    public Object index() {
        return "feign client";
    }

    @RequestMapping("/ti")
    public Object ti() {
        return testService.indexService();
    }

    @RequestMapping("/plus")
    public Object plus(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        return testService.plusService(numA, numB);
    }

    @RequestMapping("/plusab")
    public Object plusA(@RequestParam("numa") int numA, @RequestParam("numb") int numB) {
        Plus plus = new Plus();
        plus.setNumA(numA);
        plus.setNumB(numB);
        return testService.plusabService(plus);
    }

    @RequestMapping("/plus2")
    public Object plus2(Plus plus) {
        return testService.plus2Service(plus);
    }


    @RequestMapping("/ti-zuul")
    public Object ti_zuul() {
        return testServiceZuul.indexService();
    }

}