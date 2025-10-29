package silly.chemthunder.phototaxis.object.entity.moths.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import silly.chemthunder.phototaxis.Phototaxis;
import silly.chemthunder.phototaxis.index.PhototaxisEntities;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;
import silly.chemthunder.phototaxis.object.entity.moths.MothVariant;

import java.util.Map;

public class MothEntityRenderer extends MobEntityRenderer<MothEntity, MothModelEntity<MothEntity>> {
    public MothEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MothModelEntity<>(context.getPart(MothModelEntity.MOTH_MODEL)), 0.5f);
    }


    @Override
    public Identifier getTexture(MothEntity entity) {
        if (entity.getType() == PhototaxisEntities.MOTH) {
            return Phototaxis.id("textures/entity/basic_mof.png");
        }
        if (entity.getType() == PhototaxisEntities.DUSTY_MOTH) {
            return Phototaxis.id("textures/entity/dusty_mof.png");
        }
        if (entity.getType() == PhototaxisEntities.MOTH_REDHEAD) {
            return Phototaxis.id("textures/entity/redhead_mof.png");
        }
        if (entity.getType() == PhototaxisEntities.MOTH_SATIN) {
            return Phototaxis.id("textures/entity/ivory_mof.png");
        }
        return getTexture(entity);
    }
}
