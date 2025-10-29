package silly.chemthunder.phototaxis;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import silly.chemthunder.phototaxis.datagen.PhototaxisItemTagGen;

public class PhototaxisDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(PhototaxisItemTagGen::new);
	}
}
