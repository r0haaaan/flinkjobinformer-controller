package in.rohaan;

import in.rohaan.model.FlinkJobCustomResource;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FlinkJobController {
    private static final Logger logger = LoggerFactory.getLogger(FlinkJobController.class.getSimpleName());
    private static final long RESYNC_PERIOD = 30 * 1000;

    public static void main(String[] args) {
        try (KubernetesClient client = new DefaultKubernetesClient()) {
            logger.info("[OK] Initialized KubernetesClient.");
            SharedInformerFactory factory = client.informers();

            logger.info("[OK] Initialized FlinkJob Informer.");
            SharedIndexInformer<FlinkJobCustomResource> flinkJobCustomResourceSharedIndexInformer = factory.sharedIndexInformerForCustomResource(FlinkJobCustomResource.class, RESYNC_PERIOD);
            flinkJobCustomResourceSharedIndexInformer.addEventHandler(new ResourceEventHandler<>() {
                @Override
                public void onAdd(FlinkJobCustomResource flinkJobCustomResource) {
                    logger.info("ADD {}/{}", flinkJobCustomResource.getMetadata().getNamespace(), flinkJobCustomResource.getMetadata().getName());
                }

                @Override
                public void onUpdate(FlinkJobCustomResource flinkJobCustomResource, FlinkJobCustomResource t1) {
                    logger.info("UPDATE {}/{}", flinkJobCustomResource.getMetadata().getNamespace(), flinkJobCustomResource.getMetadata().getName());
                }

                @Override
                public void onDelete(FlinkJobCustomResource flinkJobCustomResource, boolean b) {
                    logger.info("DELETE {}/{}", flinkJobCustomResource.getMetadata().getNamespace(), flinkJobCustomResource.getMetadata().getName());
                }
            });
            logger.info("[OK] Registered FlinkJob Informer Event Handler.");

            factory.addSharedInformerEventListener(e -> logger.error("Exception in starting informer ", e));
            Future<Void> startedFuture = factory.startAllRegisteredInformers();
            startedFuture.get();


            TimeUnit.MINUTES.sleep(60);
            factory.stopAllRegisteredInformers();
        } catch (ExecutionException executionException) {
            logger.info("Failure in execution: ", executionException);
            executionException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            logger.info("interrupted ", interruptedException);
        }
    }
}
