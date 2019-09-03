package org.github.tarolas.travian.application.gui.login

import javafx.stage.Screen

object LoginFormConstants {

    // Tab Name
    val TAB_NAME = "Form"

    // File paths
    val CSS_PATH = "/styles/bootstrap3.css"

    // Window Properties
    val MIN_WIDTH = 400
    val MIN_HEIGHT = 400

    val PREF_WIDTH: Double? = Screen.getPrimary().visualBounds.width / 2
    val PREF_HEIGHT: Double? = Screen.getPrimary().visualBounds.height / 2

    val ERROR_MSG = "Oops.. Something went wrong!"

}
