package org.github.tarolas.travian.application.utils

import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFont
import org.controlsfx.glyphfont.GlyphFontRegistry

import java.io.InputStream

/**
 * Factory class for glyphs from Fontawesome library. <br></br>
 * **Usage example:**
 *
 * <pre>
 * Glyph g = GlyphFactory.create(FontAwesome.Glyph.CLOSE);
 * g.setTextFill(Color.RED);
 * Button button = new Button("", g);
</pre> *
 *
 */
object GlyphFactory {
    private val fontawesome: GlyphFont

    init {
        val `is` = GlyphFactory::class.java.getResourceAsStream("/fonts/fontawesome-webfont")
        fontawesome = FontAwesome(`is`)
        GlyphFontRegistry.register(fontawesome)
    }

    /**
     * Creates a glyph for given identifier
     * @param icon one of the font code available in [FontAwesome] Glyph enum.
     * @return created glyph
     */
    fun create(icon: FontAwesome.Glyph): Glyph {
        return fontawesome.create(icon)
    }

}
