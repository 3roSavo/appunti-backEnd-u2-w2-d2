package savogineros.webserviceu2w2d2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/examples")
public class ExampleController {

    @GetMapping("/getExample")
    public String getExample() {
        System.out.println("prova get");
        return "Questa è una prova GET";

    }
    @PostMapping("/postExample")
    public String postExample() {
        return "prova post";
    }
}
