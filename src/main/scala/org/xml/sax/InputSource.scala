package org.xml.sax

import java.io.Reader
import java.io.InputStream

class InputSource {

  private var publicId       : String      = null
  private var systemId       : String      = null
  private var byteStream     : InputStream = null
  private var encoding       : String      = null
  private var characterStream: Reader      = null

  def this(systemId: String) {
    this()
    setSystemId(systemId)
  }

  def this(byteStream: InputStream) {
    this()
    setByteStream(byteStream)
  }

  def this(characterStream: Reader) {
    this()
    setCharacterStream(characterStream)
  }

  def setPublicId(publicId: String): Unit = this.publicId = publicId
  def getPublicId: String = publicId
  def setSystemId(systemId: String): Unit = this.systemId = systemId
  def getSystemId: String = systemId
  def setByteStream(byteStream: InputStream): Unit = this.byteStream = byteStream
  def getByteStream: InputStream = byteStream
  def setEncoding(encoding: String): Unit = this.encoding = encoding
  def getEncoding: String = encoding
  def setCharacterStream(characterStream: Reader): Unit = this.characterStream = characterStream
  def getCharacterStream: Reader = characterStream
}
