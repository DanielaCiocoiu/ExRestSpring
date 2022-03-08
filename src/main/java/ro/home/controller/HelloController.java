package ro.home.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.home.model.User;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    // GET http://localhost:8871/hello
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String sayHello() {
        return "<html><body><b>Hello</b></body></html>";
    }

    //@RequestMapping(method = GET) <=> @GetMapping
    // GET http://localhost:8871/hello/name?name=costel
    @GetMapping(path = "/name", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHelloTo1(@RequestParam("name") String name) {
        return "Hello, " + name;
    }

    // GET http://localhost:8871/hello/name/costel?lang=ro
    @GetMapping(path = "/name/{name}")
    public String sayHelloTo2(@PathVariable("name") String name,
                              @RequestParam(name = "lang", defaultValue = "en") String language) {
        switch (language) {
            case "en" : return "Hello, " + name;
            case "ro" : return "Buna, " + name;
            default: throw new IllegalArgumentException(language + " not supported");
        }
    }

    // GET http://localhost:8871/hello/name/all?name=gigel&name=costel&name=ionel
    @GetMapping("/name/all")
    public String sayHelloToAll(@RequestParam("name") List<String> names) {
        return "Hello, " + String.join(", ", names);
    }

    // GET http://localhost:8871/hello/user?username=gigel&age=25&password=1234
    @GetMapping("/user")
    public String parseUser(User user) {
        return user.toString();
    }
}
