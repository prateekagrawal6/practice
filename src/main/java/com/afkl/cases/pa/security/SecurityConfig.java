package com.afkl.cases.pa.security;

import com.afkl.cases.pa.metrics.TravelMetricsInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Value("${oauth2.client-id}")
	private String clientId;
	
	@Value("${oauth2.client-secret}")
	private String clientSecret;
	
	@Value("${oauth2.token-url}")
	private String tokenUrl;
	
	@Value("${oauth2.grant-type}")
	private String grantType;
	

	@Value("${mock.fareurl}")
	private String fareUrl;
	
	@Value("${mock.searchurl}")
	private String searchUrl;

	@Value("${mock.airportsurl}")
	private String airportsUrl;
	
	public String getAirportsUrl() {
		return airportsUrl;
	}

	public String getFareUrl() {
		return fareUrl;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
    }

	@Bean(name="oAuth2RestTemplate")
	public OAuth2RestTemplate oauth2RestTemplate() {
		return new OAuth2RestTemplate(resource());
	}
	
	public OAuth2ProtectedResourceDetails  resource() {
		ClientCredentialsResourceDetails   clientCredentialsResourceDetails = new ClientCredentialsResourceDetails ();
        clientCredentialsResourceDetails.setAccessTokenUri(tokenUrl);
        clientCredentialsResourceDetails.setClientId(clientId);
        clientCredentialsResourceDetails.setClientSecret(clientSecret);
        
        
        return clientCredentialsResourceDetails;
    }
	
	@Component
    public static class InterceptorConfigurer extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new TravelMetricsInterceptor());
        }
    }

   
}
