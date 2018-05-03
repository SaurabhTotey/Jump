package com.saurabhtotey.jump.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.saurabhtotey.jump.Jump
import com.saurabhtotey.jump.World
import ktx.app.KtxScreen
import ktx.scene2d.button
import ktx.scene2d.label
import ktx.scene2d.table

/**
 * The screen that is the main menu of the game
 */
class MainMenuScreen(val game: Jump) : KtxScreen {

    //What handles showing what is going on currently
    val camera: OrthographicCamera = OrthographicCamera()
    //What handles showing the UI components
    val stage = Stage()
    //The world that the game will take place in
    val world = World()

    /**
     * What happens when a MainMenuScreen is created
     */
    init {
        this.camera.setToOrtho(false, this.world.width.toFloat(), this.world.height.toFloat())
        Gdx.input.inputProcessor = this.stage
        this.stage.addActor(table {
            setFillParent(true)
            pad(5f)
            button {
                label("Hello!") {
                    setFontScale(10f)
                }
                pad(100f)
            }
        })
    }

    /**
     * When the window is resized, the stage is updated with the new dimensions
     */
    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        this.stage.viewport.update(width, height, true)
    }

    /**
     * What the screen does every tick
     */
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(.529f, .808f, .922f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        this.camera.update()
        this.game.batch.projectionMatrix = this.camera.combined
        this.game.batch.begin()
        this.world.draw(this.game.batch)
        this.game.batch.end()
        this.stage.act(delta)
        this.stage.draw()
    }

    /**
     * What the screen does when it is finished or destroyed
     */
    override fun dispose() {
        this.stage.dispose()
    }

}
