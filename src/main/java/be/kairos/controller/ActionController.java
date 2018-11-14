package be.kairos.controller;

import be.kairos.representation.ActionR;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ActionController {

    private ActionR actionR = null;

    @RequestMapping(value = "action/{actionId}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ActionR get() {
        actionR = new ActionR(12L, "Content const");
        return actionR;
    }

    @RequestMapping(value = "action", method = POST, produces = APPLICATION_JSON_VALUE)
    public ActionR create() {
        actionR = new ActionR(12L, "Content const");
        return actionR;
    }
}
