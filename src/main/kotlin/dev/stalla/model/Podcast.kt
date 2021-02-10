package dev.stalla.model

import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.builder.podcast.PodcastGooglePlayBuilder
import dev.stalla.builder.podcast.PodcastITunesBuilder
import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastLockedBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastFeedpressBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastFyydBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastGooglePlayBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastITunesBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastFundingBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastPodcastLockedBuilder
import java.time.temporal.TemporalAccessor

/**
 * Model class for all the properties extracted by parser implementations from RSS `<channel>` elements.
 *
 * @property title The RSS `<title>` field textContent.
 * @property link The RSS `<link>` field textContent.
 * @property description The RSS `<description>` field textContent.
 * @property pubDate The RSS `<pubDate>` field textContent.
 * @property lastBuildDate The RSS `<lastBuildDate>` field textContent.
 * @property language The RSS `<language>` field textContent.
 * @property generator The RSS `<generator>` field textContent.
 * @property copyright The RSS `<copyright>` field textContent.
 * @property docs The RSS `<docs>` field textContent.
 * @property managingEditor The RSS `<managingEditor>` field textContent.
 * @property webMaster The RSS `<webMaster>` field textContent.
 * @property ttl The RSS `<ttl>` field textContent.
 * @property image The RSS `<image>` element wrapped in an [RssImage] instance.
 * @property episodes List of [Episode] instances extracted from the `<item>` entries of the RSS feed.
 * @property iTunes The data from the iTunes namespace, or null if no data from this namespace was found.
 * @property atom The data from the Atom namespace, or null if no data from this namespace was found.
 * @property fyyd The data from the Fyyd namespace, or null if no data from this namespace was found.
 * @property feedpress The data from the Feedpress namespace, or null if no data from this namespace was found.
 * @property googlePlay The data from the Google Play namespace, or null if no data from this namespace was found.
 * @property categories The RSS feed categories, if any.
 * @property podcast The data from the Podcast namespace, or null if no data from this namespace was found.
 */
public data class Podcast(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: TemporalAccessor? = null,
    val lastBuildDate: TemporalAccessor? = null,
    val language: String,
    val generator: String? = null,
    val copyright: String? = null,
    val docs: String? = null,
    val managingEditor: String? = null,
    val webMaster: String? = null,
    val ttl: Int? = null,
    val image: RssImage? = null,
    val episodes: List<Episode>,
    val iTunes: ITunes? = null,
    val atom: Atom? = null,
    val fyyd: Fyyd? = null,
    val feedpress: Feedpress? = null,
    val googlePlay: GooglePlay? = null,
    val categories: List<RssCategory> = emptyList(),
    val podcast: Podcast? = null
) {

    public companion object Factory : BuilderFactory<dev.stalla.model.Podcast, PodcastBuilder> {

        /** Returns a builder implementation for building [dev.stalla.model.Podcast] model instances. */
        @JvmStatic
        override fun builder(): PodcastBuilder = ValidatingPodcastBuilder()
    }

    /**
     * Model class for data from the iTunes namespace valid within an RSS `<channel>`.
     *
     * @property subtitle The `<itunes:subtitle>` field text content.
     * @property summary The `<itunes:summary>` field text content.
     * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
     * @property keywords The `<itunes:keywords>` field text content.
     * @property author The `<itunes:author>` field text content.
     * @property categories The list of `<itunes:category>` element's field text contents.
     * @property explicit The logical value of the `<itunes:explicit>` field's text content.
     * @property block The logical value of the `<itunes:block>` field's text content.
     * @property complete The logical value of the `<itunes:complete>` field's text content.
     * @property type The `<itunes:type>` field text content.
     * @property owner The `<itunes:owner>` elements data as a [Person].
     * @property owner The `<itunes:title>` field text content.
     * @property owner The `<itunes:new-feed-url>` field text content.
     */
    public data class ITunes(
        override val subtitle: String? = null,
        override val summary: String? = null,
        override val image: HrefOnlyImage?,
        val keywords: String? = null,
        override val author: String? = null,
        val categories: List<ITunesStyleCategory>,
        val explicit: Boolean,
        override val block: Boolean,
        val complete: Boolean,
        val type: ShowType? = null,
        val owner: Person? = null,
        override val title: String? = null,
        val newFeedUrl: String? = null
    ) : ITunesBase {

        public companion object Factory : BuilderFactory<ITunes, PodcastITunesBuilder> {

            /** Returns a builder implementation for building [ITunes] model instances. */
            @JvmStatic
            override fun builder(): PodcastITunesBuilder = ValidatingPodcastITunesBuilder()
        }

        /**
         * Enum model for the defined values encountered within the
         * `<itunes:type>` element within a `<channel>` element.
         *
         * @property type The string representation of the Enum instance.
         */
        public enum class ShowType(public val type: String) {

            /** Type describing an episodic show. */
            EPISODIC("episodic"),

            /** Type describing a serial show. */
            SERIAL("serial");

            public companion object Factory {

                /**
                 * Factory method for the instance of the Enum matching the [type] parameter.
                 *
                 * @param type The string representation of the Enum instance.
                 * @return The Enum instance matching [type], or null if not matching instance exists.
                 */
                public fun from(type: String?): ShowType? = type?.let {
                    values().find { t -> t.type == it.toLowerCase() }
                }
            }
        }
    }

    /**
     * Model class for data from the Google Play namespace valid within an RSS `<channel>`.
     *
     * @property author The `<googleplay:author>` field text content.
     * @property owner The `<googleplay:email>` field text content.
     * @property categories The list of `<googleplay:category>` element's field text contents.
     * @property description The `<googleplay:description>` field text content.
     * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
     * @property block The logical value of the `<googleplay:block>` field's text content.
     * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
     */
    public data class GooglePlay(
        val author: String? = null,
        val owner: String? = null,
        val categories: List<ITunesStyleCategory>,
        override val description: String? = null,
        override val explicit: Boolean? = null,
        override val block: Boolean,
        override val image: HrefOnlyImage? = null
    ) : GooglePlayBase {

        public companion object Factory : BuilderFactory<GooglePlay, PodcastGooglePlayBuilder> {

            /** Returns a builder implementation for building [GooglePlay] model instances. */
            @JvmStatic
            override fun builder(): PodcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
        }
    }

    /**
     * Model class for data from elements of the Fyyd namespace that are valid within `<channel>` elements.
     *
     * @property verify The Podcast's verification token.
     */
    public data class Fyyd(
        val verify: String
    ) {

        public companion object Factory : BuilderFactory<Fyyd, PodcastFyydBuilder> {

            /** Returns a builder implementation for building [Fyyd] model instances. */
            @JvmStatic
            override fun builder(): PodcastFyydBuilder = ValidatingPodcastFyydBuilder()
        }
    }

    /**
     * Model class for data from elements of the Feedpress namespace that are valid within `<channel>` elements.
     *
     * @property newsletterId The ID of the FeedPress newsletter.
     * @property locale The feed template language.
     * @property podcastId The iTunes Podcast ID.
     * @property cssFile The feed's custom CSS file.
     * @property link An alternative link to podcast or RSS clients.
     */
    public data class Feedpress(
        val newsletterId: String? = null,
        val locale: String? = null,
        val podcastId: String? = null,
        val cssFile: String? = null,
        val link: String? = null
    ) {

        public companion object Factory : BuilderFactory<Feedpress, PodcastFeedpressBuilder> {

            /** Returns a builder implementation for building [Feedpress] model instances. */
            @JvmStatic
            override fun builder(): PodcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
        }
    }

    /**
     * Model class for data from elements of the Podcast 1.0 namespace that are valid within `<channel>` elements.
     *
     * @property locked The lock status of the podcast.
     * @property funding The funding information for the podcast.
     */
    public data class Podcast(
        val locked: Locked? = null,
        val funding: List<Funding> = emptyList()
    ) {

        public companion object Factory : BuilderFactory<Podcast, PodcastPodcastBuilder> {

            /** Returns a builder implementation for building [Podcast] model instances. */
            @JvmStatic
            override fun builder(): PodcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
        }

        /**
         * The lock status of the podcast. Tells other podcast platforms whether they are allowed to
         * import this feed into their systems.
         *
         * @param owner An email address that can be used to verify ownership when moving hosting platforms.
         * @param locked When `true`, the podcast cannot be transferred to a new hosting platform.
         */
        public data class Locked(
            val owner: String,
            val locked: Boolean
        ) {

            public companion object Factory : BuilderFactory<Locked, PodcastPodcastLockedBuilder> {

                /** Returns a builder implementation for building [Locked] model instances. */
                @JvmStatic
                override fun builder(): PodcastPodcastLockedBuilder = ValidatingPodcastPodcastLockedBuilder()
            }
        }

        /**
         * The funding information for the podcast.
         *
         * @param url The URL where listeners can find funding information for the podcast.
         * @param message The recommended CTA text to show users for the funding link.
         */
        public data class Funding(
            val url: String,
            val message: String
        ) {

            public companion object Factory : BuilderFactory<Funding, PodcastPodcastFundingBuilder> {

                /** Returns a builder implementation for building [Funding] model instances. */
                @JvmStatic
                override fun builder(): PodcastPodcastFundingBuilder = ValidatingPodcastPodcastFundingBuilder()
            }
        }
    }
}