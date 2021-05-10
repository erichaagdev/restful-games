package com.gorlah.demo.restfulgames.game

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import java.util.UUID

interface GameRepository : ReactiveCassandraRepository<GameById, UUID>