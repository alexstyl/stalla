package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.util.whenNotNull

/** Builder for constructing [Episode.Podlove.SimpleChapter] instances. */
public interface EpisodePodloveSimpleChapterBuilder : Builder<Episode.Podlove.SimpleChapter> {

    /** Set the start value. */
    public fun start(start: String): EpisodePodloveSimpleChapterBuilder

    /** Set the title value. */
    public fun title(title: String): EpisodePodloveSimpleChapterBuilder

    /** Set the href value. */
    public fun href(href: String?): EpisodePodloveSimpleChapterBuilder

    /** Set the image value. */
    public fun image(image: String?): EpisodePodloveSimpleChapterBuilder

    override fun from(model: Episode.Podlove.SimpleChapter?): EpisodePodloveSimpleChapterBuilder = whenNotNull(model) { simpleChapter ->
        start(simpleChapter.start)
        title(simpleChapter.title)
        href(simpleChapter.href)
        image(simpleChapter.image)
    }
}