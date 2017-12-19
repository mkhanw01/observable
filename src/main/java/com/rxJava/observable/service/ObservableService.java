package com.rxJava.observable.service;


import com.rxJava.observable.Util;
import com.rxJava.observable.model.Message;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import rx.Observable;
import rx.schedulers.Schedulers;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by khan on 12/19/17.
 */
@Slf4j
@Service
public class ObservableService {

  private final ExecutorService customObservableService = Executors.newFixedThreadPool(100);
  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  public Observable<Message> getMessageObservable(){
    return Observable.<Message>create(subscriber -> {
      log.info("Start: executing with slow task in observableService");
      Util.delay(100);
      subscriber.onNext(new Message("data 1"));
      log.info("End: executing with slow task ");
      subscriber.onCompleted();
    }).subscribeOn(Schedulers.from(this.customObservableService));
  }

  public CompletableFuture<Message> getCompletableFuture() {
    return CompletableFuture.supplyAsync(() -> {
      log.info("Start: executing  slow task in ObservableService ");
      Util.delay(100);
      log.info("End: executing slow task in ObservableService ");
      return new Message("data one");
    },this.executorService);
  }
}
