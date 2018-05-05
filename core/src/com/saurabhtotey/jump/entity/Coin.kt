package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.saurabhtotey.jump.Game

/**
 * What the player collects in order to get higher
 */
class Coin(game: Game, placement: Vector2) : Entity(game) {

    /**
     * A static object that holds the sprite of the coin so that only one sprite is used between all coins
     */
    companion object {
        val sprite = Sprite(Texture("images/Coin.png"))
        val size = 20
    }

    override var location = Rectangle(0f, 0f, size.toFloat(), size.toFloat()).also { it.setCenter(placement) }
    override var representation = sprite

    /**
     * Coins check for collisions with players; if the player is touching, they will jump
     */
    override fun act(delta: Float) {
        if (this.game.player.location.overlaps(this.location)) {
            this.game.player.jump()
        }
    }

}