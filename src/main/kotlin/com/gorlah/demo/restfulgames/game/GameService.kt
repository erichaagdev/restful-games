package com.gorlah.demo.restfulgames.game

import com.gorlah.demo.restfulgames.game.Game.CreateRequest
import com.gorlah.demo.restfulgames.util.IdGenerator
import com.gorlah.demo.restfulgames.util.TimestampGenerator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

@Service
class GameService(
    private val gameRepository: GameRepository,
    private val idGenerator: IdGenerator,
    private val timestampGenerator: TimestampGenerator,
) {

    fun createGame(createRequest: CreateRequest): Mono<Game> {
        val game = Game(
            createdOn = timestampGenerator.now(),
            id = idGenerator.generateId(),
            type = createRequest.type!!,
            user = createRequest.user!!,
        )

        return Mono.just(game)
            .map { GameById(it) }
            .flatMap { gameRepository.insert(it) }
            .map { game }
    }

    fun getGame(gameId: UUID): Mono<Game> = gameRepository.findById(gameId).map { it.toGame() }
}