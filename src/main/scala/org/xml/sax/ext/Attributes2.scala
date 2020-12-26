package org.xml.sax.ext

import org.xml.sax.Attributes


trait Attributes2 extends Attributes {
  def isDeclared(index: Int): Boolean
  def isDeclared(qName: String): Boolean
  def isDeclared(uri: String, localName: String): Boolean
  def isSpecified(index: Int): Boolean
  def isSpecified(uri: String, localName: String): Boolean
  def isSpecified(qName: String): Boolean
}
