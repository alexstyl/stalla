package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.googleplay.ExplicitType
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [PodcastGoogleplay] instances.
 *
 * @since 1.0.0
 */
public interface PodcastGoogleplayBuilder : Builder<PodcastGoogleplay> {

    /** Set the author value. */
    public fun author(author: String?): PodcastGoogleplayBuilder

    /** Set the owner email value. */
    public fun email(email: String?): PodcastGoogleplayBuilder

    /** Adds the [GoogleplayCategory] the list of categories. */
    public fun addCategory(category: GoogleplayCategory): PodcastGoogleplayBuilder

    /** Adds all of the [GoogleplayCategory] to the list of categories. */
    public fun addAllCategories(categories: List<GoogleplayCategory>): PodcastGoogleplayBuilder =
        apply { categories.forEach(::addCategory) }

    /** Set the description value. */
    public fun description(description: String?): PodcastGoogleplayBuilder

    /** Set the explicit flag value. */
    @Deprecated(
        message = "Use explicitType(ExplicitType?) instead. Scheduled for removal in v2.0.0.",
        replaceWith = ReplaceWith("explicitType()")
    )
    public fun explicit(explicit: Boolean?): PodcastGoogleplayBuilder = explicitType(
        when (explicit) {
            true -> ExplicitType.YES
            false -> ExplicitType.NO
            else -> null
        }
    )

    /** Set the explicit flag value. */
    public fun explicitType(explicitType: ExplicitType?): PodcastGoogleplayBuilder

    /** Set the block flag value. */
    public fun block(block: Boolean): PodcastGoogleplayBuilder

    /** Set the [HrefOnlyImageBuilder]. */
    public fun imageBuilder(imageBuilder: HrefOnlyImageBuilder?): PodcastGoogleplayBuilder

    /** Set the new URL at which this feed is located. */
    public fun newFeedUrl(newFeedUrl: String?): PodcastGoogleplayBuilder

    override fun applyFrom(prototype: PodcastGoogleplay?): PodcastGoogleplayBuilder =
        whenNotNull(prototype) { googleplay ->
            author(googleplay.author)
            email(googleplay.email)
            addAllCategories(googleplay.categories)
            description(googleplay.description)
            explicitType(googleplay.explicitType)
            block(googleplay.block)
            imageBuilder(HrefOnlyImage.builder().applyFrom(googleplay.image))
            newFeedUrl(googleplay.newFeedUrl)
        }
}
