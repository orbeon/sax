package org.xml.sax.helpers

import org.xml.sax.Attributes

class AttributesImpl extends Attributes {

  private[helpers] var length = 0
  private[helpers] var data: Array[String] = null

  def this(atts: Attributes) {
    this()
    setAttributes(atts)
  }

  def getLength: Int = length
  def getURI      (index: Int): String = if (index >= 0 && index < length) data(index * 5)     else null
  def getLocalName(index: Int): String = if (index >= 0 && index < length) data(index * 5 + 1) else null
  def getQName    (index: Int): String = if (index >= 0 && index < length) data(index * 5 + 2) else null
  def getType     (index: Int): String = if (index >= 0 && index < length) data(index * 5 + 3) else null
  def getValue    (index: Int): String = if (index >= 0 && index < length) data(index * 5 + 4) else null

  def getIndex(uri: String, localName: String): Int = {
    val max = length * 5
    var i = 0
    while (i < max) {
      if (data(i) == uri && data(i + 1) == localName)
        return i / 5
      i += 5
    }
    -1
  }

  def getIndex(qName: String): Int = {
    val max = length * 5
    var i = 0
    while (i < max) {
      if (data(i + 2) == qName)
        return i / 5
      i += 5
    }
    -1
  }

  def getType(uri: String, localName: String): String = {
    val max = length * 5
    var i = 0
    while (i < max) {
      if (data(i) == uri && data(i + 1) == localName)
        return data(i + 3)
      i += 5
    }
    null
  }

  def getType(qName: String): String = {
    val max = length * 5
    var i = 0
    while (i < max) {
      if (data(i + 2) == qName)
        return data(i + 3)
      i += 5
    }
    null
  }

  def getValue(uri: String, localName: String): String = {
    val max = length * 5
    var i = 0
    while (i < max) {
      if (data(i) == uri && data(i + 1) == localName)
        return data(i + 4)
      i += 5
    }
    null
  }

  def getValue(qName: String): String = {
    val max = length * 5
    var i = 0
    while (i < max) {
      if (data(i + 2) == qName)
        return data(i + 4)
      i += 5
    }
    null
  }

  def clear(): Unit = {
    if (data != null)
      for (i <- 0 until (length * 5))
        data(i) = null
    length = 0
  }

  def setAttributes(atts: Attributes): Unit = {
    clear()
    length = atts.getLength
    if (length > 0) {
      data = new Array[String](length * 5)
      for (i <- 0 until length) {
        data(i * 5) = atts.getURI(i)
        data(i * 5 + 1) = atts.getLocalName(i)
        data(i * 5 + 2) = atts.getQName(i)
        data(i * 5 + 3) = atts.getType(i)
        data(i * 5 + 4) = atts.getValue(i)
      }
    }
  }

  def addAttribute(uri: String, localName: String, qName: String, `type`: String, value: String): Unit = {
    ensureCapacity(length + 1)
    data(length * 5) = uri
    data(length * 5 + 1) = localName
    data(length * 5 + 2) = qName
    data(length * 5 + 3) = `type`
    data(length * 5 + 4) = value
    length += 1
  }

  def setAttribute(index: Int, uri: String, localName: String, qName: String, `type`: String, value: String): Unit =
    if (index >= 0 && index < length) {
      data(index * 5)     = uri
      data(index * 5 + 1) = localName
      data(index * 5 + 2) = qName
      data(index * 5 + 3) = `type`
      data(index * 5 + 4) = value
    } else
      badIndex(index)

  def removeAttribute(index: Int): Unit =
    if (index >= 0 && index < length) {
      var i = index
      if (i < length - 1)
        System.arraycopy(data, (i + 1) * 5, data, i * 5, (length - i - 1) * 5)
      i = (length - 1) * 5
      data(i) = null
      i += 1
      data(i) = null
      i += 1
      data(i) = null
      i += 1
      data(i) = null
      i += 1
      data(i) = null
      length -= 1
    } else
      badIndex(index)

  def setURI      (index: Int, uri: String)      : Unit = if (index >= 0 && index < length) data(index * 5)     = uri       else badIndex(index)
  def setLocalName(index: Int, localName: String): Unit = if (index >= 0 && index < length) data(index * 5 + 1) = localName else badIndex(index)
  def setQName    (index: Int, qName: String)    : Unit = if (index >= 0 && index < length) data(index * 5 + 2) = qName     else badIndex(index)
  def setType     (index: Int, `type`: String)   : Unit = if (index >= 0 && index < length) data(index * 5 + 3) = `type`    else badIndex(index)
  def setValue    (index: Int, value: String)    : Unit = if (index >= 0 && index < length) data(index * 5 + 4) = value     else badIndex(index)

  private def ensureCapacity(n: Int): Unit = {
    if (n <= 0)
      return
    var max = 0
    if (data == null || data.length == 0)
      max = 25
    else if (data.length >= n * 5)
      return
    else
      max = data.length
    while (max < n * 5)
      max *= 2
    val newData = new Array[String](max)
    if (length > 0)
      System.arraycopy(data, 0, newData, 0, length * 5)
    data = newData
  }

  private def badIndex(index: Int): Nothing =
    throw new ArrayIndexOutOfBoundsException(s"Attempt to modify attribute at illegal index: $index")
}
