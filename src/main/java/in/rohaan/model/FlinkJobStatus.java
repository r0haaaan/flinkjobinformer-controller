package in.rohaan.model;

public class FlinkJobStatus {
    private int replicas;
    private String labelSelector;
    
    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }


    public String getLabelSelector() {
        return labelSelector;
    }

    public void setLabelSelector(String labelSelector) {
        this.labelSelector = labelSelector;
    }

    @Override
        public String toString() {
            return "FlinkJobStatus{" +
                " replicas=" + replicas +
                " , labelSelector='" + labelSelector + "'" +
                "}";
        }
}
