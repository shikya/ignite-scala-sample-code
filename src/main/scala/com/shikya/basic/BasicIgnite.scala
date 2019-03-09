package com.shikya.basic

import java.util.concurrent.TimeUnit

import javax.cache.expiry.CreatedExpiryPolicy
import org.apache.ignite.cache.{CacheMode, CacheWriteSynchronizationMode}
import org.apache.ignite.configuration.{CacheConfiguration, DataStorageConfiguration, IgniteConfiguration}

object BasicIgnite extends App {
  println("Basic Ignite Started")
}

class IgniteCustomStorageClass {
  val WalPath = "/tmp/wal"

  def getStorageConfig(): DataStorageConfiguration ={
    val dataStorageConfiguration: DataStorageConfiguration = new DataStorageConfiguration()
    dataStorageConfiguration.getDefaultDataRegionConfiguration.setPersistenceEnabled(true)
    dataStorageConfiguration.setStoragePath(WalPath)
    dataStorageConfiguration.setWalPath(WalPath)
    dataStorageConfiguration.setWalArchivePath(WalPath)
  }
}

class IgniteCustomConfig {
  def getIgniteConfig(storageCfg: DataStorageConfiguration): IgniteConfiguration ={
    val NativePersistant = "device_native"
    val WalPath = "/tmp/wal"
    val config = new IgniteConfiguration()

    val cacheCfg = new CacheConfiguration(NativePersistant)
    cacheCfg.setBackups(1)
    cacheCfg.setCacheMode(CacheMode.REPLICATED)
    cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_ASYNC)
    cacheCfg.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new javax.cache.expiry.Duration(TimeUnit.SECONDS, 30)))
    config.setDataStorageConfiguration(storageCfg)
    config.setCacheConfiguration(cacheCfg)

    config
  }
}