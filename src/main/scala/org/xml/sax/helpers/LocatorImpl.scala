package org.xml.sax.helpers

import org.xml.sax.Locator


class LocatorImpl(locator: Locator) extends Locator {

  private var publicId     : String = if (locator ne null) locator.getPublicId     else null
  private var systemId     : String = if (locator ne null) locator.getSystemId     else null
  private var lineNumber   : Int    = if (locator ne null) locator.getLineNumber   else 0
  private var columnNumber : Int    = if (locator ne null) locator.getColumnNumber else 0

  def this() =
    this(null)

  def getPublicId    : String = publicId
  def getSystemId    : String = systemId
  def getLineNumber  : Int    = lineNumber
  def getColumnNumber: Int    = columnNumber

  def setPublicId    (publicId: String) : Unit = this.publicId     = publicId
  def setSystemId    (systemId: String) : Unit = this.systemId     = systemId
  def setLineNumber  (lineNumber: Int)  : Unit = this.lineNumber   = lineNumber
  def setColumnNumber(columnNumber: Int): Unit = this.columnNumber = columnNumber
}
