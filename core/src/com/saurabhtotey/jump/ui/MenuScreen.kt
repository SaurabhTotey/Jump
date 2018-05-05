package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Game
import com.saurabhtotey.jump.Jump
import ktx.actors.onClick
import ktx.app.KtxScreen
import ktx.app.use
import ktx.scene2d.button
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.table

/**
 * First screen that the user sees; is a main menu
 */
class MenuScreen(val app: Jump) : KtxScreen {

    //A game that has yet to be started
    val emptyGame = Game()
    //What handles converting game world coordinates to actual coordinates and displaying the game correctly
    val camera = OrthographicCamera()
    //The stage that contains all the main menu UI elements
    val uiContainer = Stage()

    /**
     * Initializes all of the main menu's UI components
     */
    init {
        this.camera.setToOrtho(false, this.emptyGame.width, this.emptyGame.height)
        Gdx.input.inputProcessor = this.uiContainer
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
    override fun render(delta: Float) {
        //Clears the screen to sky blue
        Gdx.gl.glClearColor(.529f, .808f, .922f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //Updates the camera and the batch
        this.camera.update()
        this.app.batch.projectionMatrix = this.camera.combined

        //Updates the game state
        this.emptyGame.act(delta)

        //Allows the batch to draw sprites and draws the app
        this.app.batch.use {
            this.emptyGame.draw(it)
        }

        //Draws the UI components
        this.uiContainer.act(delta)
        this.uiContainer.draw()

        //When the game is touched; changes screen to a game screen
        if (Gdx.input.isTouched) {
            this.app.screen = GameScreen(this.app, this.emptyGame)
            this.dispose()
        }
    }

    /**
     * When the screen is resized, the UI container is adjusted
     */
    override fun resize(width: Int, height: Int) {
        this.uiContainer.viewport.update(width, height, true)
    }

    /**
     * When the screen is done, the UI container is disposed of
     */
    override fun dispose() {
        this.uiContainer.dispose()
    }

}