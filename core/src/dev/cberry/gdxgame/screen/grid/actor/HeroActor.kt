package dev.cberry.gdxgame.screen.grid.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import dev.cberry.gdxgame.screen.grid.input.HeroInputListener

class HeroActor : Image(texture) {

    init {
        setBounds(x, y, width, height)
        setScale(0.5f)
        addListener(HeroInputListener(this))
    }

    companion object {
        val texture = Texture(
            "images/rpg/sprite/character/hero-0.png"
        )
    }
}