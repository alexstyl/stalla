package io.hemin.wien.parser

import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node
import java.time.ZonedDateTime

/** Interface for XML namespace parser classes. */
interface NamespaceParser {

    /** The URI of the namespace processed by this parser. */
    val namespaceURI: String?

    /**
     * Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [PodcastBuilder].
     *
     * @param podcast The builder  where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun parse(podcast: PodcastBuilder, node: Node)

    /**
     * Extracts data from the XML namespace defined by [namespaceURI]
     * and applies the values to properties of the [EpisodeBuilder].
     *
     * @param episode The builder  where all parsed data is added to.
     * @param node The DOM node from which all data is extracted from.
     */
    fun parse(episode: EpisodeBuilder, node: Node)

    /**
     * Extracts the text content of a DOM node. Trims whitespace at the beginning and the end.
     *
     * @return The content of the DOM node in string representation.
     */
    fun toText(node: Node): String? = node.textContent.trim()

    /**
     * Extracts the text content of a DOM node, and transforms it to a Date object.
     *
     * @return The DOM nodes content as a date object, or null if date parsing failed.
     */
    fun toDate(node: Node): ZonedDateTime? {
        return try {
            ZonedDateTime.parse(toText(node))
        }
        catch (e: Exception) {
            null
        }
    }
}