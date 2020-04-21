package com.netcrackerTask.backend.config;

import java.util.HashMap;
import java.util.Map;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {
    /**
     * PayPal client id.
     * */
    @Value("${paypal.client.id}")
    private String cliendId;

    /**
     * PayPal client sercret key.
     * */
    @Value("${paypal.client.secret}")
    private String clientSecret;

    /**
     * PayPal mode (sandbox).
     * */
    @Value("${paypal.mode}")
    private String mode;

    @Bean
    public Map<String,String> paypalSdkConfig(){
        Map<String,String> configMap = new HashMap<>();
        configMap.put("mode",mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential(){
        return new OAuthTokenCredential(cliendId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context=new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}

