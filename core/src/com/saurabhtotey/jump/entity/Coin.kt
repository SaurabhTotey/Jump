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
        const val size = 20
    }

    //The location of the coin is given in the constructor as the center; width and height are constant for all coins
    override var location = Rectangle(0f, 0f, size.toFloat(), size.toFloat()).also { it.setCenter(placement) }
    //All coins look the same and have the same sprite
    override var representation = sprite

    /**
     * Coins check for collisions with players; if the player is touching, they will jump and the coin will be removed from the game
     */
    override fun act(delta: Float) {
        if (!this.game.player.location.overlaps(this.location)) {
            return
        }
        this.game.player.jump()
        this.game.entities.remove(this)
    }

}