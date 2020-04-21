/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ public class CombatTracker
/*     */ {
/*  22 */   private final List<CombatEntry> a = Lists.newArrayList();
/*     */   private final EntityLiving b;
/*     */   private int c;
/*     */   private int d;
/*     */   private int e;
/*     */   private boolean f;
/*     */   private boolean g;
/*     */   private String h;
/*     */   
/*     */   public CombatTracker(EntityLiving paramEntityLiving) {
/*  32 */     this.b = paramEntityLiving;
/*     */   }
/*     */   
/*     */   public void a() {
/*  36 */     k();
/*     */     
/*  38 */     if (this.b.m_()) {
/*  39 */       Block block = this.b.world.getType(new BlockPosition(this.b.locX, (this.b.getBoundingBox()).b, this.b.locZ)).getBlock();
/*     */       
/*  41 */       if (block == Blocks.LADDER) {
/*  42 */         this.h = "ladder";
/*  43 */       } else if (block == Blocks.VINE) {
/*  44 */         this.h = "vines";
/*     */       } 
/*  46 */     } else if (this.b.isInWater()) {
/*  47 */       this.h = "water";
/*     */     } 
/*     */   }
/*     */   
/*     */   public void trackDamage(DamageSource paramDamageSource, float paramFloat1, float paramFloat2) {
/*  52 */     g();
/*  53 */     a();
/*     */     
/*  55 */     CombatEntry combatEntry = new CombatEntry(paramDamageSource, this.b.ticksLived, paramFloat1, paramFloat2, this.h, this.b.fallDistance);
/*     */     
/*  57 */     this.a.add(combatEntry);
/*  58 */     this.c = this.b.ticksLived;
/*  59 */     this.g = true;
/*     */     
/*  61 */     if (combatEntry.f() && !this.f && this.b.isAlive()) {
/*  62 */       this.f = true;
/*  63 */       this.d = this.b.ticksLived;
/*  64 */       this.e = this.d;
/*  65 */       this.b.enterCombat();
/*     */     } 
/*     */   }
/*     */   public IChatBaseComponent getDeathMessage() {
/*     */     IChatBaseComponent iChatBaseComponent2;
/*  70 */     if (this.a.isEmpty()) {
/*  71 */       return new ChatMessage("death.attack.generic", new Object[] { this.b.getScoreboardDisplayName() });
/*     */     }
/*     */     
/*  74 */     CombatEntry combatEntry1 = j();
/*  75 */     CombatEntry combatEntry2 = this.a.get(this.a.size() - 1);
/*     */     
/*  77 */     IChatBaseComponent iChatBaseComponent1 = combatEntry2.h();
/*  78 */     Entity entity = combatEntry2.a().getEntity();
/*     */     
/*  80 */     if (combatEntry1 != null && combatEntry2.a() == DamageSource.FALL) {
/*  81 */       IChatBaseComponent iChatBaseComponent = combatEntry1.h();
/*     */       
/*  83 */       if (combatEntry1.a() == DamageSource.FALL || combatEntry1.a() == DamageSource.OUT_OF_WORLD) {
/*  84 */         iChatBaseComponent2 = new ChatMessage("death.fell.accident." + a(combatEntry1), new Object[] { this.b.getScoreboardDisplayName() });
/*  85 */       } else if (iChatBaseComponent != null && (iChatBaseComponent1 == null || !iChatBaseComponent.equals(iChatBaseComponent1))) {
/*  86 */         Entity entity1 = combatEntry1.a().getEntity();
/*  87 */         ItemStack itemStack = (entity1 instanceof EntityLiving) ? ((EntityLiving)entity1).getItemInMainHand() : ItemStack.a;
/*     */         
/*  89 */         if (!itemStack.isEmpty() && itemStack.hasName()) {
/*  90 */           iChatBaseComponent2 = new ChatMessage("death.fell.assist.item", new Object[] { this.b.getScoreboardDisplayName(), iChatBaseComponent, itemStack.C() });
/*     */         } else {
/*  92 */           iChatBaseComponent2 = new ChatMessage("death.fell.assist", new Object[] { this.b.getScoreboardDisplayName(), iChatBaseComponent });
/*     */         } 
/*  94 */       } else if (iChatBaseComponent1 != null) {
/*  95 */         ItemStack itemStack = (entity instanceof EntityLiving) ? ((EntityLiving)entity).getItemInMainHand() : ItemStack.a;
/*  96 */         if (!itemStack.isEmpty() && itemStack.hasName()) {
/*  97 */           iChatBaseComponent2 = new ChatMessage("death.fell.finish.item", new Object[] { this.b.getScoreboardDisplayName(), iChatBaseComponent1, itemStack.C() });
/*     */         } else {
/*  99 */           iChatBaseComponent2 = new ChatMessage("death.fell.finish", new Object[] { this.b.getScoreboardDisplayName(), iChatBaseComponent1 });
/*     */         } 
/*     */       } else {
/* 102 */         iChatBaseComponent2 = new ChatMessage("death.fell.killer", new Object[] { this.b.getScoreboardDisplayName() });
/*     */       } 
/*     */     } else {
/* 105 */       iChatBaseComponent2 = combatEntry2.a().getLocalizedDeathMessage(this.b);
/*     */     } 
/*     */     
/* 108 */     return iChatBaseComponent2;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving c() {
/* 113 */     EntityLiving entityLiving = null;
/* 114 */     EntityHuman entityHuman = null;
/* 115 */     float f1 = 0.0F;
/* 116 */     float f2 = 0.0F;
/*     */     
/* 118 */     for (CombatEntry combatEntry : this.a) {
/* 119 */       if (combatEntry.a().getEntity() instanceof EntityHuman && (entityHuman == null || combatEntry.c() > f2)) {
/* 120 */         f2 = combatEntry.c();
/* 121 */         entityHuman = (EntityHuman)combatEntry.a().getEntity();
/*     */       } 
/*     */       
/* 124 */       if (combatEntry.a().getEntity() instanceof EntityLiving && (entityLiving == null || combatEntry.c() > f1)) {
/* 125 */         f1 = combatEntry.c();
/* 126 */         entityLiving = (EntityLiving)combatEntry.a().getEntity();
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     if (entityHuman != null && f2 >= f1 / 3.0F) {
/* 131 */       return entityHuman;
/*     */     }
/* 133 */     return entityLiving;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private CombatEntry j() {
/* 139 */     CombatEntry combatEntry1 = null;
/* 140 */     CombatEntry combatEntry2 = null;
/* 141 */     float f1 = 0.0F;
/* 142 */     float f2 = 0.0F;
/*     */     
/* 144 */     for (byte b = 0; b < this.a.size(); b++) {
/* 145 */       CombatEntry combatEntry3 = this.a.get(b);
/* 146 */       CombatEntry combatEntry4 = (b > 0) ? this.a.get(b - 1) : null;
/*     */       
/* 148 */       if ((combatEntry3.a() == DamageSource.FALL || combatEntry3.a() == DamageSource.OUT_OF_WORLD) && combatEntry3.j() > 0.0F && (combatEntry1 == null || combatEntry3.j() > f2)) {
/* 149 */         if (b > 0) {
/* 150 */           combatEntry1 = combatEntry4;
/*     */         } else {
/* 152 */           combatEntry1 = combatEntry3;
/*     */         } 
/* 154 */         f2 = combatEntry3.j();
/*     */       } 
/*     */       
/* 157 */       if (combatEntry3.g() != null && (combatEntry2 == null || combatEntry3.c() > f1)) {
/* 158 */         combatEntry2 = combatEntry3;
/* 159 */         f1 = combatEntry3.c();
/*     */       } 
/*     */     } 
/*     */     
/* 163 */     if (f2 > 5.0F && combatEntry1 != null)
/* 164 */       return combatEntry1; 
/* 165 */     if (f1 > 5.0F && combatEntry2 != null) {
/* 166 */       return combatEntry2;
/*     */     }
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String a(CombatEntry paramCombatEntry) {
/* 173 */     return (paramCombatEntry.g() == null) ? "generic" : paramCombatEntry.g();
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
/*     */   public int f() {
/* 187 */     if (this.f) {
/* 188 */       return this.b.ticksLived - this.d;
/*     */     }
/* 190 */     return this.e - this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   private void k() {
/* 195 */     this.h = null;
/*     */   }
/*     */   
/*     */   public void g() {
/* 199 */     byte b = this.f ? 300 : 100;
/*     */     
/* 201 */     if (this.g && (!this.b.isAlive() || this.b.ticksLived - this.c > b)) {
/* 202 */       boolean bool = this.f;
/* 203 */       this.g = false;
/* 204 */       this.f = false;
/* 205 */       this.e = this.b.ticksLived;
/*     */       
/* 207 */       if (bool) {
/* 208 */         this.b.exitCombat();
/*     */       }
/* 210 */       this.a.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public EntityLiving h() {
/* 215 */     return this.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CombatTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */