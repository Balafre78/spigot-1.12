/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemFish
/*     */   extends ItemFood
/*     */ {
/*     */   private final boolean b;
/*     */   
/*     */   public ItemFish(boolean paramBoolean) {
/*  18 */     super(0, 0.0F, false);
/*     */     
/*  20 */     this.b = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNutrition(ItemStack paramItemStack) {
/*  25 */     EnumFish enumFish = EnumFish.a(paramItemStack);
/*     */     
/*  27 */     if (this.b && enumFish.g()) {
/*  28 */       return enumFish.e();
/*     */     }
/*  30 */     return enumFish.c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSaturationModifier(ItemStack paramItemStack) {
/*  36 */     EnumFish enumFish = EnumFish.a(paramItemStack);
/*     */     
/*  38 */     if (this.b && enumFish.g()) {
/*  39 */       return enumFish.f();
/*     */     }
/*  41 */     return enumFish.d();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
/*  47 */     EnumFish enumFish = EnumFish.a(paramItemStack);
/*     */     
/*  49 */     if (enumFish == EnumFish.PUFFERFISH) {
/*  50 */       paramEntityHuman.addEffect(new MobEffect(MobEffects.POISON, 1200, 3));
/*  51 */       paramEntityHuman.addEffect(new MobEffect(MobEffects.HUNGER, 300, 2));
/*  52 */       paramEntityHuman.addEffect(new MobEffect(MobEffects.CONFUSION, 300, 1));
/*     */     } 
/*     */     
/*  55 */     super.a(paramItemStack, paramWorld, paramEntityHuman);
/*     */   }
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
/*     */   public String a(ItemStack paramItemStack) {
/*  71 */     EnumFish enumFish = EnumFish.a(paramItemStack);
/*  72 */     return getName() + "." + enumFish.b() + "." + ((this.b && enumFish.g()) ? "cooked" : "raw");
/*     */   }
/*     */   
/*     */   public enum EnumFish {
/*  76 */     COD(0, "cod", 2, 0.1F, 5, 0.6F),
/*  77 */     SALMON(1, "salmon", 2, 0.1F, 6, 0.8F),
/*  78 */     CLOWNFISH(2, "clownfish", 1, 0.1F),
/*  79 */     PUFFERFISH(3, "pufferfish", 1, 0.1F);
/*     */     
/*  81 */     private static final Map<Integer, EnumFish> e = Maps.newHashMap();
/*     */     private final int f;
/*     */     private final String g;
/*     */     private final int h;
/*     */     private final float i;
/*     */     private final int j;
/*     */     private final float k;
/*     */     private final boolean l;
/*     */     
/*     */     static {
/*  91 */       for (EnumFish enumFish : values()) {
/*  92 */         e.put(Integer.valueOf(enumFish.a()), enumFish);
/*     */       }
/*     */     }
/*     */     
/*     */     EnumFish(int param1Int1, String param1String1, int param1Int2, float param1Float1, int param1Int3, float param1Float2) {
/*  97 */       this.f = param1Int1;
/*  98 */       this.g = param1String1;
/*  99 */       this.h = param1Int2;
/* 100 */       this.i = param1Float1;
/* 101 */       this.j = param1Int3;
/* 102 */       this.k = param1Float2;
/* 103 */       this.l = true;
/*     */     }
/*     */     
/*     */     EnumFish(int param1Int1, String param1String1, int param1Int2, float param1Float) {
/* 107 */       this.f = param1Int1;
/* 108 */       this.g = param1String1;
/* 109 */       this.h = param1Int2;
/* 110 */       this.i = param1Float;
/* 111 */       this.j = 0;
/* 112 */       this.k = 0.0F;
/* 113 */       this.l = false;
/*     */     }
/*     */     
/*     */     public int a() {
/* 117 */       return this.f;
/*     */     }
/*     */     
/*     */     public String b() {
/* 121 */       return this.g;
/*     */     }
/*     */     
/*     */     public int c() {
/* 125 */       return this.h;
/*     */     }
/*     */     
/*     */     public float d() {
/* 129 */       return this.i;
/*     */     }
/*     */     
/*     */     public int e() {
/* 133 */       return this.j;
/*     */     }
/*     */     
/*     */     public float f() {
/* 137 */       return this.k;
/*     */     }
/*     */     
/*     */     public boolean g() {
/* 141 */       return this.l;
/*     */     }
/*     */     
/*     */     public static EnumFish a(int param1Int) {
/* 145 */       EnumFish enumFish = e.get(Integer.valueOf(param1Int));
/*     */       
/* 147 */       if (enumFish == null) {
/* 148 */         return COD;
/*     */       }
/* 150 */       return enumFish;
/*     */     }
/*     */ 
/*     */     
/*     */     public static EnumFish a(ItemStack param1ItemStack) {
/* 155 */       if (param1ItemStack.getItem() instanceof ItemFish) {
/* 156 */         return a(param1ItemStack.getData());
/*     */       }
/* 158 */       return COD;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */