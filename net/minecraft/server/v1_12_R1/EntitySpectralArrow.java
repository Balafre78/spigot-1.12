/*    */ package net.minecraft.server.v1_12_R1;
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
/*    */ public class EntitySpectralArrow
/*    */   extends EntityArrow
/*    */ {
/* 15 */   public int duration = 200;
/*    */   
/*    */   public EntitySpectralArrow(World paramWorld) {
/* 18 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public EntitySpectralArrow(World paramWorld, EntityLiving paramEntityLiving) {
/* 22 */     super(paramWorld, paramEntityLiving);
/*    */   }
/*    */   
/*    */   public EntitySpectralArrow(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 26 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void B_() {
/* 31 */     super.B_();
/*    */     
/* 33 */     if (this.world.isClientSide && !this.inGround) {
/* 34 */       this.world.addParticle(EnumParticle.SPELL_INSTANT, this.locX, this.locY, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack j() {
/* 40 */     return new ItemStack(Items.SPECTRAL_ARROW);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(EntityLiving paramEntityLiving) {
/* 45 */     super.a(paramEntityLiving);
/*    */     
/* 47 */     MobEffect mobEffect = new MobEffect(MobEffects.GLOWING, this.duration, 0);
/* 48 */     paramEntityLiving.addEffect(mobEffect);
/*    */   }
/*    */   
/*    */   public static void c(DataConverterManager paramDataConverterManager) {
/* 52 */     EntityArrow.a(paramDataConverterManager, "SpectralArrow");
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 57 */     super.a(paramNBTTagCompound);
/* 58 */     if (paramNBTTagCompound.hasKey("Duration")) {
/* 59 */       this.duration = paramNBTTagCompound.getInt("Duration");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(NBTTagCompound paramNBTTagCompound) {
/* 65 */     super.b(paramNBTTagCompound);
/* 66 */     paramNBTTagCompound.setInt("Duration", this.duration);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySpectralArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */