package org.xml.sax

import java.io.IOException

trait EntityResolver {
  @throws[SAXException]
  @throws[IOException]
  def resolveEntity(publicId: String, systemId: String): Unit
}
