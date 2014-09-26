package org.chinthaka.kashyapa

import com.google.protobuf.MessageLite
import com.twitter.bijection.Injection
import com.twitter.bijection.Inversion.attempt

import scala.collection.mutable.{Map => MMap}
import scala.reflect.runtime.{universe => ru}

/**
 * @author Eran Withana
 * @since  9/25/14
 */
object ScalaBufCodec {
  /**
   * For scala instantiation. Uses reflection.
   * def typeOf[T](implicit ttag: TypeTag[T]): Type
   */
  implicit def apply[T <: MessageLite : Manifest]: Injection[T, Array[Byte]] = {
    new ScalaBufCodec[T]
  }

}

class ScalaBufCodec[T <: MessageLite](implicit man: Manifest[T])
  extends Injection[T, Array[Byte]] {

  // get to scala run time mirrors
  val runtimeMirror = ru.runtimeMirror(getClass.getClassLoader)

  // get the companion object of T
  val companionObjectInstance = companionOf[T].get

  // get the instance mirror of companion
  val reflectedObject = runtimeMirror.reflect(companionObjectInstance)

  // the defaultInstance param in every companion object refers to an instance of its case class.
  val defaultInstanceField = reflectedObject.symbol.typeSignature.member(ru.newTermName("defaultInstance")).asTerm
    .accessed.asTerm

  // Now we need to invoke the mergeFrom method and that comes from MessageLite.Builder, cast to that in preparation to
  // invoke it
  val builder = reflectedObject.reflectField(defaultInstanceField).get
    .asInstanceOf[com.google.protobuf.MessageLite.Builder]

  override def apply(item: T) = item.toByteArray

  override def invert(bytes: Array[Byte]) = {

    attempt(bytes) { bytes => {
      builder.mergeFrom(bytes).asInstanceOf[T]
    }
    }
  }

  /**
   * Got the code from http://stackoverflow.com/questions/9172775/get-companion-object-of-class-by-given-generic-type-scala
   */
  def companionOf[T: Manifest]: Option[AnyRef] = {
    try {
      val classOfT = implicitly[Manifest[T]].erasure
      val companionClassName = classOfT.getName + "$"
      val companionClass = Class.forName(companionClassName)
      val moduleField = companionClass.getField("MODULE$")
      Some(moduleField.get(null))
    } catch {
      case e: Throwable => throw e
    }
  }
}