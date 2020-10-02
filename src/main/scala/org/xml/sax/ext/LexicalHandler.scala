package org.xml.sax.ext

import org.xml.sax.SAXException

trait LexicalHandler {
  @throws[SAXException]
  def startDTD(name: String, publicId: String, systemId: String): Unit

  @throws[SAXException]
  def endDTD(): Unit

  @throws[SAXException]
  def startEntity(name: String): Unit

  @throws[SAXException]
  def endEntity(name: String): Unit

  @throws[SAXException]
  def startCDATA(): Unit

  @throws[SAXException]
  def endCDATA(): Unit

  @throws[SAXException]
  def comment(ch: Array[Char], start: Int, length: Int): Unit
}
