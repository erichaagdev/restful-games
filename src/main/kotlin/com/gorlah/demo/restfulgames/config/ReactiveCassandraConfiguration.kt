package com.gorlah.demo.restfulgames.config

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories

@Configuration
@EnableReactiveCassandraRepositories
class ReactiveCassandraConfiguration(
    private val cassandraProperties: CassandraProperties
) : AbstractReactiveCassandraConfiguration() {

    override fun getContactPoints() = cassandraProperties.contactPoints.joinToString(",")
    override fun getKeyspaceName() = cassandraProperties.keyspaceName!!
    override fun getLocalDataCenter() = cassandraProperties.localDatacenter!!
    override fun getPort() = cassandraProperties.port
    override fun getSchemaAction() = SchemaAction.valueOf(cassandraProperties.schemaAction.uppercase())
    override fun getKeyspaceCreations() = CreateKeyspaceSpecification
        .createKeyspace(cassandraProperties.keyspaceName)
        .withSimpleReplication()
        .ifNotExists()
        .let { listOf(it) }
}