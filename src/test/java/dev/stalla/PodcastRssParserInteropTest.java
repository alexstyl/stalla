package dev.stalla;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.*;
import java.lang.reflect.Method;

import static dev.stalla.TestUtilKt.declaresException;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastRssParserInteropTest {

    private final File unavailableFile = new File("some/fantasy/file");
    private final File invalidRssFile = new File(getClass().getClassLoader().getResource("xml/no-rss.xml").getFile());

    @Test
    @DisplayName("should fail to parse an URI that is null")
    public void failOnNullUri() {
        assertAll("fail on invalid argument",
            () -> assertThrows(NullPointerException.class, () -> {
                final String uri = null;
                PodcastRssParser.parse(uri);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an URI to a file that does not exist")
    public void failOnUriNotExists() {
        assertAll("fail on invalid argument",
            () -> assertThrows(IOException.class, () -> {
                PodcastRssParser.parse("some/fantasy/uri");
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an URI to an invalid XML")
    public void failOnUriInvalidXml() {
        assertAll("fail on invalid argument",
            () -> assertThrows(SAXException.class, () -> {
                final String uri = invalidRssFile.getAbsolutePath();
                PodcastRssParser.parse(uri);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an InputStream that is null")
    public void failOnNullInputStream() {
        assertAll("fail on invalid argument",
            () -> assertThrows(NullPointerException.class, () -> {
                final InputStream inputStream = null;
                PodcastRssParser.parse(inputStream);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an InputStream from a file that does not exist")
    public void failOnInputStreamNotExists() {
        assertAll("fail on invalid argument",
            () -> assertThrows(IOException.class, () -> {
                final InputStream inputStream = new FileInputStream(unavailableFile);
                PodcastRssParser.parse(inputStream);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an InputStream from an invalid XML")
    public void failOnInputStreamInvalidXml() {
        assertAll("fail on invalid argument",
            () -> assertThrows(SAXException.class, () -> {
                final InputStream inputStream = new FileInputStream(invalidRssFile);
                PodcastRssParser.parse(inputStream);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse a File that is null")
    public void failOnNullFile() {
        assertAll("fail on invalid argument",
            () -> assertThrows(NullPointerException.class, () -> {
                final File file = null;
                PodcastRssParser.parse(file);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse a File that does not exist")
    public void failOnFileNotExists() {
        assertAll("fail on invalid argument",
            () -> assertThrows(IOException.class, () -> {
                PodcastRssParser.parse(unavailableFile);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse a File from an invalid XML")
    public void failOnFileInvalidXml() {
        assertAll("fail on invalid argument",
            () -> assertThrows(SAXException.class, () -> {
                PodcastRssParser.parse(invalidRssFile);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an InputSource that is null")
    public void failOnNullInputSource() {
        assertAll("fail on invalid argument",
            () -> assertThrows(NullPointerException.class, () -> {
                final InputSource inputSource = null;
                PodcastRssParser.parse(inputSource);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an InputSource of a file that does not exist")
    public void failOnInputSourceNotExists() {
        assertAll("fail on invalid argument",
            () -> assertThrows(IOException.class, () -> {
                final InputSource inputSource = toInputSource(unavailableFile);
                PodcastRssParser.parse(inputSource);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse an InputSource of an invalid XML")
    public void failOnInputSourceInvalidXml() {
        assertAll("fail on invalid argument",
            () -> assertThrows(SAXException.class, () -> {
                final InputSource inputSource = toInputSource(invalidRssFile);
                PodcastRssParser.parse(inputSource);
            })
        );
    }

    @Test
    @DisplayName("should fail to parse a Document that is null")
    public void failOnNullDocument() {
        assertAll("fail on invalid argument",
            () -> assertThrows(NullPointerException.class, () -> {
                final Document document = null;
                PodcastRssParser.parse(document);
            })
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(String) method")
    void shouldDeclareExpectedExceptionsInParseUri() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", String.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputStream) method")
    void shouldDeclareExpectedExceptionsInParseInputStream() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", InputStream.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputStream) method")
    void shouldDeclareExpectedExceptionsInParseInputStreamString() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", InputStream.class, String.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputStream) method")
    void shouldDeclareExpectedExceptionsInParseFile() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", File.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(InputSource) method")
    void shouldDeclareExpectedExceptionsInParseInputSource() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", InputSource.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    @Test
    @DisplayName("should declare expected exceptions for the parse(Document) method")
    void shouldDeclareExpectedExceptionsInParseDocument() throws NoSuchMethodException {
        final Method method = PodcastRssParser.class.getMethod("parse", Document.class);
        assertAll("Should declare expected exceptions",
            () -> assertNotNull(method),
            () -> assertTrue(declaresException(method, IOException.class)),
            () -> assertTrue(declaresException(method, SAXException.class))
        );
    }

    private InputSource toInputSource(File file) throws FileNotFoundException {
        final InputStream inputStream = new FileInputStream(file);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new InputSource(inputStreamReader);
    }

}
