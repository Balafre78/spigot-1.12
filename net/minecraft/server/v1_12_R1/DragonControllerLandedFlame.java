/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DragonControllerLandedFlame
/*     */   extends AbstractDragonControllerLanded
/*     */ {
/*     */   private int b;
/*     */   private int c;
/*     */   private EntityAreaEffectCloud d;
/*     */   
/*     */   public DragonControllerLandedFlame(EntityEnderDragon paramEntityEnderDragon) {
/*  23 */     super(paramEntityEnderDragon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/*  28 */     this.b++;
/*     */     
/*  30 */     if (this.b % 2 == 0 && this.b < 10) {
/*  31 */       Vec3D vec3D = this.a.a(1.0F).a();
/*  32 */       vec3D.b(-0.7853982F);
/*  33 */       double d1 = this.a.bw.locX;
/*  34 */       double d2 = this.a.bw.locY + (this.a.bw.length / 2.0F);
/*  35 */       double d3 = this.a.bw.locZ;
/*  36 */       for (byte b = 0; b < 8; b++) {
/*  37 */         double d4 = d1 + this.a.getRandom().nextGaussian() / 2.0D;
/*  38 */         double d5 = d2 + this.a.getRandom().nextGaussian() / 2.0D;
/*  39 */         double d6 = d3 + this.a.getRandom().nextGaussian() / 2.0D;
/*  40 */         for (byte b1 = 0; b1 < 6; b1++) {
/*  41 */           this.a.world.addParticle(EnumParticle.DRAGON_BREATH, d4, d5, d6, -vec3D.x * 0.07999999821186066D * b1, -vec3D.y * 0.6000000238418579D, -vec3D.z * 0.07999999821186066D * b1, new int[0]);
/*     */         }
/*  43 */         vec3D.b(0.19634955F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  50 */     this.b++;
/*     */     
/*  52 */     if (this.b >= 200) {
/*  53 */       if (this.c >= 4) {
/*  54 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.e);
/*     */       } else {
/*  56 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.g);
/*     */       } 
/*  58 */     } else if (this.b == 10) {
/*  59 */       Vec3D vec3D = (new Vec3D(this.a.bw.locX - this.a.locX, 0.0D, this.a.bw.locZ - this.a.locZ)).a();
/*  60 */       float f = 5.0F;
/*  61 */       double d1 = this.a.bw.locX + vec3D.x * 5.0D / 2.0D;
/*  62 */       double d2 = this.a.bw.locZ + vec3D.z * 5.0D / 2.0D;
/*  63 */       double d3 = this.a.bw.locY + (this.a.bw.length / 2.0F);
/*     */       
/*  65 */       BlockPosition.MutableBlockPosition mutableBlockPosition = new BlockPosition.MutableBlockPosition(MathHelper.floor(d1), MathHelper.floor(d3), MathHelper.floor(d2));
/*  66 */       while (this.a.world.isEmpty(mutableBlockPosition)) {
/*  67 */         d3--;
/*  68 */         mutableBlockPosition.c(MathHelper.floor(d1), MathHelper.floor(d3), MathHelper.floor(d2));
/*     */       } 
/*  70 */       d3 = (MathHelper.floor(d3) + 1);
/*  71 */       this.d = new EntityAreaEffectCloud(this.a.world, d1, d3, d2);
/*  72 */       this.d.setSource(this.a);
/*  73 */       this.d.setRadius(5.0F);
/*  74 */       this.d.setDuration(200);
/*  75 */       this.d.setParticle(EnumParticle.DRAGON_BREATH);
/*  76 */       this.d.a(new MobEffect(MobEffects.HARM));
/*  77 */       this.a.world.addEntity(this.d);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  83 */     this.b = 0;
/*  84 */     this.c++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  89 */     if (this.d != null) {
/*  90 */       this.d.die();
/*  91 */       this.d = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DragonControllerPhase<DragonControllerLandedFlame> getControllerPhase() {
/*  97 */     return DragonControllerPhase.f;
/*     */   }
/*     */   
/*     */   public void j() {
/* 101 */     this.c = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DragonControllerLandedFlame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */