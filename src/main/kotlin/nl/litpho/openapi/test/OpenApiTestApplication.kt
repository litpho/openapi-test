package nl.litpho.openapi.test

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class OpenApiTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(OpenApiTestApplication::class.java, *args)
}
