package com.gorlah.demo.restfulgames.game

import com.gorlah.demo.restfulgames.game.Game.CreateRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/games")
class GameController(
    private val gameService: GameService,
) {

    @PostMapping
    fun createGame(@RequestBody createRequest: CreateRequest): Mono<Game> =
        gameService.createGame(createRequest)

    @GetMapping("/{gameId}")
    fun getGame(@PathVariable gameId: UUID): Mono<Game> =
        gameService.getGame(gameId)
}