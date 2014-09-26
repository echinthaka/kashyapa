# Kashyapa

Etymology: From [Kashyapa](http://en.wikipedia.org/wiki/Kashyapa_I_of_Anuradhapura) is a king ruled Sri Lanka from 473–495. He is well known for building his palace on a massive column of rock, [Sigiriya](http://en.wikipedia.org/wiki/Sigiriya) nearly 200 metres (660 ft) high.  

## Project Goal
If you picked protobuf as your data representation and serialization framework and you are working with Scala, you might not like the java code generated by the protobuf code generator (no case class support specifically). To solve this, you can use [ScalaBuff](https://github.com/SandroGrzicic/ScalaBuff) and integrate that with your sbt build with [sbt-scalabuff](https://github.com/sbt/sbt-scalabuff) project. 

Having done that, one of the challenges you might face is writing a generic converter between a serialized protobuf messages and its de-serialized scala objects. Especially if you are using [Twitter Bijection](https://github.com/twitter/bijection) for this work, you will need a converter to/from protobuf objects. 

The Kashyapa repo will have two main items to pick from if you are working with ScalaBuff and Twitter Bijection. 

<ol>
<li> it will provide you with a project template if you want to integrate protobuf scala code generation with your sbt build. This template was extracted from the repo <a href="https://github.com/jilen/srethink/tree/68fb6f8f4e4a0bdd3679519efba21768f61a1041">here</a>
<li> it will provide you with a generic Scala reflection based method to de-construct and re-construct any protobuf generated scala object.
</ol>

## Project Setup

Clone the repo and update the protobuf definitions (src/main/protobuf/kashyapa.proto) to suite your needs. You will also have a test written in ScalaBufCodecTest to test the re/de-construction of objects.

