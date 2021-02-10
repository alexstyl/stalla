package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Enclosure
import dev.stalla.util.whenNotNull

/** Builder for constructing [Enclosure] instances. */
public interface EpisodeEnclosureBuilder : Builder<Enclosure> {

    /** Set the url value. */
    public fun url(url: String): EpisodeEnclosureBuilder

    /** Set the length value. */
    public fun length(length: Long): EpisodeEnclosureBuilder

    /** Set the type value. */
    public fun type(type: String): EpisodeEnclosureBuilder

    override fun from(model: Enclosure?): EpisodeEnclosureBuilder = whenNotNull(model) { enclosure ->
        url(enclosure.url)
        length(enclosure.length)
        type(enclosure.type)
    }
}
