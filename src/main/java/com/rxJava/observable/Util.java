package com.rxJava.observable;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by khan on 12/19/17.
 */
@Slf4j
public class Util {
  public static void delay(long millis) {
    try {
      Thread.sleep(millis);
    }catch (Exception e) {
      log.error("thread error: {}", e);
    }

  }
}
