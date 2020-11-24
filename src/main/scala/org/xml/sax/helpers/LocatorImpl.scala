package org.xml.sax.helpers

import org.xml.sax.Locator


class LocatorImpl extends Locator {

  private var publicId: String = null
  private var systemId: String = null
  private var lineNumber = 0
  private var columnNumber = 0

  def this(locator: Locator) = {
    this()
    setPublicId(locator.getPublicId)
    setSystemId(locator.getSystemId)
    setLineNumber(locator.getLineNumber)
    setColumnNumber(locator.getColumnNumber)
  }

  def getPublicId: String = publicId
  def getSystemId: String = systemId
  def getLineNumber: Int = lineNumber
  def getColumnNumber: Int = columnNumber

  def setPublicId(publicId: String): Unit = this.publicId = publicId
  def setSystemId(systemId: String): Unit = this.systemId = systemId
  def setLineNumber(lineNumber: Int): Unit = this.lineNumber = lineNumber
  def setColumnNumber(columnNumber: Int): Unit = this.columnNumber = columnNumber
}
