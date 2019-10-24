package com.hbm.entity.projectile;

import com.hbm.lib.ModDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityShrapnel extends EntityThrowable {
	
	public static final DataParameter<Byte> TRAIL = EntityDataManager.<Byte>createKey(EntityShrapnel.class, DataSerializers.BYTE);

    public EntityShrapnel(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityShrapnel(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    @Override
	public void entityInit() {
        this.dataManager.register(TRAIL, Byte.valueOf((byte)0));
    }

    public EntityShrapnel(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	if(!world.isRemote)
    		if(this.dataManager.get(TRAIL) == (byte)1) {
    			world.spawnEntity(new EntitySSmokeFX(world, this.posX, this.posY - 0.5, this.posZ, 0.0, 0.0, 0.0));
    			world.spawnEntity(new EntitySSmokeFX(world, this.posX - this.motionX, this.posY - 0.5 - this.motionY, this.posZ - this.motionZ, 0.0, 0.0, 0.0));
    		}
    }

    @Override
	protected void onImpact(RayTraceResult p_70184_1_)
    {
        if (p_70184_1_.entityHit != null)
        {
            byte b0 = 15;

            p_70184_1_.entityHit.attackEntityFrom(ModDamageSource.shrapnel, b0);
        }

        if(this.ticksExisted > 5) {
        	this.setDead();
        	if(!this.world.isRemote)
        		world.createExplosion(this, this.posX, this.posY, this.posZ, 0.1F, true);
        }
    }
    
    public void setTrail(boolean b) {
        	this.dataManager.set(TRAIL, (byte)(b ? 1 : 0));
    }
}