package fr.SafetyNet.SafetyNetAlerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.SafetyNet.SafetyNetAlerts.util.NotTested;

@NotTested
@SpringBootApplication
public class SafetyNetAlertsApplication {

	private static Logger logger = LoggerFactory.getLogger(SafetyNetAlertsApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		logger.info("SafetyNetAlertsApplication started");

	}

}
