package com.market.fishmarket.infostructure.exception;

import com.market.fishmarket.infostructure.exception.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        logger.error("Unexpected error", ex);

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", "Сталася непередбачена помилка.");
        mav.addObject("status", 500);
        return mav;
    }

    @ExceptionHandler(FishNotFoundException.class)
    public ModelAndView handleFishNotFoundException(FishNotFoundException ex) {
        logger.warn(ex.getMessage());

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", ex.getMessage());
        mav.addObject("status", 404);
        return mav;
    }

    @ExceptionHandler({
            FileStorageException.class,
            DirectoryCreationException.class,
            FileDeletionException.class,
            FileReadException.class,
            InvalidImageFilesException.class
    })
    public ModelAndView handleFileExceptions(RuntimeException ex) {
        logger.error(ex.getMessage(), ex);

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", ex.getMessage());
        mav.addObject("status", 500);
        return mav;
    }
}

