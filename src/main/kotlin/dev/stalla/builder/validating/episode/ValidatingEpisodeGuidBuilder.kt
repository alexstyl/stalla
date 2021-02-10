package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.model.Episode

internal class ValidatingEpisodeGuidBuilder : EpisodeGuidBuilder {

    private lateinit var text: String

    private var isPermalink: Boolean? = null

    override fun textContent(textContent: String): EpisodeGuidBuilder = apply { this.text = textContent }

    override fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder = apply { this.isPermalink = isPermalink }

    override val hasEnoughDataToBuild: Boolean
        get() = ::text.isInitialized

    override fun build(): Episode.Guid? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Guid(text, isPermalink)
    }
}