/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.ComparisonChain;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobEffect
/*     */   implements Comparable<MobEffect>
/*     */ {
/*  12 */   private static final Logger a = LogManager.getLogger();
/*     */   
/*     */   private final MobEffectList b;
/*     */   
/*     */   private int duration;
/*     */   private int amplification;
/*     */   private boolean splash;
/*     */   private boolean ambient;
/*     */   private boolean h;
/*     */   
/*     */   public MobEffect(MobEffectList paramMobEffectList) {
/*  23 */     this(paramMobEffectList, 0, 0);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList paramMobEffectList, int paramInt) {
/*  27 */     this(paramMobEffectList, paramInt, 0);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList paramMobEffectList, int paramInt1, int paramInt2) {
/*  31 */     this(paramMobEffectList, paramInt1, paramInt2, false, true);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList paramMobEffectList, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
/*  35 */     this.b = paramMobEffectList;
/*  36 */     this.duration = paramInt1;
/*  37 */     this.amplification = paramInt2;
/*  38 */     this.ambient = paramBoolean1;
/*  39 */     this.h = paramBoolean2;
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffect paramMobEffect) {
/*  43 */     this.b = paramMobEffect.b;
/*  44 */     this.duration = paramMobEffect.duration;
/*  45 */     this.amplification = paramMobEffect.amplification;
/*  46 */     this.ambient = paramMobEffect.ambient;
/*  47 */     this.h = paramMobEffect.h;
/*     */   }
/*     */   
/*     */   public void a(MobEffect paramMobEffect) {
/*  51 */     if (this.b != paramMobEffect.b) {
/*  52 */       a.warn("This method should only be called for matching effects!");
/*     */     }
/*  54 */     if (paramMobEffect.amplification > this.amplification) {
/*  55 */       this.amplification = paramMobEffect.amplification;
/*  56 */       this.duration = paramMobEffect.duration;
/*  57 */     } else if (paramMobEffect.amplification == this.amplification && this.duration < paramMobEffect.duration) {
/*  58 */       this.duration = paramMobEffect.duration;
/*  59 */     } else if (!paramMobEffect.ambient && this.ambient) {
/*  60 */       this.ambient = paramMobEffect.ambient;
/*     */     } 
/*  62 */     this.h = paramMobEffect.h;
/*     */   }
/*     */   
/*     */   public MobEffectList getMobEffect() {
/*  66 */     return this.b;
/*     */   }
/*     */   
/*     */   public int getDuration() {
/*  70 */     return this.duration;
/*     */   }
/*     */   
/*     */   public int getAmplifier() {
/*  74 */     return this.amplification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAmbient() {
/*  82 */     return this.ambient;
/*     */   }
/*     */   
/*     */   public boolean isShowParticles() {
/*  86 */     return this.h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tick(EntityLiving paramEntityLiving) {
/*  96 */     if (this.duration > 0) {
/*  97 */       if (this.b.a(this.duration, this.amplification)) {
/*  98 */         b(paramEntityLiving);
/*     */       }
/* 100 */       h();
/*     */     } 
/* 102 */     return (this.duration > 0);
/*     */   }
/*     */   
/*     */   private int h() {
/* 106 */     return --this.duration;
/*     */   }
/*     */   
/*     */   public void b(EntityLiving paramEntityLiving) {
/* 110 */     if (this.duration > 0) {
/* 111 */       this.b.tick(paramEntityLiving, this.amplification);
/*     */     }
/*     */   }
/*     */   
/*     */   public String f() {
/* 116 */     return this.b.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String str;
/* 122 */     if (this.amplification > 0) {
/* 123 */       str = f() + " x " + (this.amplification + 1) + ", Duration: " + this.duration;
/*     */     } else {
/* 125 */       str = f() + ", Duration: " + this.duration;
/*     */     } 
/* 127 */     if (this.splash) {
/* 128 */       str = str + ", Splash: true";
/*     */     }
/* 130 */     if (!this.h) {
/* 131 */       str = str + ", Particles: false";
/*     */     }
/* 133 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 138 */     if (this == paramObject) {
/* 139 */       return true;
/*     */     }
/*     */     
/* 142 */     if (paramObject instanceof MobEffect) {
/* 143 */       MobEffect mobEffect = (MobEffect)paramObject;
/*     */       
/* 145 */       return (this.duration == mobEffect.duration && this.amplification == mobEffect.amplification && this.splash == mobEffect.splash && this.ambient == mobEffect.ambient && this.b.equals(mobEffect.b));
/*     */     } 
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 152 */     int i = this.b.hashCode();
/* 153 */     i = 31 * i + this.duration;
/* 154 */     i = 31 * i + this.amplification;
/* 155 */     i = 31 * i + (this.splash ? 1 : 0);
/* 156 */     i = 31 * i + (this.ambient ? 1 : 0);
/* 157 */     return i;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 161 */     paramNBTTagCompound.setByte("Id", (byte)MobEffectList.getId(getMobEffect()));
/* 162 */     paramNBTTagCompound.setByte("Amplifier", (byte)getAmplifier());
/* 163 */     paramNBTTagCompound.setInt("Duration", getDuration());
/* 164 */     paramNBTTagCompound.setBoolean("Ambient", isAmbient());
/* 165 */     paramNBTTagCompound.setBoolean("ShowParticles", isShowParticles());
/* 166 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   public static MobEffect b(NBTTagCompound paramNBTTagCompound) {
/* 170 */     byte b1 = paramNBTTagCompound.getByte("Id");
/* 171 */     MobEffectList mobEffectList = MobEffectList.fromId(b1);
/* 172 */     if (mobEffectList == null) {
/* 173 */       return null;
/*     */     }
/* 175 */     byte b2 = paramNBTTagCompound.getByte("Amplifier");
/* 176 */     int i = paramNBTTagCompound.getInt("Duration");
/* 177 */     boolean bool1 = paramNBTTagCompound.getBoolean("Ambient");
/* 178 */     boolean bool2 = true;
/* 179 */     if (paramNBTTagCompound.hasKeyOfType("ShowParticles", 1)) {
/* 180 */       bool2 = paramNBTTagCompound.getBoolean("ShowParticles");
/*     */     }
/* 182 */     return new MobEffect(mobEffectList, i, (b2 < 0) ? 0 : b2, bool1, bool2);
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
/*     */   public int b(MobEffect paramMobEffect) {
/* 195 */     char c = '經';
/* 196 */     if ((getDuration() > 32147 && paramMobEffect.getDuration() > 32147) || (isAmbient() && paramMobEffect.isAmbient()))
/*     */     {
/* 198 */       return ComparisonChain.start()
/* 199 */         .compare(Boolean.valueOf(isAmbient()), Boolean.valueOf(paramMobEffect.isAmbient()))
/* 200 */         .compare(getMobEffect().getColor(), paramMobEffect.getMobEffect().getColor())
/* 201 */         .result();
/*     */     }
/* 203 */     return ComparisonChain.start()
/* 204 */       .compare(Boolean.valueOf(isAmbient()), Boolean.valueOf(paramMobEffect.isAmbient()))
/* 205 */       .compare(getDuration(), paramMobEffect.getDuration())
/* 206 */       .compare(getMobEffect().getColor(), paramMobEffect.getMobEffect().getColor())
/* 207 */       .result();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MobEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */