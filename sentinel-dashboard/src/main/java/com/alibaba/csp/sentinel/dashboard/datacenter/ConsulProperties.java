package com.alibaba.csp.sentinel.dashboard.datacenter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.style.ToStringCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @Author guofeng@picooc.com
 * @Date 2020-04-21
 * @Version V1.0
 **/
@ConfigurationProperties("consul")
@Validated
public class ConsulProperties {

    /** Consul agent hostname. Defaults to 'localhost'. */
    @NotNull
    private String host = "localhost";

    /**
     * Consul agent scheme (HTTP/HTTPS). If there is no scheme in address - client will
     * use HTTP.
     */
    private String scheme;

    /** Consul agent port. Defaults to '8500'. */
    @NotNull
    private int port = 8500;

    /** Is spring cloud consul enabled. */
    private boolean enabled = true;

    /** configuration for TLS. */
    private TLSConfig tls;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getScheme() {
        return this.scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public TLSConfig getTls() {
        return this.tls;
    }

    public void setTls(TLSConfig tls) {
        this.tls = tls;
    }

    @Override
    public String toString() {
        return "ConsulProperties{" + "host='" + this.host + '\'' + ", port=" + this.port
                + ", scheme=" + this.scheme + ", tls=" + this.tls + ", enabled="
                + this.enabled + '}';
    }

    /**
     * TLS configuration.
     */
    public static class TLSConfig {

        /** Type of key framework to use. */
        private com.ecwid.consul.transport.TLSConfig.KeyStoreInstanceType keyStoreInstanceType;

        /** Path to an external keystore. */
        private String keyStorePath;

        /** Password to an external keystore. */
        private String keyStorePassword;

        /** File path to the certificate. */
        private String certificatePath;

        /** Password to open the certificate. */
        private String certificatePassword;

        public TLSConfig() {
        }

        public TLSConfig(com.ecwid.consul.transport.TLSConfig.KeyStoreInstanceType keyStoreInstanceType, String keyStorePath,
                         String keyStorePassword, String certificatePath,
                         String certificatePassword) {
            this.keyStoreInstanceType = keyStoreInstanceType;
            this.keyStorePath = keyStorePath;
            this.keyStorePassword = keyStorePassword;
            this.certificatePath = certificatePath;
            this.certificatePassword = certificatePassword;
        }

        public com.ecwid.consul.transport.TLSConfig.KeyStoreInstanceType getKeyStoreInstanceType() {
            return this.keyStoreInstanceType;
        }

        public void setKeyStoreInstanceType(com.ecwid.consul.transport.TLSConfig.KeyStoreInstanceType keyStoreInstanceType) {
            this.keyStoreInstanceType = keyStoreInstanceType;
        }

        public String getKeyStorePath() {
            return this.keyStorePath;
        }

        public void setKeyStorePath(String keyStorePath) {
            this.keyStorePath = keyStorePath;
        }

        public String getKeyStorePassword() {
            return this.keyStorePassword;
        }

        public void setKeyStorePassword(String keyStorePassword) {
            this.keyStorePassword = keyStorePassword;
        }

        public String getCertificatePath() {
            return this.certificatePath;
        }

        public void setCertificatePath(String certificatePath) {
            this.certificatePath = certificatePath;
        }

        public String getCertificatePassword() {
            return this.certificatePassword;
        }

        public void setCertificatePassword(String certificatePassword) {
            this.certificatePassword = certificatePassword;
        }

        @Override
        public String toString() {
            return new ToStringCreator(this)
                    .append("keyStoreInstanceType", this.keyStoreInstanceType)
                    .append("keyStorePath", this.keyStorePath)
                    .append("keyStorePassword", this.keyStorePassword)
                    .append("certificatePath", this.certificatePath)
                    .append("certificatePassword", this.certificatePassword).toString();
        }

    }
}
