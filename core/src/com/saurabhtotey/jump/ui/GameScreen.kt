package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.Game
import ktx.actors.onClick
import ktx.app.KtxScreen
import ktx.app.use
import ktx.scene2d.button
import ktx.scene2d.image
import ktx.scene2d.table

/**
 * The screen that handles drawing and displaying and handling the graphics part of the app
 * Functions not only as where the player plays, but also the main menu
 */
class GameScreen(val app: Jump, val game: Game) : KtxScreen {

    //What handles showing what is going on currently
    val camera = OrthographicCamera()
    //Where the previous camera baseline was for the bottom of the app
    var baseline = 0f
    //What handles showing the UI components
    val uiContainer = Stage()

    /**
     * What happens when a GameScreen is created; initializes UI components
     */
    init {
        this.game.start()
        this.camera.setToOrtho(false, this.game.width, this.game.height)
        Gdx.input.inputProcessor = this.uiContainer
        this.uiContainer.addActor(table {
            setFillParent(true)
            pad(15f)
            button {
                image("PauseButton")
                onClick {

                }
                it.width(75f)
                it.height(75f)
            }
            align(Align.topLeft)
        })
    }

    /**
     * What the screen does every tick
     */
    override fun render(delta: Float) {
        //Clears the screen to sky blue
        Gdx.gl.glClearColor(.529f, .808f, .922f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //Updates game and moves camera based on game info
        this.game.act(delta)
        this.camera.translate(0f, this.game.currentBaseHeight - this.baseline)
        this.baseline = this.game.currentBaseHeight

        //Updates the camera and the batch
        this.camera.update()
        this.app.batch.projectionMatrix = this.camera.combined

        //Allows the batch to draw sprites and draws the app
        this.app.batch.use {
            this.game.draw(this.app.batch)
        }

        //Draws the UI components
        this.uiContainer.act(delta)
        this.uiContainer.draw()

        //If the screen is touched, moves the player towards the touch
        if (Gdx.input.isTouched) {
            val touchLocation = camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
            this.game.player.moveTowards(touchLocation.x)
        }
    }

    /**
     * When the window is resized, the uiContainer is updated with the new dimensions
     */
    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        this.uiContainer.viewport.update(width, height, true)
    }

    /**
     * What the screen does when it is finished or destroyed
     */
    override fun dispose() {
        this.uiContainer.dispose()
    }

}
