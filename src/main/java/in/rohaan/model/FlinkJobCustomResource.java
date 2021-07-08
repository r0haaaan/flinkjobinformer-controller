package in.rohaan.model;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.ShortNames;

@Group(FlinkJobConstants.GROUP)
@Version("v1")
@Kind(FlinkJobConstants.KIND)
@Plural(FlinkJobConstants.PLURAL)
@ShortNames({FlinkJobConstants.SHORT_NAME})
public class FlinkJobCustomResource extends CustomResource<FlinkJobSpec, FlinkJobStatus> implements Namespaced {
    private static final long serialVersionUID = 1L;

    @Override
    protected FlinkJobSpec initSpec() {
        return new FlinkJobSpec();
    }

    @Override
    protected FlinkJobStatus initStatus() {
        return new FlinkJobStatus();
    }
}
