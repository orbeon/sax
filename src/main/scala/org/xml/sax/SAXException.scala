package org.xml.sax

class SAXException(message: String, e: Exception) extends Exception(message, e) {

  def this(message: String) {
    this(message, null)
  }

  def this(e: Exception) {
    this(null, e)
  }

  override def getMessage: String = {
    val message = super.getMessage
    if (message == null && e != null)
      e.getMessage
    else
      message
  }

  def getException: Exception = e

  override def toString: String = if (e ne null) e.toString else super.toString
}
