package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Game
import com.saurabhtotey.jump.Jump
import ktx.actors.onClick
import ktx.app.use
import ktx.scene2d.button
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.table

/**
 * First screen that the user sees; is a main menu
 */
class MenuScreen(app: Jump) : JumpScreen(app) {

    //A game that has yet to be started
    val emptyGame = Game()

    /**
     * Initializes all of the main menu's UI components
     */
    init {
        this.camera.setToOrtho(false, this.emptyGame.width, this.emptyGame.height)
        this.uiContainer.addActor(
            table {
                setFillParent(true)
                pad(5f)
                label("Tap anywhere to begin...") {
                    setFontScale(4f)
                    it.expand()
                }
                row().align(Align.bottomLeft)
                button {
                    image("OptionsButton")
                    onClick {

                    }
                    it.width(75f)
                    it.height(75f)
                }
            }
        )
    }

    /**
     * The periodic update/render method that updates all game elements and such
     */
    override fun act(delta: Float) {
        //Updates the game state
        this.emptyGame.act(delta)

        //Allows the batch to draw sprites and draws the app
        this.app.batch.use {
            this.emptyGame.draw(it)
        }

        //When the game is touched; changes screen to a game screen
        if (Gdx.input.isTouched) {
            this.app.screen = GameScreen(this.app, this.emptyGame)
            this.dispose()
        }
    }

}