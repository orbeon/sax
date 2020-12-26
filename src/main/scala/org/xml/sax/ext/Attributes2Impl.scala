package org.xml.sax.ext

import org.xml.sax.Attributes
import org.xml.sax.helpers.AttributesImpl


class Attributes2Impl extends AttributesImpl with Attributes2 {

  private var declared : Array[Boolean] = null
  private var specified: Array[Boolean] = null

  def this(atts: Attributes) = {
    this()
    super.setAttributes(atts)
  }

  def isDeclared(index: Int): Boolean = {
    if (index < 0 || index >= getLength)
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index)
    declared(index)
  }

  def isDeclared(uri: String, localName: String): Boolean = {
    val index = getIndex(uri, localName)
    if (index < 0)
      throw new IllegalArgumentException("No such attribute: local=" + localName + ", namespace=" + uri)
    declared(index)
  }

  def isDeclared(qName: String): Boolean = {
    val index = getIndex(qName)
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException("No such attribute: " + qName)
    declared(index)
  }

  def isSpecified(index: Int): Boolean = {
    if (index < 0 || index >= getLength) throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index)
    specified(index)
  }

  def isSpecified(uri: String, localName: String): Boolean = {
    val index = getIndex(uri, localName)
    if (index < 0)
      throw new IllegalArgumentException("No such attribute: local=" + localName + ", namespace=" + uri)
    specified(index)
  }

  def isSpecified(qName: String): Boolean = {
    val index = getIndex(qName)
    if (index < 0)
      throw new IllegalArgumentException("No such attribute: " + qName)
    specified(index)
  }

  override def setAttributes(atts: Attributes): Unit = {
    val length = atts.getLength
    super.setAttributes(atts)
    declared = new Array[Boolean](length)
    specified = new Array[Boolean](length)
    if (atts.isInstanceOf[Attributes2]) {
      val a2 = atts.asInstanceOf[Attributes2]
      for (i <- 0 until length) {
        declared(i) = a2.isDeclared(i)
        specified(i) = a2.isSpecified(i)
      }
    } else
      for (i <- 0 until length) {
        declared(i) = ! "CDATA".equals(atts.getType(i))
        specified(i) = true
      }
  }

  override def addAttribute(uri: String, localName: String, qName: String, `type`: String, value: String): Unit = {
    super.addAttribute(uri, localName, qName, `type`, value)
    val length = getLength
    if (length < specified.length) {
      var newFlags = new Array[Boolean](length)
      System.arraycopy(declared, 0, newFlags, 0, declared.length)
      declared = newFlags
      newFlags = new Array[Boolean](length)
      System.arraycopy(specified, 0, newFlags, 0, specified.length)
      specified = newFlags
    }
    specified(length - 1) = true
    declared(length - 1) = ! "CDATA".equals(`type`)
  }

  override def removeAttribute(index: Int): Unit = {
    val origMax = getLength - 1
    super.removeAttribute(index)
    if (index != origMax) {
      System.arraycopy(declared, index + 1, declared, index, origMax - index)
      System.arraycopy(specified, index + 1, specified, index, origMax - index)
    }
  }

  def setDeclared(index: Int, value: Boolean): Unit = {
    if (index < 0 || index >= getLength)
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index)
    declared(index) = value
  }

  def setSpecified(index: Int, value: Boolean): Unit = {
    if (index < 0 || index >= getLength)
      throw new ArrayIndexOutOfBoundsException("No attribute at index: " + index)
    specified(index) = value
  }
}
