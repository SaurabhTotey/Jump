package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.Game
import ktx.actors.onClick
import ktx.app.use
import ktx.scene2d.button
import ktx.scene2d.image
import ktx.scene2d.table

/**
 * The screen that handles drawing and displaying and handling the graphics part of the app
 * Functions not only as where the player plays, but also the main menu
 */
class GameScreen(app: Jump, val game: Game) : JumpScreen(app) {

    //Where the previous camera baseline was for the bottom of the app
    var cameraBaseline = 0f

    /**
     * What happens when a GameScreen is created; initializes UI components
     */
    init {
        this.game.start()
        this.camera.setToOrtho(false, this.game.width, this.game.height)
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
    override fun act(delta: Float) {
        //Updates game and moves camera based on game info
        this.game.act(delta)
        this.camera.translate(0f, this.game.currentBaseHeight - this.cameraBaseline)
        this.cameraBaseline = this.game.currentBaseHeight

        //Allows the batch to draw sprites and draws the app
        this.app.batch.use {
            this.game.draw(this.app.batch)
        }

        //If the screen is touched, moves the player towards the touch
        if (Gdx.input.isTouched) {
            this.game.player.moveTowards(camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)).x)
        }
    }

}
