package io.hemin.wien.builder

public interface LinkBuilderProvider {

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    public fun createLinkBuilder(): LinkBuilder
}
