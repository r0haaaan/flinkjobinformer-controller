package in.rohaan.model;

public class FlinkJobSpec {
    public String getFlinkJobSpec() {
        return flinkJobSpec;
    }

    public void setFlinkJobSpec(String flinkJobSpec) {
        this.flinkJobSpec = flinkJobSpec;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    private String flinkJobSpec;
    private String image;
    private int replicas;

    @Override
    public String toString() {
        return "FlinkJobSpec{" +
                "replicas=" + replicas  +
                ", flinkJobSpec='" + flinkJobSpec + "'" +
                ", image='" + image + "'" +
                "}";
    }
}
