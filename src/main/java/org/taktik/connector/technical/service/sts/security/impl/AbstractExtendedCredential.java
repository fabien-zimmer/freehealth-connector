package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.ExtendedCredential;
import be.fgov.ehealth.etee.crypto.utils.SecurityConfiguration;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import org.joda.time.DateTime;

public abstract class AbstractExtendedCredential implements ExtendedCredential {
   private static final CertificateFactory CF;
   private final String quality;

   public AbstractExtendedCredential() {
      this.quality = "doctor";
   }

   public AbstractExtendedCredential(String quality) {
      this.quality = quality;
   }

   public CertPath getCertPath() throws TechnicalConnectorException {
      try {
         return CF.generateCertPath(Arrays.asList(this.getCertificateChain()));
      } catch (CertificateException var2) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, var2, new Object[0]);
      }
   }

   public DateTime getExpirationDateTime() throws TechnicalConnectorException {
      return new DateTime(this.getCertificate().getNotAfter());
   }

   @Override
   public String getQuality() {
      return quality;
   }

   static {
      try {
         SecurityConfiguration.configure();
         CF = CertificateFactory.getInstance("X.509", "BC");
      } catch (NoSuchProviderException | CertificateException ex) {
         throw new IllegalArgumentException(ex);
      }
   }
}
