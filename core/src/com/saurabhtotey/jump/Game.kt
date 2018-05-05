package com.saurabhtotey.jump

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.saurabhtotey.jump.entity.Coin
import com.saurabhtotey.jump.entity.Entity
import com.saurabhtotey.jump.entity.Ground
import com.saurabhtotey.jump.entity.Player
import kotlin.math.ceil
import kotlin.math.roundToInt

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
    //Whether the game is finished or not; is determined by whether the player has fallen out of the world yet
    var isFinished = false
        get() = this.player.location.y + this.player.location.height < this.currentBaseHeight
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

    //When a game is constructed, the constructor handles spawning the initial coin layout
    init {
        val rarity = 400
        for (i in 0 until this.width.roundToInt() step 10) {
            for (j in (this.player.location.y + this.player.location.height).roundToInt() until this.height.roundToInt() step 10) {
                if ((Math.random() * rarity).roundToInt() == 0) {
                    this.entities.add(Coin(this, Vector2(i.toFloat(), j.toFloat())))
                }
            }
        }
    }

    /**
     * Draws the app and reflects any changes
     */
    fun draw(batch: Batch) {
        (this.entities + this.player).forEach { batch.draw(it.representation, it.location.x, it.location.y, it.location.width, it.location.height) }
    }

    /**
     * What the app does every tick; just ensures that all entities update
     */
    fun act(delta: Float) {
        //Game will not act if it isn't running or it is finished
        if (this.isFinished) {
            this.isRunning = false
            return
        }
        //Tells each entity including player to act
        (this.entities + this.player).forEach { it.act(delta) }
        //Gets rid of all entities that have been passed and are out of the screen
        this.entities = this.entities.filterNot { it.location.y + it.location.height < this.currentBaseHeight }.toMutableList()
        //If any entity goes too much to one side, it will emerge out the other; TODO: make this work for being partially out of the edge
        (this.entities + this.player).forEach {
            if (it.location.x > width) {
                it.location.x %= width
            } else if (it.location.x + it.location.width < 0) {
                it.location.x += width * ceil(it.location.x / width)
            }
        }
        if (this.isRunning) {
            //Coin spawning algorithm; TODO: is temporary
            if (this.entities.size < 15) {
                this.entities.add(Coin(this, Vector2(Math.random().toFloat() * this.width, this.currentBaseHeight + this.height + Math.random().toFloat() * this.maxPlayerRelativeHeight)))
            }
        }
        //Updates the base height if the player is above the maxPlayerRelativeHeight
        if (this.player.location.y > this.currentBaseHeight + this.maxPlayerRelativeHeight) {
            this.currentBaseHeight = this.player.location.y - this.maxPlayerRelativeHeight
        }
    }

    /**
     * The procedure for starting the app
     */
    fun start() {
        if (this.isRunning) {
            return
        }
        this.player.location.y += 1
        this.player.jump()
        this.isRunning = true
    }

}