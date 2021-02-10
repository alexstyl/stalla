package dev.stalla.builder.validating.episode

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.model.Episode
import dev.stalla.model.episode.anEpisodePodlove
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodloveBuilderTest {

    private val expectedChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
        .start("start")
        .title("chapter title")

    @Test
    internal fun `should not build an Episode Podlover Simple Chapters when there is no chapter`() {
        val podloveBuilder = ValidatingEpisodePodloveBuilder()

        assertAll {
            assertThat(podloveBuilder).prop(EpisodePodloveBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podloveBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podlover Simple Chapters when adding a simple chapter`() {
        val podloveBuilder = ValidatingEpisodePodloveBuilder()
            .addSimpleChapterBuilder(expectedChapterBuilder)

        assertAll {
            assertThat(podloveBuilder).prop(EpisodePodloveBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podloveBuilder.build()?.simpleChapters).isNotNull()
                .containsExactly(expectedChapterBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Podlover Simple Chapters when adding multiple simple chapters at once`() {
        val anotherChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            .start("start 2")
            .title("chapter title 2")
        val podloveBuilder = ValidatingEpisodePodloveBuilder()
            .addSimpleChapterBuilders(listOf(expectedChapterBuilder, anotherChapterBuilder))

        assertAll {
            assertThat(podloveBuilder).prop(EpisodePodloveBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podloveBuilder.build()?.simpleChapters).isNotNull()
                .containsExactly(expectedChapterBuilder.build(), anotherChapterBuilder.build())
        }
    }

    @Test
    internal fun `should populate an Episode Podlove builder with all properties from an Episode Podlove model`() {
        val episodePodlove = anEpisodePodlove()
        val episodePodloveBuilder = Episode.Podlove.builder().from(episodePodlove)

        assertAll {
            assertThat(episodePodloveBuilder).prop(EpisodePodloveBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodloveBuilder.build()).isNotNull().isEqualTo(episodePodlove)
        }
    }
}