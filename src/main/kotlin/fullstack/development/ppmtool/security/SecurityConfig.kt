package fullstack.development.ppmtool.security

import fullstack.development.ppmtool.security.SecurityConstants.H2_URL
import fullstack.development.ppmtool.security.SecurityConstants.SIGN_UP_URLS
import fullstack.development.ppmtool.security.SecurityConstants.GRAPIHQL_URL
import fullstack.development.ppmtool.services.CustomUserDetailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
class SecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val customUserDetailService: CustomUserDetailService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) : WebSecurityConfigurerAdapter() {
//    @Bean
//    @Throws(Exception::class)
//    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
//        return authenticationConfiguration.authenticationManager
//    }
//
//
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
//        http.cors().and().csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .headers().frameOptions().sameOrigin() //to be able to use H2 database
//            .and().authorizeRequests().antMatchers("/",
//                "/favicon.ico",
//                "/**/*.png",
//                "/**/*.gif",
//                "/**/*.svg",
//                "/**/*.jpg",
//                "/**/*.html",
//                "/**/*.css",
//                "/**/*.js").permitAll()
//            .antMatchers(GRAPIHQL_URL).permitAll()
//            .antMatchers("/api/graphql").permitAll()
//            .antMatchers(SIGN_UP_URLS).permitAll()
//            .antMatchers(H2_URL).permitAll()
//            .anyRequest().authenticated()
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
////        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
//        return http.build()
//    }

//    @Bean
//    fun corsConfigurer(): WebMvcConfigurer? {
//        return object : WebMvcConfigurer {
//            override fun addCorsMappings(registry: CorsRegistry) {
//                registry.addMapping("/**")
//                    .allowedMethods("*")
//            }
//        }
//    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .headers().frameOptions().sameOrigin() //to be able to use H2 database
            .and().authorizeRequests().antMatchers("/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js").permitAll()
            .antMatchers(GRAPIHQL_URL).permitAll()
            .antMatchers("/api/graphql").permitAll()
            .antMatchers(SIGN_UP_URLS).permitAll()
            .antMatchers(H2_URL).permitAll()
            .anyRequest().authenticated()

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }
}