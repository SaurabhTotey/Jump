package com.saurabhtotey.jump

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.saurabhtotey.jump.entity.Coin
import com.saurabhtotey.jump.entity.Entity
import com.saurabhtotey.jump.entity.Ground
import com.saurabhtotey.jump.entity.Player
import kotlin.math.ceil

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
    var entities = mutableListOf<Entity>(Ground(this))

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
        this.entities = this.entities.filterNot { it.location.y + it.location.height < this.currentBaseHeight }.toMutableList()
        (this.entities + this.player).forEach {
            if (it.location.x > width) {
                it.location.x %= width
            } else if (it.location.x + it.location.width < 0) {
                it.location.x += width * ceil(it.location.x / width)
            }
        }
        //TODO: below is temporary
        if (this.entities.size < 10) {
            this.entities.add(Coin(this, Vector2(Math.random().toFloat() * this.width, this.currentBaseHeight + Math.random().toFloat() * this.height)))
        }
        //TODO: above is temporary
        if (this.player.location.y > this.currentBaseHeight + this.maxPlayerRelativeHeight) {
            this.currentBaseHeight = this.player.location.y - this.maxPlayerRelativeHeight
        }
        if (this.player.location.y + this.player.location.height < this.currentBaseHeight) {
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