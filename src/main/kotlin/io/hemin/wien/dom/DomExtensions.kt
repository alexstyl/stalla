package io.hemin.wien.dom

import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.FeedNamespace.Companion.matches
import io.hemin.wien.util.InternalApi
import io.hemin.wien.util.trimmedOrNullIfBlank
import org.w3c.dom.Attr
import org.w3c.dom.Element
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList

/** Finds the first element matching the given (local)[name], [namespace] and [filter], if any. */
@InternalApi
internal fun Node.findElementByName(
    name: String,
    namespace: FeedNamespace? = null,
    filter: (Node) -> Boolean = { true }
) = childNodes.asListOfNodes()
    .firstOrNull { it.getTagName() == name && namespace.matches(it.namespaceURI) && filter(it) }
    ?.asElement()

private fun Node.getTagName(): String? = when (this) {
    is Element -> localName ?: tagName ?: nodeName
    else -> localName ?: nodeName
}

/**
 * Checks whether the node is a direct child of a tag with the given name.
 */
@InternalApi
internal fun Node.isDirectChildOf(tagName: String) =
    parentNode.nodeName == tagName && parentNode.namespaceURI == null

/**
 * Extract the [`textContent`][Node.getTextContent] of a DOM node attribute identified by name.
 *
 * @param attributeName The name of the node's attribute.
 * @param namespace The namespace to use, if any.
 * @return The textContent of the node's attribute.
 */
@InternalApi
internal fun Node.getAttributeValueByName(attributeName: String, namespace: FeedNamespace? = null): String? =
    getAttributeByName(attributeName, namespace)?.value?.trimmedOrNullIfBlank()

/**
 * Extract the [`textContent`][Node.getTextContent] of a DOM node attribute identified by name.
 *
 * @param attributeName The name of the node's attribute.
 * @param namespace The namespace to use, if any.
 * @return The textContent of the node's attribute.
 */
@InternalApi
internal fun Node.getAttributeByName(attributeName: String, namespace: FeedNamespace? = null): Attr? =
    attributes?.getNamedItemNS(namespace?.uri, attributeName) as? Attr

/** Returns true if the [NodeList] is empty. */
@InternalApi
internal fun NodeList.isEmpty() = length == 0

/** Returns true if the [NodeList] contains at least one node. */
@InternalApi
internal fun NodeList.isNotEmpty() = length > 0

/** Converts this [NodeList] to a [List] of [Node]s. */
@InternalApi
internal fun NodeList.asListOfNodes(): List<Node> {
    if (length == 0) return emptyList()
    return NodeListWrapper(this)
}

/** Converts this [NamedNodeMap] to a [List] of [Attr]s. */
@InternalApi
internal fun NamedNodeMap.asListOfAttrs(): List<Attr> {
    if (length == 0) return emptyList()
    return (0 until length).map { index -> item(index) }
        .filterIsInstance(Attr::class.java)
}

/** Casts the [Node] to an [Element]. */
@InternalApi
internal fun Node.asElement() = this as Element
