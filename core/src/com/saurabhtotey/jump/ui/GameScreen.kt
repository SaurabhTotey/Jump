package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.Game
import ktx.actors.onClick
import ktx.app.use
import ktx.scene2d.*

/**
 * The screen that handles drawing and displaying and handling the graphics part of the app
 * Functions not only as where the player plays, but also the main menu
 */
class GameScreen(app: Jump, val game: Game) : JumpScreen(app) {

    //Where the previous camera baseline was for the bottom of the app
    var cameraBaseline = 0f
    //The label that displays the score
    lateinit var scoreLabel: Label
    //Whether the current device can use tilt controls
    val isTiltAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)
    //Whether the game should be paused or not
    var isPaused = false

    /**
     * What happens when a GameScreen is created; initializes UI components
     */
    init {
        this.game.start()
        this.camera.setToOrtho(false, this.game.width, this.game.height)
        this.uiContainer.addActor(
            table {
                setFillParent(true)
                pad(15f)
                button {
                    val pauseImage = image("PauseButton")
                    onClick {
                        isPaused = !isPaused
                        pauseImage.drawable = Scene2DSkin.defaultSkin.getDrawable(if (isPaused) "ResumeButton" else "PauseButton")
                    }
                    it.width(75f)
                    it.height(75f)
                }
                scoreLabel = label("") {
                    it.expandX()
                    it.right()
                    setFontScale(3f)
                }
                align(Align.topLeft)
            }
        )
    }

    /**
     * What the screen does every tick
     */
    override fun act(delta: Float) {
        //Updates game and moves camera based on game info
        if (!this.isPaused) {
            this.game.act(delta)
        }
        this.camera.translate(0f, this.game.currentBaseHeight - this.cameraBaseline)
        this.cameraBaseline = this.game.currentBaseHeight
        val gameScore = this.game.currentBaseHeight.toInt()
        this.scoreLabel.setText("$gameScore")

        //Allows the batch to draw sprites and draws the app
        this.app.batch.use {
            this.game.draw(this.app.batch)
        }


        //If the game is finished, switch screens
        if (this.game.isFinished) {
            this.app.screen = EndScreen(this.app, gameScore)
            this.dispose()
        }
        //If the game is paused, doesn't look for controls
        if (this.isPaused) {
            return
        }

        //Uses the accelerometer to control movement with phone tilt if available
        if (this.isTiltAvailable) {
            this.game.player.changeHorizontalBy(Gdx.input.accelerometerX * -3)
        }
        //If a key is pressed, move towards key direction
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.game.player.changeHorizontalBy(10f * if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) -1 else 1)
        }
    }

    override fun pause() { this.isPaused = true }
    override fun hide() { this.isPaused = true }

}
