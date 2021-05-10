package com.gorlah.demo.restfulgames.game

import com.gorlah.demo.restfulgames.game.Game.CreateRequest
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
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
    fun createGame(@RequestBody createRequest: CreateRequest): Mono<ResponseEntity<Unit>> =
        gameService.createGame(createRequest)
            .map { linkTo(methodOn(GameController::class.java).getGame(it.id)).withSelfRel() }
            .map { ResponseEntity.created(it.toUri()).build() }

    @GetMapping("/{gameId}")
    fun getGame(@PathVariable gameId: UUID): Mono<EntityModel<Game>> =
        gameService.getGame(gameId)
            .map { EntityModel.of(it).add(linkTo(methodOn(GameController::class.java).getGame(it.id)).withSelfRel()) }
}