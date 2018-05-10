package com.saurabhtotey.jump

import com.badlogic.gdx.Gdx

/**
 * A singleton that represents the user's game information
 * While all the data storage is done locally on files, in a real game, it would be handled online for security purposes
 */
object UserData {

    //The file that stores coin information
    private val coinFile = Gdx.files.local("JumpCoinTotal.txt")
    //The file that stores scores
    private val scoreDataFile = Gdx.files.local("JumpScoreData.txt")

    //A field that stores how many coins the user has; when set, ensures the file is updated
    var numCoins = if (coinFile.exists()) Integer.parseInt(coinFile.readString()) else 0
        set(value) {
            coinFile.writeString(value.toString(), false)
            field = value
        }
    //A field that stores all of the user's score data; when set, ensures the file is updated
    var scores = if (scoreDataFile.exists()) scoreDataFile.readString().lines().map { Integer.parseInt(it) } else List(0, { 0 })
        set(value) {
            scoreDataFile.writeString(value.joinToString("\n"), false)
            field = value
        }

}