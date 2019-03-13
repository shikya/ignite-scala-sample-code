package com.shikya.basic

import org.apache.ignite.Ignition
import org.apache.ignite.client.{ClientCache, IgniteClient}
import org.apache.ignite.configuration.ClientConfiguration
import org.apache.ignite.internal.IgnitionEx

object Application extends App {

  println("Connecting to ignite")
  val igniteConfiguration = (new ClientConfiguration()).setAddresses("127.0.0.1:10800")
  val igniteClient: IgniteClient = Ignition.startClient(igniteConfiguration)
  val CACHE_NAME: String = "put-get-example"
  val cache = igniteClient.getOrCreateCache[Int, String](CACHE_NAME)

  println("Ignite Started and I got the cache")

  println(cache.get(1))
  cache.put(1, "Hello World")

  println(cache.get(1))
}
