package silly.chemthunder.phototaxis.object.entity.moths.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import silly.chemthunder.phototaxis.Phototaxis;
import silly.chemthunder.phototaxis.object.entity.moths.MothEntity;

// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MothModelEntity<T extends MothEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftantenna;
	private final ModelPart rightantenna;
	public MothModelEntity(ModelPart root) {
		this.body = root.getChild("body");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.leftantenna = this.body.getChild("leftantenna");
		this.rightantenna = this.body.getChild("rightantenna");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 12).cuboid(-2.0F, -2.1667F, -3.9167F, 4.0F, 3.0F, 9.0F, new Dilation(0.0F))
		.uv(0, 20).cuboid(-2.0F, 0.8333F, -1.4167F, 4.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 18).cuboid(-2.0F, 0.8333F, 0.5833F, 4.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 19).cuboid(-2.0F, 0.8333F, 2.5833F, 4.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.1667F, -0.0833F));

		ModelPartData leftwing = body.addChild("leftwing", ModelPartBuilder.create().uv(-12, 0).cuboid(0.0F, 0.0F, -5.0F, 8.0F, 0.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -1.6667F, -0.4167F));

		ModelPartData rightwing = body.addChild("rightwing", ModelPartBuilder.create().uv(-12, 0).mirrored().cuboid(-8.0F, 0.0F, -5.0F, 8.0F, 0.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-2.0F, -1.6667F, -0.4167F));

		ModelPartData leftantenna = body.addChild("leftantenna", ModelPartBuilder.create().uv(-5, 12).cuboid(-0.5F, 0.0F, -5.0F, 2.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, -0.6667F, -3.9167F));

		ModelPartData rightantenna = body.addChild("rightantenna", ModelPartBuilder.create().uv(-5, 12).mirrored().cuboid(-1.5F, 0.0F, -5.0F, 2.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-1.5F, -0.6667F, -3.9167F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(MothEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        body.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }

    public static final EntityModelLayer MOTH_MODEL = new EntityModelLayer(Phototaxis.id("moth"), "main");
}