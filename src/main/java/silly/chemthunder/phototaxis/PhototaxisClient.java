package silly.chemthunder.phototaxis;

import net.fabricmc.api.ClientModInitializer;
import silly.chemthunder.phototaxis.common.index.PhototaxisEntities;

public class PhototaxisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PhototaxisEntities.clientInit();
    }
}
