package com.afkl.cases.pa.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class TravelCustomExcHandler {

  private static final Logger LOGGER =
      Logger.getLogger(TravelCustomExcHandler.class.getName());



  @ExceptionHandler({TravelCustomException.class})
  @ResponseBody
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public TravelCustomError serviceError(HttpServletResponse httpRes,
                                        TravelCustomException exception) {

    String errorCode = exception.getcustError().getErrorCode();

    if (errorCode.equalsIgnoreCase(TravelCustomConstants.NO_DATA_FOUND_E1003)) {
      httpRes.setStatus(HttpStatus.NOT_FOUND.value());
    } else if (errorCode.equalsIgnoreCase(TravelCustomConstants.MISSING_INPUT_ERROR_E1001)) {
      httpRes.setStatus(HttpStatus.BAD_REQUEST.value());
    } else if (errorCode
        .equalsIgnoreCase(TravelCustomConstants.ERROR_INVALID_PARAM_COMBINATION_E1005)) {
      httpRes.setStatus(HttpStatus.FORBIDDEN.value());
    } else if (errorCode.equalsIgnoreCase(TravelCustomConstants.UNAUTHORIZED_E1006)) {
      httpRes.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    else {
      httpRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    LOGGER.log(Level.SEVERE,
        "DataServiceException Caught in Handler - " + exception.getcustError().getErrorCode() + "-"
            + exception.getcustError().getErrorMessage() + "-"
            + exception.getcustError().getUserMessage());

    return exception.getcustError();
  }

}
