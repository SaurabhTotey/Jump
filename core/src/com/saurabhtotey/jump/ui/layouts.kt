package com.saurabhtotey.jump.ui

import com.badlogic.gdx.utils.Align
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
        it.width(50f)
        it.height(50f)
    }
}

//How the app will look during play
val mainGameLayout = table {
    setFillParent(true)
    pad(15f)
    button {
        image("PauseButton")
        it.width(50f)
        it.height(50f)
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
