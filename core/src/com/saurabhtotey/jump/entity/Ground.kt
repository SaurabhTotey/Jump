package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.saurabhtotey.jump.World

/**
 * The ground on where the game and the player start
 */
class Ground(world: World) : Entity(world) {

    //Grounds look like the grounds texture
    override var representation = Texture("Ground.png")
    //THe ground is always at the bottom
    override var location = Rectangle(0f, 0f, this.world.width.toFloat(), this.world.maxPlayerRelativeHeight.toFloat())

    /**
     * Grounds don't do anything
     */
    override fun act(delta: Float) {}

}