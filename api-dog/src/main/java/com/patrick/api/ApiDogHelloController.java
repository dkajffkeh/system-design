package com.patrick.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-dog")
@Tag(name = "테스트 컨트롤러", description = "Hello 와 관련되었습니다.")
public class ApiDogHelloController {

    @GetMapping("/{path}")
    @Operation(summary = "Hello 생성 근데 문제가있는")
    public String helloWorld(@PathVariable Long path) {
        return path.toString();
    }

    @PostMapping("/{path}")
    @Operation(summary = "POST Mappring Test")
    public PostApiDogResponse postApiDog(@PathVariable Long path, @RequestBody PostApiDogRequest request) {
        return new PostApiDogResponse("HelloWorld");
    }

    @DeleteMapping
    public void helloDelete() {

    }

    public static class PostApiDogRequest {
        /**
         * 마이크테스트
         */
        @Schema(required = false, description = "miock")
        private String helloWorld;

        @Schema(required = true)
        private String helloWorld2;

        @Schema(required = true)
        private String helloWorld3;
    }

    public static class PostApiDogResponse {
        private String helloWorld;

        public PostApiDogResponse(String helloWorld) {
            this.helloWorld = helloWorld;
        }
    }
}
