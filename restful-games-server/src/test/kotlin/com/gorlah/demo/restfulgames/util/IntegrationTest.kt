package com.gorlah.demo.restfulgames.util

import com.gorlah.demo.restfulgames.config.CassandraContainerConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@Target(AnnotationTarget.CLASS)
@ContextConfiguration(initializers = [CassandraContainerConfiguration::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
annotation class IntegrationTest