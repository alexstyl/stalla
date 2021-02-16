package dev.stalla.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.model.episode.anEpisodePodcastindexSoundbite
import dev.stalla.model.podcastindex.Soundbite
import org.junit.jupiter.api.Test
import java.time.Duration

internal class ValidatingEpisodePodcastindexSoundbiteBuilderTest {

    @Test
    internal fun `should not build an Episode Podcast Soundbite with when all the fields are missing`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Soundbite with when the startTime field is missing`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .duration(Duration.ofSeconds(15))

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Soundbite with when the startTime field is negative`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(Duration.ZERO.minusSeconds(1))
            .duration(Duration.ofSeconds(15))

        assertAll {
            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Soundbite with when the duration field is missing`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(Duration.ofSeconds(1))

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Soundbite with when the duration field is zero`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(Duration.ofSeconds(1))
            .duration(Duration.ZERO)

        assertAll {
            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Soundbite with when the duration field is negative`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(Duration.ofSeconds(1))
            .duration(Duration.ZERO.minusSeconds(1))

        assertAll {
            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast Soundbite with with all the added entries to its fields`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(Duration.ofSeconds(1))
            .duration(Duration.ofSeconds(15))
            .title("soundbite")

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(soundbiteBuilder.build()).isNotNull().all {
                prop(Soundbite::startTime).isEqualTo(Duration.ofSeconds(1))
                prop(Soundbite::duration).isEqualTo(Duration.ofSeconds(15))
                prop(Soundbite::title).isEqualTo("soundbite")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Soundbite builder with all properties from an Episode Podcastindex Soundbite model`() {
        val episodePodcastSoundbite = anEpisodePodcastindexSoundbite()
        val episodePodcastSoundbiteBuilder = Soundbite.builder().applyFrom(episodePodcastSoundbite)

        assertAll {
            assertThat(episodePodcastSoundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastSoundbiteBuilder.build()).isNotNull().isEqualTo(episodePodcastSoundbite)
        }
    }
}