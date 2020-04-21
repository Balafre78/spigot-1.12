/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityPainting
/*     */   extends EntityHanging {
/*     */   public EnumArt art;
/*     */   
/*     */   public EntityPainting(World world) {
/*  13 */     super(world);
/*  14 */     this.art = EnumArt.values()[this.random.nextInt((EnumArt.values()).length)];
/*     */   }
/*     */   
/*     */   public EntityPainting(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  18 */     super(world, blockposition);
/*  19 */     ArrayList<EnumArt> arraylist = Lists.newArrayList();
/*  20 */     int i = 0;
/*  21 */     EnumArt[] aentitypainting_enumart = EnumArt.values();
/*  22 */     int j = aentitypainting_enumart.length;
/*     */     
/*  24 */     for (int k = 0; k < j; k++) {
/*  25 */       EnumArt entitypainting_enumart = aentitypainting_enumart[k];
/*     */       
/*  27 */       this.art = entitypainting_enumart;
/*  28 */       setDirection(enumdirection);
/*  29 */       if (survives()) {
/*  30 */         arraylist.add(entitypainting_enumart);
/*  31 */         int l = entitypainting_enumart.C * entitypainting_enumart.D;
/*     */         
/*  33 */         if (l > i) {
/*  34 */           i = l;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  39 */     if (!arraylist.isEmpty()) {
/*  40 */       Iterator<EnumArt> iterator = arraylist.iterator();
/*     */       
/*  42 */       while (iterator.hasNext()) {
/*  43 */         EnumArt entitypainting_enumart1 = iterator.next();
/*     */         
/*  45 */         if (entitypainting_enumart1.C * entitypainting_enumart1.D < i) {
/*  46 */           iterator.remove();
/*     */         }
/*     */       } 
/*     */       
/*  50 */       this.art = arraylist.get(this.random.nextInt(arraylist.size()));
/*     */     } 
/*     */     
/*  53 */     setDirection(enumdirection);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  57 */     nbttagcompound.setString("Motive", this.art.B);
/*  58 */     super.b(nbttagcompound);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  62 */     String s = nbttagcompound.getString("Motive");
/*  63 */     EnumArt[] aentitypainting_enumart = EnumArt.values();
/*  64 */     int i = aentitypainting_enumart.length;
/*     */     
/*  66 */     for (int j = 0; j < i; j++) {
/*  67 */       EnumArt entitypainting_enumart = aentitypainting_enumart[j];
/*     */       
/*  69 */       if (entitypainting_enumart.B.equals(s)) {
/*  70 */         this.art = entitypainting_enumart;
/*     */       }
/*     */     } 
/*     */     
/*  74 */     if (this.art == null) {
/*  75 */       this.art = EnumArt.KEBAB;
/*     */     }
/*     */     
/*  78 */     super.a(nbttagcompound);
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  82 */     return this.art.C;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  86 */     return this.art.D;
/*     */   }
/*     */   
/*     */   public void a(@Nullable Entity entity) {
/*  90 */     if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/*  91 */       a(SoundEffects.eF, 1.0F, 1.0F);
/*  92 */       if (entity instanceof EntityHuman) {
/*  93 */         EntityHuman entityhuman = (EntityHuman)entity;
/*     */         
/*  95 */         if (entityhuman.abilities.canInstantlyBuild) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 100 */       a(new ItemStack(Items.PAINTING), 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void p() {
/* 105 */     a(SoundEffects.eG, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
/* 109 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public enum EnumArt
/*     */   {
/* 114 */     KEBAB("Kebab", 16, 16, 0, 0), AZTEC("Aztec", 16, 16, 16, 0), ALBAN("Alban", 16, 16, 32, 0), AZTEC_2("Aztec2", 16, 16, 48, 0), BOMB("Bomb", 16, 16, 64, 0), PLANT("Plant", 16, 16, 80, 0), WASTELAND("Wasteland", 16, 16, 96, 0), POOL("Pool", 32, 16, 0, 32), COURBET("Courbet", 32, 16, 32, 32), SEA("Sea", 32, 16, 64, 32), SUNSET("Sunset", 32, 16, 96, 32), CREEBET("Creebet", 32, 16, 128, 32), WANDERER("Wanderer", 16, 32, 0, 64), GRAHAM("Graham", 16, 32, 16, 64), MATCH("Match", 32, 32, 0, 128), BUST("Bust", 32, 32, 32, 128), STAGE("Stage", 32, 32, 64, 128), VOID("Void", 32, 32, 96, 128), SKULL_AND_ROSES("SkullAndRoses", 32, 32, 128, 128), WITHER("Wither", 32, 32, 160, 128), FIGHTERS("Fighters", 64, 32, 0, 96), POINTER("Pointer", 64, 64, 0, 192), PIGSCENE("Pigscene", 64, 64, 64, 192), BURNING_SKULL("BurningSkull", 64, 64, 128, 192), SKELETON("Skeleton", 64, 48, 192, 64), DONKEY_KONG("DonkeyKong", 64, 48, 192, 112);
/*     */     
/* 116 */     public static final int A = "SkullAndRoses".length();
/*     */     
/*     */     public final String B;
/*     */     
/*     */     public final int C;
/*     */     public final int D;
/*     */     
/*     */     EnumArt(String s, int i, int j, int k, int l) {
/* 124 */       this.B = s;
/* 125 */       this.C = i;
/* 126 */       this.D = j;
/* 127 */       this.E = k;
/* 128 */       this.F = l;
/*     */     }
/*     */     
/*     */     public final int E;
/*     */     public final int F;
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */