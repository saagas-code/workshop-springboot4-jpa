package estudo.course.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/error")
public class CustomErrorController implements ErrorController {
	
    public String getErrorPath() {
        return "/error";
    }
    
    @GetMapping
    public void globalError(HttpServletResponse response) {
    	throw new ResponseStatusException(HttpStatus.valueOf(response.getStatus()));
    }

}

