package com.saurabhtotey.jump.entity

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.saurabhtotey.jump.World

/**
 * An object that represents something in the game
 */
abstract class Entity(val world: World) {

    /**
     * Where the entity is along with its dimensions
     */
    abstract var location : Rectangle

    /**
     * How the entity looks in the current frame
     */
    abstract var representation : Sprite

    /**
     * What the entity does every frame given a change in time
     */
    abstract fun act(delta: Float)

}