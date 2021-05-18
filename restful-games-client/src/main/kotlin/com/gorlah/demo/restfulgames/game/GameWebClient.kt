package com.gorlah.demo.restfulgames.game

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.util.UUID

class GameWebClient(private val webClient: WebClient) : GameClient {

    override fun createGame(createRequest: Game.CreateRequest): Mono<Game> =
        webClient
            .post()
            .uri("/games")
            .bodyValue(createRequest)
            .retrieve()
            .bodyToMono()

    override fun getGame(gameId: UUID): Mono<Game> =
        webClient
            .get()
            .uri("/games/$gameId")
            .retrieve()
            .bodyToMono()
}