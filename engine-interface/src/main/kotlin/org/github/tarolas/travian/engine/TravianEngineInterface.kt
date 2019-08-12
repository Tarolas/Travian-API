package org.github.tarolas.travian.engine

import org.github.tarolas.travian.engine.entities.*

interface TravianEngineInterface {

    suspend fun login(params: LoginParams): String?

    suspend fun getDorf1(params: GetDorf1Params): Dorf1

    suspend fun getDorf2(params: GetDorf2Params): Dorf2

    suspend fun getProfile(params: GetProfileParams): Profile

}