package com.scheduler.controller;


import com.scheduler.exception.BadRequestException;
import com.scheduler.exception.NotFoundException;
import com.scheduler.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class BaseController {

    private final Logger log = LoggerFactory.getLogger(CourseService.class);


    @ExceptionHandler({BadRequestException.class})
    public void handleBadRequestException() {
        log.info("400 -- Bad Request Exception");
    }


    @ExceptionHandler({NotFoundException.class})
    public void handleNotFoundException() {
        log.info("404 -- Not Found Exception");
    }


}
