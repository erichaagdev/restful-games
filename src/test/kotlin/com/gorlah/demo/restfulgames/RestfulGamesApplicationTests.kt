package com.gorlah.demo.restfulgames

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [CassandraContainerConfiguration::class])
class RestfulGamesApplicationTests {

    @Test
    fun contextLoads() { }
}