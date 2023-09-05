package com.its.ecartsales.services.decryptor

object ReadEnvVariable extends App {
  println(sys.env("CUSTOMER_MASTER_KEY"))

}
