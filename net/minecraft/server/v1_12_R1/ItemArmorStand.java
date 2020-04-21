/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemArmorStand
/*    */   extends Item
/*    */ {
/*    */   public ItemArmorStand() {
/* 22 */     b(CreativeModeTab.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 27 */     if (paramEnumDirection == EnumDirection.DOWN) {
/* 28 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 31 */     boolean bool = paramWorld.getType(paramBlockPosition).getBlock().a(paramWorld, paramBlockPosition);
/* 32 */     BlockPosition blockPosition1 = bool ? paramBlockPosition : paramBlockPosition.shift(paramEnumDirection);
/* 33 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 34 */     if (!paramEntityHuman.a(blockPosition1, paramEnumDirection, itemStack)) {
/* 35 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 38 */     BlockPosition blockPosition2 = blockPosition1.up();
/* 39 */     int i = (!paramWorld.isEmpty(blockPosition1) && !paramWorld.getType(blockPosition1).getBlock().a(paramWorld, blockPosition1)) ? 1 : 0;
/* 40 */     i |= (!paramWorld.isEmpty(blockPosition2) && !paramWorld.getType(blockPosition2).getBlock().a(paramWorld, blockPosition2)) ? 1 : 0;
/* 41 */     if (i != 0) {
/* 42 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 45 */     double d1 = blockPosition1.getX();
/* 46 */     double d2 = blockPosition1.getY();
/* 47 */     double d3 = blockPosition1.getZ();
/*    */     
/* 49 */     List<Entity> list = paramWorld.getEntities(null, new AxisAlignedBB(d1, d2, d3, d1 + 1.0D, d2 + 2.0D, d3 + 1.0D));
/* 50 */     if (!list.isEmpty()) {
/* 51 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 54 */     if (!paramWorld.isClientSide) {
/* 55 */       paramWorld.setAir(blockPosition1);
/* 56 */       paramWorld.setAir(blockPosition2);
/*    */       
/* 58 */       EntityArmorStand entityArmorStand = new EntityArmorStand(paramWorld, d1 + 0.5D, d2, d3 + 0.5D);
/* 59 */       float f = MathHelper.d((MathHelper.g(paramEntityHuman.yaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
/* 60 */       entityArmorStand.setPositionRotation(d1 + 0.5D, d2, d3 + 0.5D, f, 0.0F);
/* 61 */       a(entityArmorStand, paramWorld.random);
/* 62 */       ItemMonsterEgg.a(paramWorld, paramEntityHuman, itemStack, entityArmorStand);
/* 63 */       paramWorld.addEntity(entityArmorStand);
/*    */       
/* 65 */       paramWorld.a((EntityHuman)null, entityArmorStand.locX, entityArmorStand.locY, entityArmorStand.locZ, SoundEffects.m, SoundCategory.BLOCKS, 0.75F, 0.8F);
/*    */     } 
/*    */     
/* 68 */     itemStack.subtract(1);
/* 69 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void a(EntityArmorStand paramEntityArmorStand, Random paramRandom) {
/* 78 */     Vector3f vector3f1 = paramEntityArmorStand.u();
/* 79 */     float f1 = paramRandom.nextFloat() * 5.0F;
/* 80 */     float f2 = paramRandom.nextFloat() * 20.0F - 10.0F;
/* 81 */     Vector3f vector3f2 = new Vector3f(vector3f1.getX() + f1, vector3f1.getY() + f2, vector3f1.getZ());
/* 82 */     paramEntityArmorStand.setHeadPose(vector3f2);
/*    */     
/* 84 */     vector3f1 = paramEntityArmorStand.w();
/* 85 */     f1 = paramRandom.nextFloat() * 10.0F - 5.0F;
/* 86 */     vector3f2 = new Vector3f(vector3f1.getX(), vector3f1.getY() + f1, vector3f1.getZ());
/* 87 */     paramEntityArmorStand.setBodyPose(vector3f2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */