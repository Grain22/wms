package grain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wulifu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Task {
    Integer taskId;
    Object info;
    int priority = 3;
    String addedDate;
    Integer taskLong;

    public static final int priority_top = 9;
    public static final int priority_bottom = 0;

    public void increasePriorityToTop() {
        this.priority = priority_top;
    }

    public void increasePriority() {
        if (this.priority < priority_top) {
            this.priority++;
        }
    }

    public void decreasePriority() {
        if (this.priority > priority_bottom) {
            this.priority--;
        }
    }
}
