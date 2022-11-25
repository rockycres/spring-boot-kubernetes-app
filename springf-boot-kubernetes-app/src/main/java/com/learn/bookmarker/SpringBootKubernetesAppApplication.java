package com.learn.bookmarker;

import com.learn.bookmarker.domain.BookmarkRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKubernetesAppApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringBootKubernetesAppApplication.class, args);
    }

}
