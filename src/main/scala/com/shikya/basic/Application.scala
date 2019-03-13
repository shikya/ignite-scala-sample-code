package com.shikya.basic

import org.apache.ignite.Ignition
import org.apache.ignite.cache.query.ScanQuery
import org.apache.ignite.client.{ClientCache, IgniteClient}
import org.apache.ignite.configuration.ClientConfiguration
import org.apache.ignite.internal.IgnitionEx

case class Teacher(name: String, age: Number)

object Application extends App {

  println("Connecting to ignite")
  val igniteConfiguration = (new ClientConfiguration()).setAddresses("127.0.0.1:10800")
  val igniteClient: IgniteClient = Ignition.startClient(igniteConfiguration)
  val TEACHER: String = "TEACHER"
  val COUNTER= "COUNTER"

  val teacherCache = igniteClient.getOrCreateCache[Int, Teacher](TEACHER)
  val counterCache = igniteClient.getOrCreateCache[String, Int](COUNTER)

  println("Ignite Started and I got the cache")

  var counter = counterCache.getAndPut(TEACHER, -1)
  println(s"Counter is on ${counter}")

  for(i <- 0 to 10){
    counter = counter +1
    teacherCache.put(counter, Teacher("SomeName", (counter*15+32)%18+18))
    counterCache.put(TEACHER, counter)
  }

  for(i <- 1 to counter){
    println(s"${i} : ${teacherCache.get(i)}")
  }

  Ignition.stop(false)
}
