package org.github.tarolas.travian.common.mapper

import org.modelmapper.ModelMapper

object Mapper {

    private val mapper: ModelMapper = ModelMapper()

    fun <T> map(source: Any?, clazz: Class<T>) = mapper.map(source, clazz)

    fun <T : Any, C : Collection<*>> mapCollection(source: C?, clazz: Class<T>) = source?.map { map(it, clazz) }

}