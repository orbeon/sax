package org.xml.sax

trait ErrorHandler {
  @throws[SAXException]
  def warning(exception: SAXParseException): Unit

  @throws[SAXException]
  def error(exception: SAXParseException): Unit

  @throws[SAXException]
  def fatalError(exception: SAXParseException): Unit
}
