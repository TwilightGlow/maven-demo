package com.example.config;

import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @date 2022/4/13
 */
@Endpoint(id = "hikari")
@Configuration
public class HikariEndpointConfig {

    private HikariDataSource hikariDataSource;

    @Autowired
    public HikariEndpointConfig(DataSource dataSource) {
        hikariDataSource = (HikariDataSource) dataSource;
    }

    @ReadOperation
    public Map<String, Object> info() {
        HashMap<String, Object> info = new HashMap<>();
        HikariConfigMXBean mxBean = hikariDataSource.getHikariConfigMXBean();
        if (mxBean != null) {
            info.put("maximumPoolSize", mxBean.getMaximumPoolSize());
            info.put("idleTimeout", mxBean.getIdleTimeout());
            info.put("minimumIdle", mxBean.getMinimumIdle());
            info.put("catalog", mxBean.getCatalog());
            info.put("connectionTimeout", mxBean.getConnectionTimeout());
            info.put("leakDetectionThreshold", mxBean.getLeakDetectionThreshold());
            info.put("maxLifetime", mxBean.getMaxLifetime());
            info.put("poolName", mxBean.getPoolName());
            info.put("validationTImeout", mxBean.getValidationTimeout());
        }
        HikariPoolMXBean poolMXBean = hikariDataSource.getHikariPoolMXBean();
        if (poolMXBean != null) {
            info.put("activeConnections", poolMXBean.getActiveConnections());
            info.put("threadsAwaitingConnection", poolMXBean.getThreadsAwaitingConnection());
            info.put("idleConnections", poolMXBean.getIdleConnections());
            info.put("totalConnections", poolMXBean.getTotalConnections());
        }
        return info;
    }
}
