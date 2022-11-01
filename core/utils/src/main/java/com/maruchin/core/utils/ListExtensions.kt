package com.maruchin.core.utils

fun <T : Entity> List<T>.addEntity(entity: T): List<T> =
    this + entity

fun <T : Entity> List<T>.updateEntity(id: String, update: (T) -> T): List<T> =
    this.map { if (it.id == id) update(it) else it }

fun <T : Entity> List<T>.upsertEntity(entity: T): List<T> =
    if (hasEntity(entity.id)) updateEntity(entity.id) { entity } else addEntity(entity)

fun <T : Entity> List<T>.removeEntity(id: String): List<T> =
    this.mapNotNull { if (it.id == id) null else it }

fun <T : Entity> List<T>.findEntity(id: String): T? =
    this.find { it.id == id }

fun <T : Entity> List<T>.hasEntity(id: String): Boolean =
    this.findEntity(id) != null
