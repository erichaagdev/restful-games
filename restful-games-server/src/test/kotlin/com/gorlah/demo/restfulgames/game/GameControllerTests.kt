package com.gorlah.demo.restfulgames.game

import com.gorlah.demo.restfulgames.game.Game.CreateRequest
import com.gorlah.demo.restfulgames.util.IntegrationTest
import com.gorlah.demo.restfulgames.util.uuid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import kotlin.test.assertEquals

@IntegrationTest
class GameControllerTests {

    @LocalServerPort private lateinit var serverPort: String
    @Autowired private lateinit var gameRepository: GameRepository
    private lateinit var gameClient: GameClient

    @BeforeEach
    fun beforeEach() {
        gameClient = WebClient.builder()
            .baseUrl("http://localhost:$serverPort")
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build()
            .let { GameWebClient(it) }
    }

    @Test
    fun `when creating a game, then response returns game`() {
        val createRequest = CreateRequest(
            type = GameType.TIC_TAC_TOE,
            user = "gorlah"
        )

        val game = gameClient
            .createGame(createRequest)
            .block()!!

        assertEquals(GameType.TIC_TAC_TOE, game.type)
        assertEquals("gorlah",game.user)
    }

    @Test
    fun `given game exists, when fetching game, then response returns expected game`() {
        val gameById = GameById(
            createdOn = 1234567890,
            id = uuid(0),
            type = GameType.TIC_TAC_TOE,
            user = "gorlah"
        )

        gameRepository.insert(gameById).block()

        val expected = Game(
            createdOn = 1234567890,
            id = uuid(0),
            type = GameType.TIC_TAC_TOE,
            user = "gorlah"
        )

        val actual = gameClient
            .getGame(uuid(0))
            .block()!!

        assertEquals(expected, actual)
    }
}