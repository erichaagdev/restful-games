package com.gorlah.demo.restfulgames.config

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource
import org.springframework.data.cassandra.config.SchemaAction
import org.testcontainers.containers.CassandraContainer

class CassandraContainerConfiguration : ApplicationContextInitializer<ConfigurableApplicationContext> {

    private val cassandraContainer = CassandraContainer<Nothing>("cassandra:3.11")

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        cassandraContainer.start()
        applicationContext.environment.propertySources.addFirst(buildProperties())
    }

    private fun buildProperties() =
        mapOf<String, Any>(
            "spring.data.cassandra.contact-points" to cassandraContainer.host,
            "spring.data.cassandra.local-datacenter" to fetchLocalDataCenter(),
            "spring.data.cassandra.port" to cassandraContainer.firstMappedPort,
            "spring.data.cassandra.schema-action" to SchemaAction.CREATE_IF_NOT_EXISTS,
        ).let { MapPropertySource(CassandraContainerConfiguration::class.simpleName!!, it) }

    private fun fetchLocalDataCenter() =
        cassandraContainer.cluster.connect().execute("select data_center from system.local;")
            .first().getString("data_center")
}