package com.ky.tests.config;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SSLConfiguration
{
    @Bean
    public RestOperations restOperations(ClientHttpRequestFactory clientHttpRequestFactory) throws Exception
    {
        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient)
    {
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
    
    private KeyStore _parseKeyStore(String keystorePath, String passwd) throws Exception
    {
        String keyStoreType = "jks";
        if(keystorePath.endsWith(".p12"))
        {
            keyStoreType = "pkcs12";
        }
        
        KeyStore keystore = KeyStore.getInstance(keyStoreType);
        if(keystorePath.startsWith("classpath:"))
        {
            keystorePath =  ClassLoader.getSystemResource("").getFile() + keystorePath.substring(keystorePath.indexOf(":") + 1);
        }
        FileInputStream instream = new FileInputStream(new File(keystorePath));
        try
        {
            keystore.load(instream, passwd.toCharArray());
        }
        finally
        {
            instream.close();
        }
        return keystore;
    }

    @Bean
    public HttpClient httpClient(@Value("${https.client.keystore.file}") String keyStoreFile,
                    @Value("${https.client.keystore.pass}") String keyPass,
                    @Value("${https.client.truststore.file}") String trustStoreFile,
                    @Value("${https.client.truststore.pass}") String trustPass) throws Exception
    {
        KeyStore keyStore = _parseKeyStore(keyStoreFile, keyPass);
        KeyStore trustStore = _parseKeyStore(trustStoreFile, trustPass);
        
        SSLContext sslcontext = SSLContexts.custom()//
                        .loadKeyMaterial(keyStore, keyPass.toCharArray())//
                        .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                        .build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1.2" }, null,
                        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
