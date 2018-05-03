package com.saurabhtotey.jump.ui

import ktx.scene2d.button
import ktx.scene2d.label
import ktx.scene2d.table

//How the main menu GUI will look
val mainMenuLayout = table {
    setFillParent(true)
    pad(5f)
    button {
        label("Hello!") {
            setFontScale(10f)
        }
        pad(100f)
    }
}
