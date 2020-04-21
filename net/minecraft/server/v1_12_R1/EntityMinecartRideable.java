/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityMinecartRideable
/*    */   extends EntityMinecartAbstract
/*    */ {
/*    */   public EntityMinecartRideable(World paramWorld) {
/* 10 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public EntityMinecartRideable(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 14 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 18 */     EntityMinecartAbstract.a(paramDataConverterManager, EntityMinecartRideable.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 23 */     if (paramEntityHuman.isSneaking()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     if (isVehicle()) {
/* 28 */       return true;
/*    */     }
/* 30 */     if (!this.world.isClientSide) {
/* 31 */       paramEntityHuman.startRiding(this);
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/* 39 */     if (paramBoolean) {
/* 40 */       if (isVehicle()) {
/* 41 */         ejectPassengers();
/*    */       }
/* 43 */       if (getType() == 0) {
/* 44 */         e(-u());
/* 45 */         d(10);
/* 46 */         setDamage(50.0F);
/* 47 */         ax();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartAbstract.EnumMinecartType v() {
/* 54 */     return EntityMinecartAbstract.EnumMinecartType.RIDEABLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartRideable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */