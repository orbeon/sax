package org.xml.sax.ext

import java.io.IOException
import org.xml.sax.EntityResolver
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import org.xml.sax.SAXException


trait EntityResolver2 extends EntityResolver {
  def getExternalSubset(name: String, baseURI: String): InputSource
  def resolveEntity(name: String, publicId: String, baseURI: String, systemId: String): InputSource
}
