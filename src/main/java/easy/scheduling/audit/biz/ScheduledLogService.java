/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easy.scheduling.audit.biz;

import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

/**
 *
 * @author Cicha
 */
@Stateless
public class ScheduledLogService extends GenericScheduler {

    public void execute(Timer timer) {

        System.out.println("Timer details\n");
        System.out.println("Timer next timeout " + timer.getNextTimeout());
        System.out.println("Timer persistent ? " + timer.isPersistent());
    }
}
