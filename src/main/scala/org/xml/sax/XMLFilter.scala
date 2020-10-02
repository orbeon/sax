package org.xml.sax

trait XMLFilter extends XMLReader {
  def setParent(parent: XMLReader): Unit
  def getParent: XMLReader
}
