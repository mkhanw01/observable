package com.rxJava.observable.controller;

import com.rxJava.observable.model.Message;
import com.rxJava.observable.service.ObservableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.concurrent.CompletableFuture;

/**
 * Created by khan on 12/19/17.
 */
@Slf4j
@RestController
public class ObservableController {

  @Autowired
  private ObservableService observableService;

  @RequestMapping(value = "/getAMessageObservableBlocking")
  public Message getMessage() {
    return observableService.getMessageObservable().toBlocking().first();
  }

  @RequestMapping(value = "/getAMessageObsAsync")
  public DeferredResult<Message> messageDeferredResult() {
    Observable<Message> observable = this.observableService.getMessageObservable();
    DeferredResult<Message> deferredResult = new DeferredResult<>(9000l);
    observable.subscribe(message -> deferredResult.setResult(message),
        e -> deferredResult.setErrorResult(e));
    return deferredResult;
  }

  @RequestMapping(value = "/getAMessageFutureBlocking")
  public Message getAMessageFutureBlockingMessage() throws Exception {
    return this.observableService.getCompletableFuture().get();
  }

  @RequestMapping(value = "/getAMessageFutureAsync")
  public DeferredResult<Message> getAMessageFutureAsync() {
    DeferredResult<Message> deferredResult = new DeferredResult<>(999l);
    CompletableFuture<Message> completableFuture = this.observableService.getCompletableFuture();
    completableFuture.whenComplete((res, exe) -> {
      if (exe != null) {
        deferredResult.setErrorResult(exe);
      } else {
        deferredResult.setResult(res);
      }
    });
    return deferredResult;
  }

  @RequestMapping(value = "/getQuickMesage")
  public Message getAFastMessage() {
    return new Message("fast message");
  }

}
