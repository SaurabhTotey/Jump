package com.saurabhtotey.jump.ui

import ktx.scene2d.label
import ktx.scene2d.table

//How the main menu GUI will look
val mainMenuLayout = table {
    setFillParent(true)
    pad(5f)
    label("Tap anywhere to begin...") {
        setFontScale(4.5f)
    }
}

//How the app will look during play
val mainGameLayout = table {  }

//How the app will look when paused
val pausedGameLayout = table {  }

//How the app will look when finished
val finishedGameLayout = table {  }
