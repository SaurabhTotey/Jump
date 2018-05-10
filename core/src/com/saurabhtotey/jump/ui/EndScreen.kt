package com.saurabhtotey.jump.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.utils.Align
import com.saurabhtotey.jump.Game
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.UserData
import ktx.actors.onClick
import ktx.scene2d.button
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.table

/**
 * The screen that is what appears at the end of the game
 */
class EndScreen(app: Jump, score: Int, collectedCoins: Int) : JumpScreen(app) {

    /**
     * Makes an endscreen and initializes all of the UI Components
     */
    init {
        this.uiContainer.addActor(
            table {
                setFillParent(true)
                pad(5f)
                label("YOU FELL AFTER JUMPING ${score}m!\nYOU COLLECTED $collectedCoins COINS!") {
                    setFontScale(1.8f)
                    it.expand()
                    it.colspan(3)
                }
                row().align(Align.bottom)
                button {
                    it.width(75f)
                    it.height(75f)
                    image("HomeButton")
                    onClick {
                        app.screen = MenuScreen(app)
                        dispose()
                    }
                }
                button {
                    it.width(75f)
                    it.height(75f)
                    image("ReplayButton")
                    onClick {
                        app.screen = GameScreen(app, Game())
                        dispose()
                    }
                }
                button {
                    it.width(75f)
                    it.height(75f)
                    image("StoreButton")
                    onClick {  }
                }
            }
        )
        //Updates user data based on game outcome
        UserData.numCoins += collectedCoins
        UserData.scores += score
    }

    /**
     * The endscreen just shows up as black
     */
    override fun act(delta: Float) {
        //Clears the screen to black
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    /**
     * When the back button is passed, the game goes back to the main menu
     */
    override fun onBack() {
        this.app.screen = MenuScreen(this.app)
        this.dispose()
    }

}