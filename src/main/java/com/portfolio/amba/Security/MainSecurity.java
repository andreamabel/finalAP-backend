
package com.portfolio.amba.Security;

//import com.portfolio.amba.Security.Service.UserDetailsImpl;
import com.portfolio.amba.Security.jwt.JwtEntryPoint;
import com.portfolio.amba.Security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity {
    //@Autowired
    //UserDetailsImpl userDetailsServicesImpl;
    
    @Autowired
    JwtEntryPoint jwtEntryPoint;
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    //@Override  REMPLAZADO
    //protected void configure(HttpSecurity http) throws Exception {
    //    http.cors().and().csrf().disable()
    //            .authorizeRequests()
    //            .antMatchers("/auth/**").permitAll()
    //            .anyRequest().authenticated()
    //            .and()
    //            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
    //            .and()
    //            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    //  http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    //}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //@Override
    //protected AuthenticationManager authenticationManager() throws Exception {
    //    return super.authenticationManager();
    //}

    //@Bean
    //@Override
    //public AuthenticationManager authenticationManagerBean() throws Exception {
    //    return super.authenticationManagerBean();
    //}

    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(userDetailsServicesImpl).passwordEncoder(passwordEncoder());
    //}
}
