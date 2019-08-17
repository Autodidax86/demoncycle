package com.autodidax.demoncycle.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelVex - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelSandDemon extends ModelBase {
    public ModelRenderer sandDemonRightArm;
    public ModelRenderer sandDemonRightLeg;
    public ModelRenderer sandDemonLeftArm;
    public ModelRenderer sandDemonBody;
    public ModelRenderer sandDemonHead;
    public ModelRenderer sandDemonRightArm_1;
    public ModelRenderer sandDemonRightLeg_1;
    public ModelRenderer sandDemonLeftArm_1;
    public ModelRenderer sandDemonBody_1;
    public ModelRenderer sandDemonHead_1;

    public ModelSandDemon() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.sandDemonRightLeg = new ModelRenderer(this, 16, 0);
        this.sandDemonRightLeg.setRotationPoint(-1.9F, 12.0F, 0.1F);
        this.sandDemonRightLeg.addBox(-1.0F, -1.0F, -2.0F, 6, 10, 4, 0.0F);
        this.setRotateAngle(sandDemonRightLeg, 0.6283185307179586F, 0.0F, 0.0F);
        this.sandDemonBody = new ModelRenderer(this, 12, 14);
        this.sandDemonBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sandDemonBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.sandDemonHead = new ModelRenderer(this, 28, 22);
        this.sandDemonHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sandDemonHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(sandDemonHead, 0.003490658503988659F, 0.0F, 0.0F);
        this.sandDemonRightLeg_1 = new ModelRenderer(this, 16, 0);
        this.sandDemonRightLeg_1.setRotationPoint(-1.9F, 12.0F, 0.1F);
        this.sandDemonRightLeg_1.addBox(-1.0F, -1.0F, -2.0F, 6, 10, 4, 0.0F);
        this.setRotateAngle(sandDemonRightLeg_1, 0.6283185307179586F, 0.0F, 0.0F);
        this.sandDemonBody_1 = new ModelRenderer(this, 12, 14);
        this.sandDemonBody_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sandDemonBody_1.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.sandDemonRightArm = new ModelRenderer(this, 0, 0);
        this.sandDemonRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.sandDemonRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(sandDemonRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.sandDemonRightArm_1 = new ModelRenderer(this, 0, 0);
        this.sandDemonRightArm_1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.sandDemonRightArm_1.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(sandDemonRightArm_1, 0.0F, 0.0F, 0.10000736613927509F);
        this.sandDemonHead_1 = new ModelRenderer(this, 28, 22);
        this.sandDemonHead_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.sandDemonHead_1.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.sandDemonLeftArm = new ModelRenderer(this, 36, 0);
        this.sandDemonLeftArm.mirror = true;
        this.sandDemonLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.sandDemonLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(sandDemonLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.sandDemonLeftArm_1 = new ModelRenderer(this, 36, 0);
        this.sandDemonLeftArm_1.mirror = true;
        this.sandDemonLeftArm_1.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.sandDemonLeftArm_1.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(sandDemonLeftArm_1, 0.0F, 0.0F, -0.10000736613927509F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.sandDemonRightLeg.render(f5);
        this.sandDemonBody.render(f5);
        this.sandDemonHead.render(f5);
        this.sandDemonRightLeg_1.render(f5);
        this.sandDemonBody_1.render(f5);
        this.sandDemonRightArm.render(f5);
        this.sandDemonRightArm_1.render(f5);
        this.sandDemonHead_1.render(f5);
        this.sandDemonLeftArm.render(f5);
        this.sandDemonLeftArm_1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
