package org.xml.sax

trait ContentHandler {

  def setDocumentLocator(locator: Locator): Unit

  @throws[SAXException]
  def startDocument(): Unit

  @throws[SAXException]
  def endDocument(): Unit

  @throws[SAXException]
  def startPrefixMapping(prefix: String, uri: String): Unit

  @throws[SAXException]
  def endPrefixMapping(prefix: String): Unit

  @throws[SAXException]
  def startElement(uri: String, localName: String, qName: String, atts: Attributes): Unit

  @throws[SAXException]
  def endElement(uri: String, localName: String, qName: String): Unit

  @throws[SAXException]
  def characters(ch: Array[Char], start: Int, length: Int): Unit

  @throws[SAXException]
  def ignorableWhitespace(ch: Array[Char], start: Int, length: Int): Unit

  @throws[SAXException]
  def processingInstruction(target: String, data: String): Unit

  @throws[SAXException]
  def skippedEntity(name: String): Unit
}
