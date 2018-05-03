package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.saurabhtotey.jump.Game

/**
 * The ground on where the app and the player start
 */
class Ground(game: Game) : Entity(game) {

    //Grounds look like the grounds texture
    override var representation = Sprite(Texture("images/Ground.png"))
    //THe ground is always at the bottom
    override var location = Rectangle(0f, 0f, this.game.width, this.game.maxPlayerRelativeHeight)

    /**
     * Grounds don't do anything
     */
    override fun act(delta: Float) {}

}