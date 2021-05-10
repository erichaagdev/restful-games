package com.gorlah.demo.restfulgames.game

import com.gorlah.demo.restfulgames.game.Game.CreateRequest
import com.gorlah.demo.restfulgames.util.IntegrationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity

@IntegrationTest
class GameControllerTests {

    @LocalServerPort private lateinit var serverPort: String
    private lateinit var webClient: WebClient

    @BeforeEach
    fun beforeEach() {
        webClient = WebClient.builder()
            .baseUrl("http://localhost:$serverPort")
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }

    @Test
    fun `when creating and fetching game then response returns game`() {
        val createRequest = CreateRequest(
            type = GameType.TIC_TAC_TOE,
            user = "gorlah"
        )

        val game = createGame(createRequest)
            .log()
            .block()

        Assertions.assertNotNull(game)
        Assertions.assertEquals(HttpStatus.OK, game!!.statusCode)
    }

    fun createGame(createRequest: CreateRequest) =
        webClient
            .post()
            .uri("/games")
            .bodyValue(createRequest)
            .retrieve()
            .toEntity<Game>()
            .map { it.headers.location!! }
            .flatMap { webClient.get().uri(it.path).retrieve().toEntity<Game>() }
}