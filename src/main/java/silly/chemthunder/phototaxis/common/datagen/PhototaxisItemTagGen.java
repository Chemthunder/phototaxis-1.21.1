package silly.chemthunder.phototaxis.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import silly.chemthunder.phototaxis.common.index.PhototaxisItems;
import silly.chemthunder.phototaxis.common.index.PhototaxisTags;

import java.util.concurrent.CompletableFuture;

public class PhototaxisItemTagGen extends FabricTagProvider.ItemTagProvider {
    public PhototaxisItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(PhototaxisTags.MOTHS).add(
                PhototaxisItems.MOTH,
                PhototaxisItems.REDHEADED_MOTH,
                PhototaxisItems.DUSTY_MOTH,
                PhototaxisItems.SATIN_MOTH
        );
    }
}
