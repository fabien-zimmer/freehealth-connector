package org.taktik.connector.business.mycarenet.attest.builders;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class RequestObjectBuilderFactory {
   public static final String PROP_REQUESTBUILDER_CLASS = "attest.requestobjectbuilder.class";
   public static final String DEFAULT_REQUESTBUILDER_CLASS = "org.taktik.connector.business.mycarenet.attest.builders.impl.RequestObjectBuilderImpl";
   private static ConfigurableFactoryHelper<RequestObjectBuilder> helperFactoryrequestBuilder = new ConfigurableFactoryHelper("attest.requestobjectbuilder.class", "org.taktik.connector.business.mycarenet.attest.builders.impl.RequestObjectBuilderImpl");

   public static RequestObjectBuilder getRequestObjectBuilder() throws TechnicalConnectorException {
      return (RequestObjectBuilder)helperFactoryrequestBuilder.getImplementation();
   }
}
