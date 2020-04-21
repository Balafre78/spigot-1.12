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
/*     */ public class ItemFood
/*     */   extends Item
/*     */ {
/*  18 */   public final int a = 32;
/*     */   
/*     */   private final int b;
/*     */   
/*     */   private final float c;
/*     */   private final boolean d;
/*     */   private boolean e;
/*     */   private MobEffect f;
/*     */   private float n;
/*     */   
/*     */   public ItemFood(int paramInt, float paramFloat, boolean paramBoolean) {
/*  29 */     this.b = paramInt;
/*  30 */     this.d = paramBoolean;
/*  31 */     this.c = paramFloat;
/*  32 */     b(CreativeModeTab.h);
/*     */   }
/*     */   
/*     */   public ItemFood(int paramInt, boolean paramBoolean) {
/*  36 */     this(paramInt, 0.6F, paramBoolean);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityLiving paramEntityLiving) {
/*  41 */     if (paramEntityLiving instanceof EntityHuman) {
/*  42 */       EntityHuman entityHuman = (EntityHuman)paramEntityLiving;
/*  43 */       entityHuman.getFoodData().a(this, paramItemStack);
/*  44 */       paramWorld.a((EntityHuman)null, entityHuman.locX, entityHuman.locY, entityHuman.locZ, SoundEffects.fD, SoundCategory.PLAYERS, 0.5F, paramWorld.random.nextFloat() * 0.1F + 0.9F);
/*     */       
/*  46 */       a(paramItemStack, paramWorld, entityHuman);
/*  47 */       entityHuman.b(StatisticList.b(this));
/*     */       
/*  49 */       if (entityHuman instanceof EntityPlayer) {
/*  50 */         CriterionTriggers.y.a((EntityPlayer)entityHuman, paramItemStack);
/*     */       }
/*     */     } 
/*     */     
/*  54 */     paramItemStack.subtract(1);
/*     */     
/*  56 */     return paramItemStack;
/*     */   }
/*     */   
/*     */   protected void a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
/*  60 */     if (!paramWorld.isClientSide && this.f != null && paramWorld.random.nextFloat() < this.n) {
/*  61 */       paramEntityHuman.addEffect(new MobEffect(this.f));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int e(ItemStack paramItemStack) {
/*  67 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAnimation f(ItemStack paramItemStack) {
/*  72 */     return EnumAnimation.EAT;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/*  77 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  78 */     if (paramEntityHuman.n(this.e)) {
/*  79 */       paramEntityHuman.c(paramEnumHand);
/*  80 */       return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*     */     } 
/*  82 */     return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemStack);
/*     */   }
/*     */   
/*     */   public int getNutrition(ItemStack paramItemStack) {
/*  86 */     return this.b;
/*     */   }
/*     */   
/*     */   public float getSaturationModifier(ItemStack paramItemStack) {
/*  90 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean g() {
/*  94 */     return this.d;
/*     */   }
/*     */   
/*     */   public ItemFood a(MobEffect paramMobEffect, float paramFloat) {
/*  98 */     this.f = paramMobEffect;
/*  99 */     this.n = paramFloat;
/* 100 */     return this;
/*     */   }
/*     */   
/*     */   public ItemFood h() {
/* 104 */     this.e = true;
/* 105 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */