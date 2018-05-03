package com.saurabhtotey.jump

import com.badlogic.gdx.graphics.g2d.Batch
import com.saurabhtotey.jump.entity.Ground
import com.saurabhtotey.jump.entity.Player

/**
 * The class where the entire game takes place
 */
class World {

    //The width of the world
    val width = 540f
    //The height of the world
    val height = 960f
    //The maximum height that the player can appear to be above the bottom of the screen
    val maxPlayerRelativeHeight = this.height / 5
    //How high the game currently is; rises as the player of the game rises
    var currentBaseHeight = 0f
    //The speed of the screen rise in px/ms
    var screenRise = 0f
    //The world's gravity or acceleration in the downward direction
    val gravity = 2f
    //The world's player
    val player = Player(this)
    //A list of all the entities that are in the world
    val entities = listOf(Ground(this))

    /**
     * Draws the world and reflects any changes
     */
    fun draw(batch: Batch) {
        for (entity in this.entities + this.player) {
            batch.draw(entity.representation, entity.location.x, entity.location.y, entity.location.width, entity.location.height)
        }
    }

    /**
     * What the world does every tick; just ensures that all entities update
     */
    fun act(delta: Float) {
        this.currentBaseHeight += delta * screenRise
        (this.entities + this.player).forEach { it.act(delta) }
    }

}