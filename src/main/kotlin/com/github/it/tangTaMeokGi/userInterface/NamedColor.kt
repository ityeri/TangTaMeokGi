package com.github.it.tangTaMeokGi.userInterface

import java.awt.Color

enum class NamedColor(name: String, color: Color) {
    BLACK("black", Color.BLACK),
    WHITE("white", Color.WHITE),
    RED("red", Color.RED),
    GREEN("green", Color.GREEN),
    BLUE("blue", Color.BLUE),
    YELLOW("yellow", Color.YELLOW),
    ORANGE("orange", Color.ORANGE),
    PINK("pink", Color.PINK),
    CYAN("cyan", Color.CYAN),
    MAGENTA("magenta", Color.MAGENTA),
    GRAY("gray", Color.GRAY),
    LIGHT_GRAY("lightgray", Color.LIGHT_GRAY),
    DARK_GRAY("darkgray", Color.DARK_GRAY)
    ;

    companion object {
        fun getColorFromName(name: String) : NamedColor? {
            for (namedColor in entries) {
                if (namedColor.name == name) {
                    return namedColor
                }
            }

            return null
        }

        fun getColorNames() : List<String> {
            return entries.map { namedColor -> namedColor.name }
        }
    }
}