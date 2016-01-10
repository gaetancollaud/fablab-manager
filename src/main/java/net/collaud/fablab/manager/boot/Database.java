package net.collaud.fablab.manager.boot;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

/**
 *
 * @author Gaetan Collaud
 */
@Configuration
public class Database {
	
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource(){
		return new DriverManagerDataSource();
	}
	
}


/**
 * <bean id="fablabDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/fablab" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
	</bean>
	
	<bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
		<property name="dataSource" ref="fablabDataSource" />
		<property name="packagesToScan" value="net.collaud.fablab.manager.data" />
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="format_sql">true</prop>
			</props>
		</property>
	</bean>
	
	<tx:annotation-driven transaction-manager="txManager"/>
    <bean id="txManager" 
          class="org.springframework.orm.jpa.JpaTransactionManager"
          p:entityManagerFactory-ref="entityManagerFactory"/>
	
	<data:repositories base-package="net.collaud.fablab.manager.dao"
					   entity-manager-factory-ref="entityManagerFactory"
					   transaction-manager-ref="txManager"/>
	
	<bean id="entityManagerFactory"
		  class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="packagesToScan" value="net.collaud.fablab.manager.data" />
		<property name="dataSource" ref="fablabDataSource" />

		<property name="jpaProperties">
			<props>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
			</props>
		</property>

		<property name="persistenceProvider">
			<bean class="org.hibernate.jpa.HibernatePersistenceProvider"></bean>
		</property>
	</bean>
 */