/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityGiantZombie
/*    */   extends EntityMonster
/*    */ {
/*    */   public EntityGiantZombie(World paramWorld) {
/* 16 */     super(paramWorld);
/* 17 */     setSize(this.width * 6.0F, this.length * 6.0F);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 21 */     EntityInsentient.a(paramDataConverterManager, EntityGiantZombie.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHeadHeight() {
/* 26 */     return 10.440001F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initAttributes() {
/* 31 */     super.initAttributes();
/*    */     
/* 33 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(100.0D);
/* 34 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
/* 35 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(50.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(BlockPosition paramBlockPosition) {
/* 40 */     return this.world.n(paramBlockPosition) - 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 46 */     return LootTables.u;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityGiantZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */