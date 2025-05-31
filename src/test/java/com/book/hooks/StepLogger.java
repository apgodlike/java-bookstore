package com.book.hooks;

import com.book.api.common.ExtentReportCommon;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

public class StepLogger implements EventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, this::handleStepFinished);
    }

    private void handleStepFinished(TestStepFinished event) {
        if (!(event.getTestStep() instanceof PickleStepTestStep)) return;

        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String keyword = step.getStep().getKeyword();  // Given/When/Then
        String text = step.getStep().getText();        // The actual step text

        String fullStep = keyword + text;
        ExtentTest test = ExtentReportCommon.getTestStep();

        if (event.getResult().getStatus() == Status.PASSED) {
            test.info("✅ " + fullStep);
        } else if (event.getResult().getStatus() == Status.FAILED) {
            test.fail("❌ " + fullStep);
        } else {
            test.warning("⚠️ " + fullStep);
        }
    }
}
