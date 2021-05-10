package com.gorlah.demo.restfulgames.game

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table
data class GameById(
    @Id val id: UUID,
    val createdOn: Long,
    val type: GameType,
    val user: String,
) {

    constructor(game: Game) : this(
        createdOn = game.createdOn,
        id = game.id,
        type = game.type,
        user = game.user,
    )

    fun toGame(): Game = Game(
        createdOn = this.createdOn,
        id = this.id,
        type = this.type,
        user = this.user,
    )
}