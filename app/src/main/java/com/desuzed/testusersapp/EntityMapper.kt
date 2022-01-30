package com.desuzed.testusersapp

interface EntityMapper <Entity, DomainModel> {
    fun mapFromEntity (entity: Entity) : DomainModel
}