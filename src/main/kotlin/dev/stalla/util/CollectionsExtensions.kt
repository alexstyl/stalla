package dev.stalla.util

import dev.stalla.builder.ITunesStyleCategoryBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.model.RssCategory
import dev.stalla.model.itunes.ITunesStyleCategory
import dev.stalla.model.podlove.SimpleChapter

/** Transforms this list into a list of [RssCategoryBuilder] */
@InternalApi
@JvmName("asRssCategoryBuilders")
internal fun List<RssCategory>.asBuilders(): List<RssCategoryBuilder> = map(RssCategory.builder()::from)

/** Transforms this list into a list of [ITunesStyleCategoryBuilder] */
@InternalApi
@JvmName("asItunesCategoryBuilders")
internal fun List<ITunesStyleCategory>.asBuilders(): List<ITunesStyleCategoryBuilder> = map(ITunesStyleCategory.builder()::from)

/** Transforms this list into a list of [EpisodeEnclosureBuilder] */
@InternalApi
@JvmName("asEnclosureBuilders")
internal fun List<Episode.Enclosure>.asBuilders(): List<EpisodeEnclosureBuilder> = map(Episode.Enclosure.builder()::from)

/** Transforms this list into a list of [EpisodePodcastSoundbiteBuilder] */
@InternalApi
@JvmName("asSoundbiteBuilders")
internal fun List<Episode.Podcast.Soundbite>.asBuilders(): List<EpisodePodcastSoundbiteBuilder> = map(Episode.Podcast.Soundbite.builder()::from)

/** Transforms this list into a list of [EpisodePodcastTranscriptBuilder] */
@InternalApi
@JvmName("asTranscriptBuilders")
internal fun List<Episode.Podcast.Transcript>.asBuilders(): List<EpisodePodcastTranscriptBuilder> =
    map(Episode.Podcast.Transcript.builder()::from)

/** Transforms this list into a list of [PodcastPodcastFundingBuilder] */
@InternalApi
@JvmName("asFundingBuilders")
internal fun List<Podcast.Podcast.Funding>.asBuilders(): List<PodcastPodcastFundingBuilder> = map(Podcast.Podcast.Funding.builder()::from)

/** Transforms this list into a list of [EpisodePodloveSimpleChapterBuilder] */
@InternalApi
@JvmName("asSimpleChapterBuilders")
internal fun List<SimpleChapter>.asBuilders(): List<EpisodePodloveSimpleChapterBuilder> =
    map(SimpleChapter.builder()::from)

/** Transforms this list into a list of [EpisodeBuilder] */
@InternalApi
@JvmName("asEpisodeBuilders")
internal fun List<Episode>.asBuilders(): List<EpisodeBuilder> = map(Episode.builder()::from)
