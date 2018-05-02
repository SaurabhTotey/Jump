package com.saurabhtotey.jump

import com.badlogic.gdx.graphics.g2d.BitmapFont
import ktx.app.KtxGame

/**
 * Main point of entry for all platforms of the game
 */
class Jump : KtxGame<MainMenuScreen>() {

    //The font that will be used to draw the game's text
    lateinit var textDrawer: BitmapFont

    /**
     * What happens when the game is made
     */
    override fun create() {
        super.create()
        this.textDrawer = BitmapFont()
    }

    /**
     * Handles disposing of and destroying the game and its resources
     */
    override fun dispose() {
        super.dispose()
        this.textDrawer.dispose()
    }

}
