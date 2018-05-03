package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.Game
import ktx.app.KtxScreen

/**
 * The screen that handles drawing and displaying and handling the graphics part of the app
 * Functions not only as where the player plays, but also the main menu
 */
class GameScreen(val app: Jump) : KtxScreen {

    //What handles showing what is going on currently
    val camera: OrthographicCamera = OrthographicCamera()
    //Where the previous camera baseline was for the bottom of the app
    var baseline = 0f
    //What handles showing the UI components
    val uiContainer = Stage()
    //The app that the app will take place in
    val game = Game()
    //Whether this screen is keeping a app running
    var isRunningGame = false

    /**
     * What happens when a GameScreen is created
     */
    init {
        this.camera.setToOrtho(false, this.game.width.toFloat(), this.game.height.toFloat())
        Gdx.input.inputProcessor = this.uiContainer
        this.uiContainer.addActor(mainMenuLayout)
    }

    /**
     * When the window is resized, the uiContainer is updated with the new dimensions
     */
    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        this.uiContainer.viewport.update(width, height, true)
    }

    /**
     * What the screen does every tick
     */
    override fun render(delta: Float) {

        //Clears the screen to sky blue
        Gdx.gl.glClearColor(.529f, .808f, .922f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //Updates the camera and the batch
        this.camera.update()
        this.app.batch.projectionMatrix = this.camera.combined

        //If the app is running and has been started
        if (this.isRunningGame && this.game.isRunning) {
            //Move towards a touch event if one happened
            if (Gdx.input.isTouched) {
                this.game.player.moveTowards(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
            }
            //Make the app tick and move the camera to the new app location
            this.game.act(delta)
            this.camera.translate(0f, this.game.currentBaseHeight - this.baseline)
            this.baseline = this.game.currentBaseHeight
        //If the app isn't currently running for whatever reason, but a touch event happened
        } else if (Gdx.input.isTouched) {
            //If the app was already started, just continue
            if (this.game.isRunning) {
                this.isRunningGame = true
            //Otherwise, if the app has yet to start, start it
            } else {
                mainMenuLayout.remove()
                this.uiContainer.addActor(mainGameLayout)
                this.isRunningGame = true
                this.game.start()
            }
        }

        //Allows the batch to draw sprites and draws the app
        this.app.batch.begin()
        this.game.draw(this.app.batch)
        this.app.batch.end()

        //Draws the UI components
        this.uiContainer.act(delta)
        this.uiContainer.draw()
    }

    //When the screen is hidden, the app is paused
    override fun hide() { this.isRunningGame = false }
    //When the screen is paused, so is the app
    override fun pause() { this.isRunningGame = false }

    /**
     * What the screen does when it is finished or destroyed
     */
    override fun dispose() {
        this.uiContainer.dispose()
    }

}
