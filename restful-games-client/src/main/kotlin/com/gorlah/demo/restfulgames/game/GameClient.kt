package com.gorlah.demo.restfulgames.game

import reactor.core.publisher.Mono
import java.util.UUID

interface GameClient {

    fun createGame(createRequest: Game.CreateRequest): Mono<Game>
    fun getGame(gameId: UUID): Mono<Game>
}