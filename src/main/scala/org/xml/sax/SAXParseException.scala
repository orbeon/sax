
package org.xml.sax

class SAXParseException(message: String, e: Exception) extends SAXException(message, e) {

  private var publicId: String = null
  private var systemId: String = null
  private var lineNumber = 0
  private var columnNumber = 0

  def getPublicId    : String = this.publicId
  def getSystemId    : String = this.systemId
  def getLineNumber  : Int    = this.lineNumber
  def getColumnNumber: Int    = this.columnNumber

  def this(message: String, locator: Locator) = {
    this(message, null: Exception)
    if (locator != null)
      init(locator.getPublicId, locator.getSystemId, locator.getLineNumber, locator.getColumnNumber)
    else
      init(null, null, -1, -1)
  }

  def this(message: String, locator: Locator, e: Exception) = {
    this(message, e)
    if (locator != null)
      init(locator.getPublicId, locator.getSystemId, locator.getLineNumber, locator.getColumnNumber)
    else
      init(null, null, -1, -1)
  }

  def this(message: String, publicId: String, systemId: String, lineNumber: Int, columnNumber: Int) = {
    this(message, null: Exception)
    init(publicId, systemId, lineNumber, columnNumber)
  }

  def this(message: String, publicId: String, systemId: String, lineNumber: Int, columnNumber: Int, e: Exception) = {
    this(message, e)
    init(publicId, systemId, lineNumber, columnNumber)
  }

  private def init(publicId: String, systemId: String, lineNumber: Int, columnNumber: Int): Unit = {
    this.publicId = publicId
    this.systemId = systemId
    this.lineNumber = lineNumber
    this.columnNumber = columnNumber
  }
}
