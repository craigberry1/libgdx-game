package dev.cberry.gdxgame.core.screen

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import dev.cberry.gdxgame.MyGame
import dev.cberry.gdxgame.constants.MAX_TILES_HEIGHT
import dev.cberry.gdxgame.constants.MAX_TILES_WIDTH
import dev.cberry.gdxgame.constants.TILE_HEIGHT
import dev.cberry.gdxgame.constants.TILE_WIDTH

abstract class BaseScreen(private val game: MyGame?) : Screen {

    val disposables: MutableList<Disposable> = mutableListOf()

    protected val stage: Stage = Stage(ScreenViewport())

    override fun hide() {}

    override fun show() {}

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    final override fun render(delta: Float) {
        // universal exit
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }

        handleInput()
        handleRender(delta)

        if (Gdx.app.logLevel == Application.LOG_DEBUG) {
            game?.batch?.let { batch ->
                batch.begin()
                (0..MAX_TILES_WIDTH).forEach { x ->
                    (0..MAX_TILES_HEIGHT).forEach { y ->
                        game.batch.draw(
                            squareTexture,
                            x * TILE_WIDTH.toFloat(),
                            y * TILE_HEIGHT.toFloat(),
                            TILE_WIDTH.toFloat(),
                            TILE_HEIGHT.toFloat()
                        )
                    }
                }
                batch.end()
            }
        }
    }

    override fun dispose() {
        disposables.forEach { it.dispose() }
    }

    abstract fun handleInput()

    abstract fun handleRender(delta: Float)

    companion object {
        val squareTexture: Texture = Texture("images/square.png")
    }
}
