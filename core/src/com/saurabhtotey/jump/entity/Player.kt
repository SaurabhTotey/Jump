package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.saurabhtotey.jump.Game
import ktx.math.*

/**
 * The class that represents the player who is actually playing the app
 */
class Player(game: Game) : Entity(game) {

    //Where the player is along with the player's size
    override var location = Rectangle(game.width / 2 - 20, game.maxPlayerRelativeHeight, 40f, 80f)
    //The speed and direction that the player is moving towards in px/ms
    var velocity = Vector2(0f, 0f)
    //How the player looks; is based on the direction that the player is moving (velocity)
    override var representation: Sprite = game.assets.getSprite("PlayerNeutral")
        get() = when {
            this.velocity.x > 0 -> game.assets.getSprite("PlayerRight")
            this.velocity.x < 0 -> game.assets.getSprite("PlayerLeft")
            this.velocity.y < 0 -> game.assets.getSprite("PlayerFalling")
            else -> game.assets.getSprite("PlayerNeutral")
        }


    /**
     * Every turn, the player adjusts its location by its velocity and changes the velocity of the player based on the app gravity
     */
    override fun act(delta: Float) {
        this.location.setPosition(Vector2().also { this.location.getPosition(it) } + this.velocity)
        this.velocity.y -= this.game.gravity * delta
        val horizontalCorrectionalAcceleration = this.game.gravity * delta * 3
        if (this.velocity.x < 0) {
            this.velocity.x += horizontalCorrectionalAcceleration
            if (this.velocity.x > 0) {
                this.velocity.x = 0f
            }
        } else if (this.velocity.x > 0) {
            this.velocity.x -= horizontalCorrectionalAcceleration
            if (this.velocity.x < 0) {
                this.velocity.x = 0f
            }
        }
    }

    /**
     * Makes the player jump
     */
    fun jump() {
        this.velocity.y = 15f
    }

    /**
     * Moves the player horizontally towards the given direction
     */
    fun changeHorizontalBy(amount: Float) {
        this.velocity.x = amount / 2
    }

}