package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.World
import ktx.app.KtxScreen

/**
 * The screen that handles drawing and displaying and handling the graphics part of the game
 * Functions not only as where the player plays, but also the main menu
 */
class GameScreen(val game: Jump) : KtxScreen {

    //What handles showing what is going on currently
    val camera: OrthographicCamera = OrthographicCamera()
    //Where the previous camera baseline was for the bottom of the world
    var baseline = 0f
    //What handles showing the UI components
    val uiContainer = Stage()
    //The world that the game will take place in
    val world = World()
    //Whether a game is in progress
    var isGameRunning = false

    /**
     * What happens when a GameScreen is created
     */
    init {
        this.camera.setToOrtho(false, this.world.width.toFloat(), this.world.height.toFloat())
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
        this.game.batch.projectionMatrix = this.camera.combined

        //If the game is running and has been started
        if (this.isGameRunning && this.world.isInProgress) {
            //Move towards a touch event if one happened
            if (Gdx.input.isTouched) {
                this.world.player.moveTowards(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
            }
            //Make the world tick and move the camera to the new world location
            this.world.act(delta)
            this.camera.translate(0f, this.world.currentBaseHeight - this.baseline)
            this.baseline = this.world.currentBaseHeight
        //If the game isn't currently running for whatever reason, but a touch event happened
        } else if (Gdx.input.isTouched) {
            //If the game was already started, just continue
            if (this.world.isInProgress) {
                this.isGameRunning = true
            //Otherwise, if the game has yet to start, start it
            } else {
                mainMenuLayout.remove()
                this.uiContainer.addActor(mainGameLayout)
                this.isGameRunning = true
                this.world.start()
            }
        }

        //Allows the batch to draw sprites and draws the world
        this.game.batch.begin()
        this.world.draw(this.game.batch)
        this.game.batch.end()

        //Draws the UI components
        this.uiContainer.act(delta)
        this.uiContainer.draw()
    }

    //When the screen is hidden, the game is paused
    override fun hide() { this.isGameRunning = false }
    //When the screen is paused, so is the game
    override fun pause() { this.isGameRunning = false }

    /**
     * What the screen does when it is finished or destroyed
     */
    override fun dispose() {
        this.uiContainer.dispose()
    }

}
