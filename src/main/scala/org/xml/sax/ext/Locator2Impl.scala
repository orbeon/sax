package org.xml.sax.ext

import org.xml.sax.Locator
import org.xml.sax.helpers.LocatorImpl


class Locator2Impl(locator: Locator)
  extends LocatorImpl(locator)
     with Locator2 {

  private var encoding: String = null
  private var version : String = null

  if (locator.isInstanceOf[Locator2]) {
    val l2 = locator.asInstanceOf[Locator2]
    version  = l2.getXMLVersion
    encoding = l2.getEncoding
  }

  def this() =
    this(null)

  def getXMLVersion: String = version
  def getEncoding  : String = encoding

  def setXMLVersion(version: String) : Unit = this.version  = version
  def setEncoding  (encoding: String): Unit = this.encoding = encoding
}
