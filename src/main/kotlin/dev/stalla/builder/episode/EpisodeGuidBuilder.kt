package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Guid
import dev.stalla.util.whenNotNull

/** Builder for constructing [Guid] instances. */
public interface EpisodeGuidBuilder : Builder<Guid> {

    /** Set the textContent value. */
    public fun textContent(textContent: String): EpisodeGuidBuilder

    /** Set the isPermalink value. */
    public fun isPermalink(isPermalink: Boolean?): EpisodeGuidBuilder

    override fun from(model: Guid?): EpisodeGuidBuilder = whenNotNull(model) { guid ->
        textContent(guid.guid)
        isPermalink(guid.isPermalink)
    }
}
