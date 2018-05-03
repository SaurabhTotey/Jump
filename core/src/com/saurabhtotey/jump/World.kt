package com.saurabhtotey.jump

import com.badlogic.gdx.graphics.g2d.Batch
import com.saurabhtotey.jump.entity.Ground

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
    //A list of all the entities that are in the world
    val entities = listOf(Ground(this))

    /**
     * Draws the world and reflects any changes
     */
    fun draw(batch: Batch) {
        for (entity in this.entities) {
            batch.draw(entity.representation, entity.location.x, entity.location.y, entity.location.width, entity.location.height)
        }
    }

    /**
     * What the world does every tick; just ensures that all entities update
     */
    fun act(delta: Float) {
        this.currentBaseHeight += delta
        this.entities.forEach { it.act(delta) }
    }

}