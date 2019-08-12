package org.github.tarolas.travian.api.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<Player, Long> {
    fun findByUsernameAndServer(username: String, server: String) : Player?
}