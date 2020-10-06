package org.xml.sax

import java.io.IOException

trait EntityResolver {


  def resolveEntity(publicId: String, systemId: String): Unit
}
