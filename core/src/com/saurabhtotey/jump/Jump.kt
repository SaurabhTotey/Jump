package com.saurabhtotey.jump

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.scene2d.Scene2DSkin

/**
 * Main point of entry for all platforms of the game
 */
class Jump : Game() {

    //The font that will be used to draw the game's text
    lateinit var textDrawer: BitmapFont
    //The batch that will be used to draw any sprites
    lateinit var batch: Batch

    /**
     * What happens when the game is made
     */
    override fun create() {
        Scene2DSkin.defaultSkin = Skin(Gdx.files.internal("skin/uiskin.json"))
        this.textDrawer = BitmapFont()
        this.screen = MainMenuScreen(this)
        this.batch = SpriteBatch()
    }

    /**
     * Handles disposing of and destroying the game and its resources
     */
    override fun dispose() {
        super.dispose()
        this.textDrawer.dispose()
    }

}
