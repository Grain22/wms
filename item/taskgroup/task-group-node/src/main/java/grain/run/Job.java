package grain.run;

import com.alibaba.fastjson.JSON;
import grain.task.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wulifu
 */
@Slf4j
public class Job implements Runnable, Comparable<Job> {

    private final Task task;

    public Job(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(task.getPriority());
        log.info("do job {} ", JSON.toJSONString(task));
    }

    public void increasePriority() {
        task.increasePriority();
        Thread.currentThread().setPriority(task.getPriority());
    }

    @Override
    public int compareTo(Job o)
    {
        if (this.task.getPriority() == o.task.getPriority()) {
            return task.getAddedDate().compareTo(o.task.getAddedDate());
        }
        return this.task.getPriority() - o.task.getPriority();
    }
}
