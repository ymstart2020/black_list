package com.atguigu.util

import java.io.InputStreamReader
import java.util.Properties

import org.apache.commons.io.Charsets

/**
 * @author ymstart
 * @create 2020-11-25 11:41
 */
object PropertiesUtil {

  def load(propertiesName:String): Properties = {
    val prop = new Properties()
    prop.load( new InputStreamReader(Thread.currentThread().getContextClassLoader.getResourceAsStream(propertiesName),Charsets.UTF_8))
    prop
  }

}
