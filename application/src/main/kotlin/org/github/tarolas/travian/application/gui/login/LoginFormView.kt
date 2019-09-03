package org.github.tarolas.travian.application.gui.login

import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.controlsfx.glyphfont.FontAwesome.Glyph
import org.github.tarolas.travian.application.gui.login.LoginFormConstants.TAB_NAME
import org.github.tarolas.travian.application.utils.GlyphFactory

@Component
class LoginFormView(val model: LoginFormModel) : BorderPane() {

    private val log = LoggerFactory.getLogger(LoginFormView::class.java)

    // GridPane
    internal var gpMainGrid = GridPane()

    // Labels
    internal var lbUsername = Label("Username:")
    internal var lbPassword = Label("Password:")
    internal var lbServer = Label("Server:")

    // Fields
    internal var tfUserName = TextField()
    internal var tfPassword = TextField()
    internal var tfServer = TextField()

    // Buttons
    internal var btnSave = Button("LogIn")
    internal var btnClose = Button("Close")

    init {
        log.debug("Initialized [{}]", javaClass.simpleName)
        Platform.runLater {
            userData = TAB_NAME
            layoutForm()
            initFieldData()
            bindFieldsToModel()
        }
    }

    protected fun initFieldData() {
        // Id and names are populated using bindings.
        // Populate birth date and age category
        btnSave.graphic = GlyphFactory.create(Glyph.SAVE)
        btnClose.graphic = GlyphFactory.create(Glyph.CLOSE)
    }

    protected fun layoutForm() {
        gpMainGrid.alignment = Pos.CENTER

        gpMainGrid.hgap = 5.0
        gpMainGrid.vgap = 5.0

        gpMainGrid.add(lbUsername, 1, 1)
        gpMainGrid.add(lbPassword, 1, 2)
        gpMainGrid.add(lbServer, 1, 3)

        gpMainGrid.add(tfUserName, 2, 1)
        gpMainGrid.add(tfPassword, 2, 2)
        gpMainGrid.add(tfServer, 2, 3)

        // Add buttons and make them the same width
        val buttonBox = VBox(btnSave, btnClose)
        buttonBox.spacing = 2.0
        btnSave.maxWidth = java.lang.Double.MAX_VALUE
        btnClose.maxWidth = java.lang.Double.MAX_VALUE

        // TitledBorderedPane titledPane = Borders.wrapWithTitledBorderedPane("Edit",
        // null, buttonBox) titlepane example
        gpMainGrid.add(buttonBox, 3, 1, 1, 5)

        // Set the prompt text for the birth date field
        center = gpMainGrid
    }

    protected fun bindFieldsToModel() {
        tfUserName.textProperty().bind(model.username)
        tfPassword.textProperty().bindBidirectional(model.password)
        tfServer.textProperty().bindBidirectional(model.server)
    }

}
