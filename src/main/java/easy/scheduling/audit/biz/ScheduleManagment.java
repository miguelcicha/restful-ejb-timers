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
import javax.ejb.Stateless;
import javax.ejb.TimerService;

/**
 *
 * @author Cicha
 */
@Stateless
public class ScheduleManagment {

    @Resource
    private TimerService tsvc;

    
    public void invoke(){
           //Entidad
           // -String: Clase   ej "ScheduledAuditService"
           // -ScheduleConfiguration
           // -autoInit: boolean
    }
    
    public ScheduledTimerInfo getTimerInfo(String name) {

        boolean timerExists = tsvc.getAllTimers().stream()
                .anyMatch((timer) -> ((String) timer.getInfo()).equals(name));

        if (!timerExists) {
            throw new IllegalStateException("Timer '" + name + "' does not exist");
        }

        return tsvc.getAllTimers().stream()
                .filter((timer) -> ((String) timer.getInfo()).equals(name))
                .map((timer) -> new ScheduledTimerInfo(name, timer.getSchedule(), timer.getTimeRemaining(), timer.getNextTimeout().toString()))
                .findFirst().get();

    }

    public List<String> getAllTimers() {
        return tsvc.getAllTimers().stream().map((timer) -> ((String) timer.getInfo())).collect(Collectors.toList());
    }

    public void cancel(String name) {

        boolean timerExists = tsvc.getAllTimers().stream()
                .anyMatch((timer) -> ((String) timer.getInfo()).equals(name));

        if (!timerExists) {
            throw new IllegalStateException("Timer '" + name + "' does not exist");
        }

        tsvc.getAllTimers().stream()
                .filter((timer) -> ((String) timer.getInfo()).equals(name))
                .findFirst().get()
                .cancel();
    }
}
