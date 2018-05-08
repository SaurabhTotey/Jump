package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Jump
import ktx.scene2d.button
import ktx.scene2d.label
import ktx.scene2d.table

/**
 * The screen that is what appears at the end of the game
 */
class EndScreen(app: Jump, score: Int) : JumpScreen(app) {

    /**
     * Makes an endscreen and initializes all of the UI Components
     */
    init {
        this.uiContainer.addActor(
            table {
                setFillParent(true)
                pad(5f)
                label("YOU DIED AFTER CLIMBING ${score}m!") {
                    setFontScale(1.8f)
                    it.expand()
                    it.colspan(3)
                }
                row().align(Align.bottom)
                button {
                    it.width(75f)
                    it.height(75f)
                    label("Home")
                    //TODO: home button
                }
                button {
                    it.width(75f)
                    it.height(75f)
                    label("Replay")
                    //TODO: replay button
                }
                button {
                    it.width(75f)
                    it.height(75f)
                    label("Store")
                    //TODO: in-game store button
                }
            }
        )
    }

    /**
     * The endscreen just shows up as black
     */
    override fun act(delta: Float) {
        //Clears the screen to black
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

}