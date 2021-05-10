package com.gorlah.demo.restfulgames.config

import com.gorlah.demo.restfulgames.util.IdGenerator
import com.gorlah.demo.restfulgames.util.TimestampGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Instant
import java.util.UUID

@Configuration
class GeneratorConfiguration {

    @Bean fun timestampGenerator() = TimestampGenerator { Instant.now().toEpochMilli() }
    @Bean fun idGenerator() = IdGenerator { UUID.randomUUID() }
}