package easy.scheduling.audit.biz;

import easy.scheduling.audit.model.ScheduledTimerInfo;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Stateless
public class ScheduledAuditService extends GenericScheduler {

    public void execute(Timer timer) {

        System.out.println("Timer details\n");
        System.out.println("Timer next timeout " + timer.getNextTimeout());
        System.out.println("Timer persistent ? " + timer.isPersistent());
    }

}
