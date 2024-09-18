package frohn.sebastian.demo.controller;

import frohn.sebastian.demo.entity.SFEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SFRestController {

    @GetMapping("/")
    public SFEntity getData() {
        return new SFEntity("Hallo Sebastian");
    }
}
