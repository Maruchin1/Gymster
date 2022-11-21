package com.maruchin.core.utils

import java.util.UUID

@JvmInline
value class Id(val value: String) {

    companion object {

        val empty = Id("")

        fun generate() = Id(UUID.randomUUID().toString())
    }
}
