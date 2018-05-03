package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.saurabhtotey.jump.World
import ktx.math.*
import kotlin.math.roundToInt

/**
 * The class that represents the player who is actually playing the game
 */
class Player(world: World) : Entity(world) {

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
    override var location = Rectangle(world.width / 2 - 20, world.maxPlayerRelativeHeight, 40f, 80f)
    //The speed and direction that the player is moving towards in px/ms
    var velocity = Vector2(0f, 0f)
    //How the player looks; is based on the direction that the player is moving (velocity)
    override var representation: Sprite = PositionTexture.NEUTRAL.appearance
        get() = when {
                this.velocity.y < 0 -> PositionTexture.FALLING.appearance
                this.velocity.x.roundToInt() == 0 -> PositionTexture.NEUTRAL.appearance
                this.velocity.x > 0 -> PositionTexture.RIGHT.appearance
                this.velocity.x < 0 -> PositionTexture.LEFT.appearance
                else -> PositionTexture.NEUTRAL.appearance
            }


    /**
     * Every turn, the player adjusts its location by its velocity and changes the velocity of the player based on the world gravity
     */
    override fun act(delta: Float) {
        this.location.setPosition(Vector2().also { this.location.getPosition(it) } + this.velocity)
        this.velocity.x = 0f
        this.velocity.y -= this.world.gravity * delta
    }

}