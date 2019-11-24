package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.service.valueobject.ErrorDetails;
import com.jkg.www.outofahat.service.valueobject.ServiceResponse;
import org.slf4j.Logger;

public final class ServiceResponseMapper {

    private ServiceResponseMapper() {
    }

    public static ServiceResponse failure(
            Logger logger,
            SystemEvent systemEvent,
            Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(systemEvent.getId(),
                systemEvent.getDescription() + ex.getMessage());
        logger.error(errorDetails.getErrorMessage(), ex);
        return ServiceResponse.failure(errorDetails);
    }
}
