package org.taktik.connector.business.common.util;

import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.technical.beid.BeIDInfo;
import org.taktik.connector.technical.beid.domain.Identity;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public final class EidUtils {
   public static Patient readFromEidCard() throws TechnicalConnectorException {
      return readFromEidCard("patient");
   }

   public static Patient readFromEidCard(String scope) throws TechnicalConnectorException {
      BeIDInfo beIdInfo = BeIDInfo.getInstance(scope);
      Identity identity = beIdInfo.getIdentity();
      Patient patient = new Patient();
      patient.setEidCardNumber(identity.getCardNumber());
      patient.setLastName(identity.getName());
      patient.setMiddleName(identity.getMiddleName());
      patient.setFirstName(identity.getFirstName());
      patient.setInss(identity.getNationalNumber());
      return patient;
   }
}
