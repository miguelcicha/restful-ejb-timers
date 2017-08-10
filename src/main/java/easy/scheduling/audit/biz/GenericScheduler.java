/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy.scheduling.audit.biz;

import easy.scheduling.audit.model.ScheduledTimerInfo;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author Cicha
 */
public abstract class GenericScheduler {

    @Resource
    private TimerService tsvc;

    public void schedule(ScheduleExpression expression, TimerConfig timerConfig) {
        boolean timerExists = tsvc.getTimers().stream()
                .anyMatch((timer) -> ((String) timer.getInfo()).equals(timerConfig.getInfo()));

        if (timerExists) {
            throw new IllegalArgumentException("Timer '" + timerConfig.getInfo() + "' already exists!");
        }
        tsvc.createCalendarTimer(expression, timerConfig);
    }

    @Timeout
    public abstract void execute(Timer timer);

}
