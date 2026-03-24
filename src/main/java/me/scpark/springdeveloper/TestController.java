package me.scpark.springdeveloper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TestController {




    @RestController
    public static class TestApiController {

        @GetMapping("/test2")
        public String test() {
            return "Hello World";
        }
    }

        @Autowired
        private TestService testService;

        @GetMapping("/test")
        public ResponseEntity<List<Member>> getAllMember(){
            return ResponseEntity.ok(testService.getAllMembers());
    }
    @PostMapping("/test")
    public ResponseEntity<Member> createMember(@RequestBody Member member){

            return ResponseEntity.ok(testService.saveMember(member));
    }
}
