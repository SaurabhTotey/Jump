package com.saurabhtotey.jump

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.saurabhtotey.jump.ui.MenuScreen
import ktx.scene2d.Scene2DSkin

/**
 * Main point of entry for all platforms of the app
 */
class Jump : Game() {

    //The font that will be used to draw the app's text
    lateinit var textDrawer: BitmapFont
    //The batch that will be used to draw any sprites
    lateinit var batch: Batch

    /**
     * What happens when the app is made
     */
    override fun create() {
        Scene2DSkin.defaultSkin = Skin(Gdx.files.internal("defaultSkin/skin/uiskin.json")).also {
            it.add("PauseButton", Texture("images/PauseButton.png"))
            it.add("ResumeButton", Texture("images/ResumeButton.png"))
            it.add("OptionsButton", Texture("images/OptionsButton.png"))
            it.add("HomeButton", Texture("images/Home.png"))
            it.add("ReplayButton", Texture("images/Replay.png"))
            it.add("StoreButton", Texture("images/Store.png"))
        }
        Gdx.input.isCatchBackKey = true
        this.textDrawer = BitmapFont()
        this.screen = MenuScreen(this)
        this.batch = SpriteBatch()
    }

    /**
     * Handles disposing of and destroying the app and its resources
     */
    override fun dispose() {
        super.dispose()
        this.textDrawer.dispose()
    }

}
