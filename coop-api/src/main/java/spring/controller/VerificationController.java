package spring.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.service.verification.IVerificationService;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.status;
import static spring.util.Constants.VERIFY_TOKEN_MESSAGE;

@RestController
@RequestMapping("/config/api/v1/verify")
public class VerificationController {


    private static final Logger LOGGER = getLogger(VerificationController.class);
    private IVerificationService verificationService;


    @Autowired
    public VerificationController(IVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping
    public ResponseEntity<Void> verifyAccount(@Param("token") String token){
        LOGGER.info(format(VERIFY_TOKEN_MESSAGE,token));

        verificationService.verifyToken(token);

        return status(NO_CONTENT).build();
    }
}
