package CaisseDeconnectee.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("CaisseDeconnectee.Controller")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService jwtService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.exceptionHandling()
        .authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/Facture/**","/Client/**",
                		"/Profile/**","/User/**","/UserProfile/**","/swagger-ui.html",
                		"/v3/api-docs/**", "/Contrat/**"  ,"/Agent/**"  ,     
                		"/Debt/**"  ,"/Org/**"  , "/Account/**"  ,  
                		"/swagger-ui/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/Auth/authenticate" ).permitAll()
        		.antMatchers(HttpMethod.GET,"/Auth/getUserNameJWT/**"   ).permitAll()
        		.antMatchers(HttpMethod.GET,"/Auth/getAgentId/**"  ).permitAll()
        		.antMatchers(HttpMethod.GET,"/Auth/getUserJWT/**" ,"/Auth/getUserFLJWT/**" ,
        				"/Auth/getUserRoleJWT/{jwt}" ).permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                
        ;

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }
}
