package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingITunesStyleCategoryBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastITunesBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedITunesCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
        .category("itunes category 1")
        .subcategory("itunes subcategory")

    private val otherExpectedITunesCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
        .category("itunes category 2")

    @Test
    internal fun `should not build a Podcast ITunes when all fields are missing`() {
        val podcastITunesBuilder = ValidatingPodcastITunesBuilder()

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastITunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all the mandatory fields`() {
        val podcastITunesBuilder = ValidatingPodcastITunesBuilder()
            .imageBuilder(expectedImageBuilder)
            .explicit(false)
            .addCategoryBuilder(expectedITunesCategoryBuilder)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().all {
                prop(Podcast.ITunes::explicit).isNotNull().isFalse()
                prop(Podcast.ITunes::subtitle).isNull()
                prop(Podcast.ITunes::summary).isNull()
                prop(Podcast.ITunes::keywords).isNull()
                prop(Podcast.ITunes::author).isNull()
                prop(Podcast.ITunes::categories).containsExactly(expectedITunesCategoryBuilder.build())
                prop(Podcast.ITunes::block).isFalse()
                prop(Podcast.ITunes::complete).isFalse()
                prop(Podcast.ITunes::type).isNull()
                prop(Podcast.ITunes::owner).isNull()
                prop(Podcast.ITunes::title).isNull()
                prop(Podcast.ITunes::newFeedUrl).isNull()
                prop(Podcast.ITunes::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all fields`() {
        val podcastITunesBuilder = ValidatingPodcastITunesBuilder()
            .explicit(true)
            .subtitle("subtitle")
            .summary("summary")
            .keywords("keywords")
            .author("author")
            .addCategoryBuilder(expectedITunesCategoryBuilder)
            .addCategoryBuilder(otherExpectedITunesCategoryBuilder)
            .block(false)
            .complete(false)
            .type(Podcast.ITunes.ShowType.SERIAL.type)
            .ownerBuilder(expectedPersonBuilder)
            .title("title")
            .newFeedUrl("newFeedUrl")
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().all {
                prop(Podcast.ITunes::explicit).isNotNull().isTrue()
                prop(Podcast.ITunes::subtitle).isEqualTo("subtitle")
                prop(Podcast.ITunes::summary).isEqualTo("summary")
                prop(Podcast.ITunes::keywords).isEqualTo("keywords")
                prop(Podcast.ITunes::author).isEqualTo("author")
                prop(Podcast.ITunes::categories).containsExactly(expectedITunesCategoryBuilder.build(), otherExpectedITunesCategoryBuilder.build())
                prop(Podcast.ITunes::block).isNotNull().isFalse()
                prop(Podcast.ITunes::complete).isNotNull().isFalse()
                prop(Podcast.ITunes::type).isEqualTo(Podcast.ITunes.ShowType.SERIAL)
                prop(Podcast.ITunes::owner).isEqualTo(expectedPersonBuilder.build())
                prop(Podcast.ITunes::title).isEqualTo("title")
                prop(Podcast.ITunes::newFeedUrl).isEqualTo("newFeedUrl")
                prop(Podcast.ITunes::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }
}
