package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.util.Iterator;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleProperties implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleProperties.class);

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      if (config instanceof ConfigurationImpl) {
         Properties properties = ((ConfigurationImpl)config).getProperties();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Current properties are : ");
            Iterator i$ = properties.keySet().iterator();

            while(i$.hasNext()) {
               Object key = i$.next();
               LOG.debug(" ." + key + " = [" + properties.getProperty((String)key) + "]");
            }
         }
      }

   }

   public void unload() throws TechnicalConnectorException {
   }
}
