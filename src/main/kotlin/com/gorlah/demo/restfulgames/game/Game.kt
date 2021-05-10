package com.gorlah.demo.restfulgames.game

import java.util.UUID
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class Game(
    val createdOn: Long,
    val id: UUID,
    val type: GameType,
    val user: String,
) {

    data class CreateRequest(

        @field:NotNull
        val type: GameType? = null,

        @field:NotEmpty
        val user: String? = null,
    )
}