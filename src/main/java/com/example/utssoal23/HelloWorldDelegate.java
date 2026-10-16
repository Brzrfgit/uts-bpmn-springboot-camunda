package com.example.utssoal23;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("helloWorldDelegate")
public class HelloWorldDelegate implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.info("=================================================");
        logger.info("🎉 [SERVICE TASK] HELLO WORLD dari Camunda BPM!");
        logger.info("📌 Process Instance ID : {}", execution.getProcessInstanceId());
        logger.info("📌 Nama Task Saat Ini   : {}", execution.getCurrentActivityName());
        logger.info("=================================================");

        execution.setVariable("pesan", "Hello World berhasil dieksekusi!");
        execution.setVariable("waktuEksekusi", LocalDateTime.now().toString());
    }
}
