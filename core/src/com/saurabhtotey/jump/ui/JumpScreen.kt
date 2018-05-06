package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.saurabhtotey.jump.Jump
import ktx.app.KtxScreen

/**
 * A simple class that all other screens in the game will extend from
 */
abstract class JumpScreen(val app: Jump) : KtxScreen {

    //The object that will hold all the UI elements
    val uiContainer = Stage()
    //The camera that will handle drawing all other game things and such
    val camera = OrthographicCamera()

    /**
     * Registers UI components as input processors
     */
    init {
        Gdx.input.inputProcessor = this.uiContainer
    }

    /**
     * What the screen does by default every tick or update
     */
    override fun render(delta: Float) {
        //Clears the screen to sky blue
        Gdx.gl.glClearColor(.529f, .808f, .922f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //Updates the camera and the batch
        this.camera.update()
        this.app.batch.projectionMatrix = this.camera.combined

        //Updates all the UI components and then the game
        this.uiContainer.act(delta)
        this.act(delta)

        //Draws the UI components
        this.uiContainer.draw()
    }

    /**
     * What the screen does every update or tick
     */
    abstract fun act(delta: Float)

    /**
     * When the screen gets resized, updates the locations of the UI components
     */
    override fun resize(width: Int, height: Int) {
        this.uiContainer.viewport.update(width, height)
    }

    /**
     * When the screen gets disposed, ensures that the UI components also get disposed of
     */
    override fun dispose() {
        this.uiContainer.dispose()
    }

}