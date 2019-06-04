/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.avianca.esb.shipmentsultimus.configurator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.avianca.esb.shipmentsultimus.properties.DatasourceSqlProducer;

@Component
@Configuration
public class DatasourceSqlConfigurationProducer {

    @Autowired
    private DatasourceSqlProducer consumerBase;
    
    @Bean("dataSourceSqlServer")
    public DriverManagerDataSource getConfigSql() {
        DriverManagerDataSource dataSourceSql = new DriverManagerDataSource();
        dataSourceSql.setDriverClassName(consumerBase.getDriver());
        dataSourceSql.setUrl(consumerBase.getUrl());        
        dataSourceSql.setUsername(consumerBase.getUser());
        dataSourceSql.setPassword(consumerBase.getPasswd());
        return dataSourceSql;
    }
    
    @Bean("jdbcTxManagerSqlServer")
    public PlatformTransactionManager jdbcTxManager() {
    	DataSourceTransactionManager dataSourceTransactionManagerSql = new DataSourceTransactionManager(getConfigSql());
    	return dataSourceTransactionManagerSql;
    }
}