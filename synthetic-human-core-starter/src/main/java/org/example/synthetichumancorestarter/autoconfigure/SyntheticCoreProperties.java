package org.example.synthetichumancorestarter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "synthetic.core")
public class SyntheticCoreProperties {
    private Audit audit = new Audit();

    public static class Audit {
        private String mode = "console";
        private String kafkaTopic = "android-audit";

        // Getters and setters
        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getKafkaTopic() {
            return kafkaTopic;
        }

        public void setKafkaTopic(String kafkaTopic) {
            this.kafkaTopic = kafkaTopic;
        }
    }

    // Getters and setters
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}