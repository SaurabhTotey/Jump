package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.saurabhtotey.jump.Game
import ktx.math.*
import kotlin.math.abs

/**
 * The class that represents the player who is actually playing the app
 */
class Player(game: Game) : Entity(game) {

    /**
     * A set of player positions mapped to their respective sprites
     */
    enum class PositionTexture(val appearance: Sprite) {
        NEUTRAL(Sprite(Texture("images/player/PlayerNeutral.png"))),
        FALLING(Sprite(Texture("images/player/PlayerFalling.png"))),
        RIGHT(Sprite(Texture("images/player/PlayerRight.png"))),
        LEFT(Sprite(Texture("images/player/PlayerRight.png")).also { it.flip(true, false) })
    }

    //Where the player is along with the player's size
    override var location = Rectangle(game.width / 2 - 20, game.maxPlayerRelativeHeight, 40f, 80f)
    //The speed and direction that the player is moving towards in px/ms
    var velocity = Vector2(0f, 0f)
    //How the player looks; is based on the direction that the player is moving (velocity)
    override var representation: Sprite = PositionTexture.NEUTRAL.appearance
        get() = when {
            this.velocity.x > 0 -> PositionTexture.RIGHT.appearance
            this.velocity.x < 0 -> PositionTexture.LEFT.appearance
            this.velocity.y < 0 -> PositionTexture.FALLING.appearance
            else -> PositionTexture.NEUTRAL.appearance
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
        this.velocity.y = 5f
    }

    /**
     * Moves the player horizontally towards the given direction
     */
    fun changeHorizontalBy(amount: Float) {
        this.velocity.x = amount / 2
    }

}