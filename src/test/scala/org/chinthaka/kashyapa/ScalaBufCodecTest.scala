package org.chinthaka.kashyapa

import org.chinthaka.kashyapa.protobuf.Person
import org.specs2.mutable.Specification

import scala.util.Try

/**
 * @author Eran Withana
 * @since  9/25/14
 */
class ScalaBufCodecTest extends Specification {

  "The ScalaBuffCodec" should {
    "convert to/from protobuf generated class properly" in {
      val scalaBuffCodec = ScalaBufCodec[Person]

      val originalPerson: Person = Person("John Doe", 35, "Male", Some("Ambalangoda"))

      val serializedPerson: Array[Byte] = scalaBuffCodec.apply(originalPerson)
      val reconstructedPerson: Try[Person] = scalaBuffCodec.invert(serializedPerson)

      reconstructedPerson.isSuccess must beTrue

      reconstructedPerson.get must be equalTo originalPerson

    }
  }

}