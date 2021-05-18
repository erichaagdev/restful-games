package com.gorlah.demo.restfulgames

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestfulGamesApplication

fun main(args: Array<String>) {
    runApplication<RestfulGamesApplication>(*args)
}