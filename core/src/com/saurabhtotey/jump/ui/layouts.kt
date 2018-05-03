package com.saurabhtotey.jump.ui

import com.badlogic.gdx.utils.Align
import ktx.actors.onClick
import ktx.scene2d.*

//How the main menu GUI will look
val mainMenuLayout = table {
    setFillParent(true)
    pad(5f)
    label("Tap anywhere to begin...") {
        setFontScale(4f)
        it.expand()
    }
    row().align(Align.bottomLeft)
    button {
        image("OptionsButton")
        onClick {

        }
        it.width(75f)
        it.height(75f)
    }
}

//How the app will look during play
val mainGameLayout = table {
    setFillParent(true)
    pad(15f)
    button {
        image("PauseButton")
        onClick {

        }
        it.width(75f)
        it.height(75f)
    }
    align(Align.topLeft)
}

//How the app will look when paused
val pausedGameLayout = table {
    setFillParent(true)
    pad(5f)
}

//How the app will look when finished
val finishedGameLayout = table {
    setFillParent(true)
    pad(5f)
}
