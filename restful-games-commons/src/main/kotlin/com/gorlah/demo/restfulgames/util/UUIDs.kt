package com.gorlah.demo.restfulgames.util

import java.util.UUID

fun uuid(): UUID = UUID.randomUUID()
fun uuid(uuidString: String): UUID = UUID.fromString(uuidString)
fun uuid(uuidLong: Long): UUID = UUID(0, uuidLong)