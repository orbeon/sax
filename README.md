# SAX API Scala implementation

## What is this

This is a Scala translation of the SAX 2.0.2 API.

The purpose of having a Scala version of this API is so that this API can be used with [Scala.js](https://www.scala-js.org/).

## Artifacts produced

Cross-build:

- Scala 2.12 and Scala 2.13
- Scala.js 1.0 and Scala JVM (the latter is probably never useful)

## What's missing

The following are not included (yet):

- `ext.Attributes2`
- `ext.Attributes2Impl`
- `ext.DeclHandler`
- `ext.DefaultHandler2`
- `ext.EntityResolver2`
- `ext.Locator2`
- `ext.Locator2Impl`
- interfaces that were deprecated in 2004 already!

Adding those would be very easy if needed. 

## Building

```
sbt package
```
