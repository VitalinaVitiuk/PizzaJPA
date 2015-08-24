package web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NotFoundPizzaException.class)
    public ModelAndView exceptionHandelr(
            Exception exception,
            HttpServletRequest req) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("ex", exception);
        model.addObject("url", req.getRequestURL());
        return model;
    }
}