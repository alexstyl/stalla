package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.ITunes] instances. */
public interface EpisodeITunesBuilder : Builder<Episode.ITunes> {

    /** Set the title value. */
    public fun title(title: String?): EpisodeITunesBuilder

    /** Set the duration value. */
    public fun duration(duration: String?): EpisodeITunesBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): EpisodeITunesBuilder

    /** Set the explicit flag value. */
    public fun explicit(explicit: Boolean?): EpisodeITunesBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): EpisodeITunesBuilder

    /** Set the season value. */
    public fun season(season: Int?): EpisodeITunesBuilder

    /** Set the episode value. */
    public fun episode(episode: Int?): EpisodeITunesBuilder

    /** Set the episodeType value. */
    public fun episodeType(episodeType: String?): EpisodeITunesBuilder

    /** Set the author value. */
    public fun author(author: String?): EpisodeITunesBuilder

    /** Set the subtitle value. */
    public fun subtitle(subtitle: String?): EpisodeITunesBuilder

    /** Set the summary value. */
    public fun summary(summary: String?): EpisodeITunesBuilder

    override fun from(model: Episode.ITunes?): EpisodeITunesBuilder = whenNotNull(model) { itunes ->
        title(itunes.title)
        duration(itunes.duration)
        imageBuilder(HrefOnlyImage.builder().from(itunes.image))
        explicit(itunes.explicit)
        block(itunes.block)
        season(itunes.season)
        episode(itunes.episode)
        episodeType(itunes.episodeType?.type)
        author(itunes.author)
        subtitle(itunes.subtitle)
        summary(itunes.summary)
    }
}
