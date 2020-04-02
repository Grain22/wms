package grain.controller;

import grain.Strings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wulifu
 */
@RestController
@RequestMapping(Strings.NODE)
public class NodeCont {
    @RequestMapping(Strings.ADD_TASK)
    public void addTask(String json) {

    }
}
