package org.xml.sax.helpers

import java.io.IOException

import org.xml.sax._


class DefaultHandler extends EntityResolver with DTDHandler with ContentHandler with ErrorHandler {
  def resolveEntity(publicId: String, systemId: String): InputSource = null
  def notationDecl(name: String, publicId: String, systemId: String): Unit = ()
  def unparsedEntityDecl(name: String, publicId: String, systemId: String, notationName: String): Unit = ()
  def setDocumentLocator(locator: Locator): Unit = ()
  def startDocument(): Unit = ()
  def endDocument(): Unit = ()
  def startPrefixMapping(prefix: String, uri: String): Unit = ()
  def endPrefixMapping(prefix: String): Unit = ()
  def startElement(uri: String, localName: String, qName: String, attributes: Attributes): Unit = ()
  def endElement(uri: String, localName: String, qName: String): Unit = ()
  def characters(ch: Array[Char], start: Int, length: Int): Unit = ()
  def ignorableWhitespace(ch: Array[Char], start: Int, length: Int): Unit = ()
  def processingInstruction(target: String, data: String): Unit = ()
  def skippedEntity(name: String): Unit = ()
  def warning(e: SAXParseException): Unit = ()
  def error(e: SAXParseException): Unit = ()
  def fatalError(e: SAXParseException): Unit = throw e
}
