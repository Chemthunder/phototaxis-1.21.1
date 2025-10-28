package silly.chemthunder.phototaxis.object.entity.moths.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import silly.chemthunder.phototaxis.Phototaxis;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;
import silly.chemthunder.phototaxis.object.entity.moths.MothVariant;

import java.util.Map;

public class MothEntityRenderer extends MobEntityRenderer<MothEntity, MothModelEntity<MothEntity>> {
    public MothEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MothModelEntity<>(context.getPart(MothModelEntity.MOTH_MODEL)), 0.05f);
    }

    private static final Map<MothVariant, Identifier> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(MothVariant.class), map -> {
        map.put(MothVariant.BASIC, Phototaxis.id("textures/entity/basic_mof.png"));
        map.put(MothVariant.DUSTY, Phototaxis.id("textures/entity/dusty_mof.png"));
    });
    @Override
    public Identifier getTexture(MothEntity entity) {
        return Phototaxis.id("textures/entity/basic_mof.png"); // textures/entity/basic_mof.png);
    }
}
