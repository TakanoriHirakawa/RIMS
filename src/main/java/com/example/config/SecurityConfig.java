package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	/*TODO:csrfの無効化設定を解除*/
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
			.csrf(csrf-> csrf.disable())//必ず解除！
			.formLogin(login->login
					.loginPage("/login")	//loginControllerのgetMappingと合わせる
					.usernameParameter("userId")//html上のth:field{}と合わせる
					.defaultSuccessUrl("/repair_report/home")//ログイン後処理後のリクエスト
					)
			.logout(logout->logout
					.logoutSuccessUrl("/login?logout")
					.invalidateHttpSession(true)
					)
			.authorizeHttpRequests(ahr -> ahr
					.requestMatchers("/login").permitAll()//無制限に許可するリクエスト
					.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()//デフォルト位置の静的リソースの許可
					.anyRequest().authenticated()
					);
		
		return http.build();
	}
}
