package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode

internal interface EpisodePodloveBuilder : Builder<Episode.Podlove> {

    /**
     * Adds a chapter builder to the list of chapters.
     *
     * @param chapterBuilder The chapter builder to add.
     */
    fun addSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveBuilder

    /**
     * Adds chapter builders to the list of chapters.
     *
     * @param chapterBuilders The chapter builders to add.
     */
    fun addSimpleChapterBuilders(chapterBuilders: List<EpisodePodloveSimpleChapterBuilder>): EpisodePodloveBuilder
}