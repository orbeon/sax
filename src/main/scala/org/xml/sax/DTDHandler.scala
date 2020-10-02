package org.xml.sax

trait DTDHandler {

  @throws[SAXException]
  def notationDecl(name: String, publicId: String, systemId: String): Unit

  @throws[SAXException]
  def unparsedEntityDecl(name: String, publicId: String, systemId: String, notationName: String): Unit
}
