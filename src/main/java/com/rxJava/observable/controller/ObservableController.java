package com.rxJava.observable.controller;

import com.rxJava.observable.model.Message;
import com.rxJava.observable.service.ObservableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

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
  public DeferredResult<Message> messageDeferredResult(){
    Observable<Message> observable = this.observableService.getMessageObservable();
    DeferredResult<Message> deferredResult = new DeferredResult<>(9000l);
    observable.subscribe(message -> deferredResult.setResult(message), e-> deferredResult
        .setErrorResult(e));
    return deferredResult;
  }

}
