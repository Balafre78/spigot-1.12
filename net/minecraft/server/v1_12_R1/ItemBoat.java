/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class ItemBoat extends Item {
/*    */   public ItemBoat(EntityBoat.EnumBoatType entityboat_enumboattype) {
/* 10 */     this.a = entityboat_enumboattype;
/* 11 */     this.maxStackSize = 1;
/* 12 */     b(CreativeModeTab.e);
/* 13 */     c("boat." + entityboat_enumboattype.a());
/*    */   }
/*    */   private final EntityBoat.EnumBoatType a;
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 17 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */     
/* 19 */     float f1 = entityhuman.lastPitch + (entityhuman.pitch - entityhuman.lastPitch) * 1.0F;
/* 20 */     float f2 = entityhuman.lastYaw + (entityhuman.yaw - entityhuman.lastYaw) * 1.0F;
/* 21 */     double d0 = entityhuman.lastX + (entityhuman.locX - entityhuman.lastX) * 1.0D;
/* 22 */     double d1 = entityhuman.lastY + (entityhuman.locY - entityhuman.lastY) * 1.0D + entityhuman.getHeadHeight();
/* 23 */     double d2 = entityhuman.lastZ + (entityhuman.locZ - entityhuman.lastZ) * 1.0D;
/* 24 */     Vec3D vec3d = new Vec3D(d0, d1, d2);
/* 25 */     float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
/* 26 */     float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
/* 27 */     float f5 = -MathHelper.cos(-f1 * 0.017453292F);
/* 28 */     float f6 = MathHelper.sin(-f1 * 0.017453292F);
/* 29 */     float f7 = f4 * f5;
/* 30 */     float f8 = f3 * f5;
/*    */     
/* 32 */     Vec3D vec3d1 = vec3d.add(f7 * 5.0D, f6 * 5.0D, f8 * 5.0D);
/* 33 */     MovingObjectPosition movingobjectposition = world.rayTrace(vec3d, vec3d1, true);
/*    */     
/* 35 */     if (movingobjectposition == null) {
/* 36 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */     }
/* 38 */     Vec3D vec3d2 = entityhuman.e(1.0F);
/* 39 */     boolean flag = false;
/* 40 */     List<Entity> list = world.getEntities(entityhuman, entityhuman.getBoundingBox().b(vec3d2.x * 5.0D, vec3d2.y * 5.0D, vec3d2.z * 5.0D).g(1.0D));
/*    */     
/* 42 */     for (int i = 0; i < list.size(); i++) {
/* 43 */       Entity entity = list.get(i);
/*    */       
/* 45 */       if (entity.isInteractable()) {
/* 46 */         AxisAlignedBB axisalignedbb = entity.getBoundingBox().g(entity.aI());
/*    */         
/* 48 */         if (axisalignedbb.b(vec3d)) {
/* 49 */           flag = true;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     if (flag)
/* 55 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack); 
/* 56 */     if (movingobjectposition.type != MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 57 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */     }
/*    */     
/* 60 */     PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, movingobjectposition.a(), movingobjectposition.direction, itemstack, enumhand);
/*    */     
/* 62 */     if (event.isCancelled()) {
/* 63 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */     }
/*    */     
/* 66 */     Block block = world.getType(movingobjectposition.a()).getBlock();
/* 67 */     boolean flag1 = !(block != Blocks.WATER && block != Blocks.FLOWING_WATER);
/* 68 */     EntityBoat entityboat = new EntityBoat(world, movingobjectposition.pos.x, flag1 ? (movingobjectposition.pos.y - 0.12D) : movingobjectposition.pos.y, movingobjectposition.pos.z);
/*    */     
/* 70 */     entityboat.setType(this.a);
/* 71 */     entityboat.yaw = entityhuman.yaw;
/* 72 */     if (!world.getCubes(entityboat, entityboat.getBoundingBox().g(-0.1D)).isEmpty()) {
/* 73 */       return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */     }
/* 75 */     if (!world.isClientSide && 
/* 76 */       !world.addEntity(entityboat)) return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */ 
/*    */     
/* 79 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 80 */       itemstack.subtract(1);
/*    */     }
/*    */     
/* 83 */     entityhuman.b(StatisticList.b(this));
/* 84 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */