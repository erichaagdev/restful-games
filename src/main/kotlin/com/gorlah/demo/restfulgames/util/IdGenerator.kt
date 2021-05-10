package com.gorlah.demo.restfulgames.util

import java.util.UUID

fun interface IdGenerator {
    fun generateId(): UUID
}