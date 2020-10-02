package org.xml.sax

import java.io.IOException

trait XMLReader {
  @throws[SAXNotRecognizedException]
  @throws[SAXNotSupportedException]
  def getFeature(name: String): Boolean

  @throws[SAXNotRecognizedException]
  @throws[SAXNotSupportedException]
  def setFeature(name: String, value: Boolean): Unit

  @throws[SAXNotRecognizedException]
  @throws[SAXNotSupportedException]
  def getProperty(name: String): Any

  @throws[SAXNotRecognizedException]
  @throws[SAXNotSupportedException]
  def setProperty(name: String, value: Any): Unit

  def setEntityResolver(resolver: EntityResolver): Unit
  def getEntityResolver: EntityResolver
  def setDTDHandler(handler: DTDHandler): Unit
  def getDTDHandler: DTDHandler
  def setContentHandler(handler: ContentHandler): Unit
  def getContentHandler: ContentHandler
  def setErrorHandler(handler: ErrorHandler): Unit
  def getErrorHandler: ErrorHandler

  @throws[IOException]
  @throws[SAXException]
  def parse(input: InputSource): Unit

  @throws[IOException]
  @throws[SAXException]
  def parse(systemId: String): Unit
}