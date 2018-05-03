package com.saurabhtotey.jump

import com.badlogic.gdx.graphics.g2d.Batch
import com.saurabhtotey.jump.entity.Ground
import com.saurabhtotey.jump.entity.Player

/**
 * The class where the entire app takes place
 */
class Game {

    //The width of the app
    val width = 540f
    //The height of the app
    val height = 960f
    //Whether the app is in progress or not
    var isRunning = false
    //The maximum height that the player can appear to be above the bottom of the screen
    val maxPlayerRelativeHeight = this.height / 5
    //How high the app currently is; rises as the player of the app rises
    var currentBaseHeight = 0f
    //The app's gravity or acceleration in the downward direction
    val gravity = 2f
    //The app's player
    val player = Player(this)
    //A list of all the entities that are in the app
    val entities = listOf(Ground(this))

    /**
     * Draws the app and reflects any changes
     */
    fun draw(batch: Batch) {
        for (entity in this.entities + this.player) {
            batch.draw(entity.representation, entity.location.x, entity.location.y, entity.location.width, entity.location.height)
        }
    }

    /**
     * What the app does every tick; just ensures that all entities update
     */
    fun act(delta: Float) {
        if (!this.isRunning) {
            return
        }
        (this.entities + this.player).forEach { it.act(delta) }
        if (this.player.location.y > this.currentBaseHeight + this.maxPlayerRelativeHeight) {
            this.currentBaseHeight = this.player.location.y - this.maxPlayerRelativeHeight
        }
        if (this.player.location.y + this.player.location.width < this.currentBaseHeight) {
            this.isRunning = false
        }
    }

    /**
     * The procedure for starting the app
     */
    fun start() {
        if (this.isRunning) {
            return
        }
        this.player.jump()
        this.isRunning = true
    }

}