package com.autodidax.demoncycle.entity.render;

import com.autodidax.demoncycle.entity.EntitySandDemon;
import com.autodidax.demoncycle.entity.model.ModelSandDemon;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import scala.tools.nsc.backend.icode.TypeKinds.REFERENCE;

public class RenderSandDemon extends RenderLiving<EntitySandDemon> {

	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/sanddemon.png");
	
	public RenderSandDemon(RenderManager manager) {
		super(manager, new ModelSandDemon(), 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySandDemon entity) {
		return TEXTURES;
	}	
}
