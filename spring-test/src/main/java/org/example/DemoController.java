package org.example;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // todo provide some example code to write tests

    @RequestMapping(value = "/demo", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> someDemo(
            @RequestParam(name = "requiredParam") String requiredParam,
            @RequestParam(name = "optionalParam", required = false) String optionalParam
            ){
        return ResponseEntity.ok("Hello " + requiredParam + " " + optionalParam);
    }

}
