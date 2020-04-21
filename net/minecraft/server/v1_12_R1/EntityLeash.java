/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityLeash
/*     */   extends EntityHanging
/*     */ {
/*     */   public EntityLeash(World world) {
/*  12 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityLeash(World world, BlockPosition blockposition) {
/*  16 */     super(world, blockposition);
/*  17 */     setPosition(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  22 */     a(new AxisAlignedBB(this.locX - 0.1875D, this.locY - 0.25D + 0.125D, this.locZ - 0.1875D, this.locX + 0.1875D, this.locY + 0.25D + 0.125D, this.locZ + 0.1875D));
/*  23 */     this.attachedToPlayer = true;
/*     */   }
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/*  27 */     super.setPosition(MathHelper.floor(d0) + 0.5D, MathHelper.floor(d1) + 0.5D, MathHelper.floor(d2) + 0.5D);
/*     */   }
/*     */   
/*     */   protected void updateBoundingBox() {
/*  31 */     this.locX = this.blockPosition.getX() + 0.5D;
/*  32 */     this.locY = this.blockPosition.getY() + 0.5D;
/*  33 */     this.locZ = this.blockPosition.getZ() + 0.5D;
/*     */   }
/*     */   
/*     */   public void setDirection(EnumDirection enumdirection) {}
/*     */   
/*     */   public int getWidth() {
/*  39 */     return 9;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  43 */     return 9;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/*  47 */     return -0.0625F;
/*     */   }
/*     */   
/*     */   public void a(@Nullable Entity entity) {
/*  51 */     a(SoundEffects.dG, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean d(NBTTagCompound nbttagcompound) {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */   public boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/*  63 */     if (this.world.isClientSide) {
/*  64 */       return true;
/*     */     }
/*  66 */     boolean flag = false;
/*     */     
/*  68 */     List<EntityInsentient> list = this.world.a(EntityInsentient.class, new AxisAlignedBB(this.locX - 7.0D, this.locY - 7.0D, this.locZ - 7.0D, this.locX + 7.0D, this.locY + 7.0D, this.locZ + 7.0D));
/*  69 */     Iterator<EntityInsentient> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */     
/*  73 */     while (iterator.hasNext()) {
/*  74 */       EntityInsentient entityinsentient = iterator.next();
/*  75 */       if (entityinsentient.isLeashed() && entityinsentient.getLeashHolder() == entityhuman) {
/*     */         
/*  77 */         if (CraftEventFactory.callPlayerLeashEntityEvent(entityinsentient, this, entityhuman).isCancelled()) {
/*  78 */           ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutAttachEntity(entityinsentient, entityinsentient.getLeashHolder()));
/*     */           
/*     */           continue;
/*     */         } 
/*  82 */         entityinsentient.setLeashHolder(this, true);
/*  83 */         flag = true;
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     if (!flag) {
/*     */ 
/*     */       
/*  90 */       boolean die = true;
/*     */ 
/*     */       
/*  93 */       iterator = list.iterator();
/*     */       
/*  95 */       while (iterator.hasNext()) {
/*  96 */         EntityInsentient entityinsentient = iterator.next();
/*  97 */         if (entityinsentient.isLeashed() && entityinsentient.getLeashHolder() == this) {
/*     */           
/*  99 */           if (CraftEventFactory.callPlayerUnleashEntityEvent(entityinsentient, entityhuman).isCancelled()) {
/* 100 */             die = false;
/*     */             continue;
/*     */           } 
/* 103 */           entityinsentient.unleash(true, !entityhuman.abilities.canInstantlyBuild);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 108 */       if (die) {
/* 109 */         die();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean survives() {
/* 120 */     return this.world.getType(this.blockPosition).getBlock() instanceof BlockFence;
/*     */   }
/*     */   
/*     */   public static EntityLeash a(World world, BlockPosition blockposition) {
/* 124 */     EntityLeash entityleash = new EntityLeash(world, blockposition);
/*     */     
/* 126 */     world.addEntity(entityleash);
/* 127 */     entityleash.p();
/* 128 */     return entityleash;
/*     */   }
/*     */   @Nullable
/*     */   public static EntityLeash b(World world, BlockPosition blockposition) {
/*     */     EntityLeash entityleash;
/* 133 */     int i = blockposition.getX();
/* 134 */     int j = blockposition.getY();
/* 135 */     int k = blockposition.getZ();
/* 136 */     List<EntityLeash> list = world.a(EntityLeash.class, new AxisAlignedBB(i - 1.0D, j - 1.0D, k - 1.0D, i + 1.0D, j + 1.0D, k + 1.0D));
/* 137 */     Iterator<EntityLeash> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 142 */       if (!iterator.hasNext()) {
/* 143 */         return null;
/*     */       }
/*     */       
/* 146 */       entityleash = iterator.next();
/* 147 */     } while (!entityleash.getBlockPosition().equals(blockposition));
/*     */     
/* 149 */     return entityleash;
/*     */   }
/*     */   
/*     */   public void p() {
/* 153 */     a(SoundEffects.dH, 1.0F, 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityLeash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */