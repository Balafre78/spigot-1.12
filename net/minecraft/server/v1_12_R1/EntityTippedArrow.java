/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class EntityTippedArrow
/*     */   extends EntityArrow
/*     */ {
/*  11 */   private static final DataWatcherObject<Integer> f = DataWatcher.a((Class)EntityTippedArrow.class, DataWatcherRegistry.b);
/*     */   private PotionRegistry potionRegistry;
/*     */   public final Set<MobEffect> effects;
/*     */   private boolean hasColor;
/*     */   
/*     */   public EntityTippedArrow(World world) {
/*  17 */     super(world);
/*  18 */     this.potionRegistry = Potions.EMPTY;
/*  19 */     this.effects = Sets.newHashSet();
/*     */   }
/*     */   
/*     */   public EntityTippedArrow(World world, double d0, double d1, double d2) {
/*  23 */     super(world, d0, d1, d2);
/*  24 */     this.potionRegistry = Potions.EMPTY;
/*  25 */     this.effects = Sets.newHashSet();
/*     */   }
/*     */   
/*     */   public EntityTippedArrow(World world, EntityLiving entityliving) {
/*  29 */     super(world, entityliving);
/*  30 */     this.potionRegistry = Potions.EMPTY;
/*  31 */     this.effects = Sets.newHashSet();
/*     */   }
/*     */   
/*     */   public void a(ItemStack itemstack) {
/*  35 */     if (itemstack.getItem() == Items.TIPPED_ARROW) {
/*  36 */       this.potionRegistry = PotionUtil.d(itemstack);
/*  37 */       List<MobEffect> list = PotionUtil.b(itemstack);
/*     */       
/*  39 */       if (!list.isEmpty()) {
/*  40 */         Iterator<MobEffect> iterator = list.iterator();
/*     */         
/*  42 */         while (iterator.hasNext()) {
/*  43 */           MobEffect mobeffect = iterator.next();
/*     */           
/*  45 */           this.effects.add(new MobEffect(mobeffect));
/*     */         } 
/*     */       } 
/*     */       
/*  49 */       int i = b(itemstack);
/*     */       
/*  51 */       if (i == -1) {
/*  52 */         q();
/*     */       } else {
/*  54 */         setColor(i);
/*     */       } 
/*  56 */     } else if (itemstack.getItem() == Items.ARROW) {
/*  57 */       this.potionRegistry = Potions.EMPTY;
/*  58 */       this.effects.clear();
/*  59 */       this.datawatcher.set(f, Integer.valueOf(-1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int b(ItemStack itemstack) {
/*  65 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/*  67 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("CustomPotionColor", 99)) ? nbttagcompound.getInt("CustomPotionColor") : -1;
/*     */   }
/*     */   
/*     */   private void q() {
/*  71 */     this.hasColor = false;
/*  72 */     this.datawatcher.set(f, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */   
/*     */   public void a(MobEffect mobeffect) {
/*  76 */     this.effects.add(mobeffect);
/*  77 */     getDataWatcher().set(f, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */   
/*     */   protected void i() {
/*  81 */     super.i();
/*  82 */     this.datawatcher.register(f, Integer.valueOf(-1));
/*     */   }
/*     */   
/*     */   public void B_() {
/*  86 */     super.B_();
/*  87 */     if (this.world.isClientSide) {
/*  88 */       if (this.inGround) {
/*  89 */         if (this.b % 5 == 0) {
/*  90 */           c(1);
/*     */         }
/*     */       } else {
/*  93 */         c(2);
/*     */       } 
/*  95 */     } else if (this.inGround && this.b != 0 && !this.effects.isEmpty() && this.b >= 600) {
/*  96 */       this.world.broadcastEntityEffect(this, (byte)0);
/*  97 */       this.potionRegistry = Potions.EMPTY;
/*  98 */       this.effects.clear();
/*  99 */       this.datawatcher.set(f, Integer.valueOf(-1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(int i) {
/* 105 */     int j = getColor();
/*     */     
/* 107 */     if (j != -1 && i > 0) {
/* 108 */       double d0 = (j >> 16 & 0xFF) / 255.0D;
/* 109 */       double d1 = (j >> 8 & 0xFF) / 255.0D;
/* 110 */       double d2 = (j >> 0 & 0xFF) / 255.0D;
/*     */       
/* 112 */       for (int k = 0; k < i; k++) {
/* 113 */         this.world.addParticle(EnumParticle.SPELL_MOB, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, d0, d1, d2, new int[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshEffects() {
/* 121 */     getDataWatcher().set(f, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */   
/*     */   public String getType() {
/* 125 */     return ((MinecraftKey)PotionRegistry.a.b(this.potionRegistry)).toString();
/*     */   }
/*     */   
/*     */   public void setType(String string) {
/* 129 */     this.potionRegistry = PotionRegistry.a.get(new MinecraftKey(string));
/* 130 */     this.datawatcher.set(f, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */   
/*     */   public boolean isTipped() {
/* 134 */     return !(this.effects.isEmpty() && this.potionRegistry == Potions.EMPTY);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/* 139 */     return ((Integer)this.datawatcher.<Integer>get(f)).intValue();
/*     */   }
/*     */   
/*     */   public void setColor(int i) {
/* 143 */     this.hasColor = true;
/* 144 */     this.datawatcher.set(f, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public static void c(DataConverterManager dataconvertermanager) {
/* 148 */     EntityArrow.a(dataconvertermanager, "TippedArrow");
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 152 */     super.b(nbttagcompound);
/* 153 */     if (this.potionRegistry != Potions.EMPTY && this.potionRegistry != null) {
/* 154 */       nbttagcompound.setString("Potion", ((MinecraftKey)PotionRegistry.a.b(this.potionRegistry)).toString());
/*     */     }
/*     */     
/* 157 */     if (this.hasColor) {
/* 158 */       nbttagcompound.setInt("Color", getColor());
/*     */     }
/*     */     
/* 161 */     if (!this.effects.isEmpty()) {
/* 162 */       NBTTagList nbttaglist = new NBTTagList();
/* 163 */       Iterator<MobEffect> iterator = this.effects.iterator();
/*     */       
/* 165 */       while (iterator.hasNext()) {
/* 166 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 168 */         nbttaglist.add(mobeffect.a(new NBTTagCompound()));
/*     */       } 
/*     */       
/* 171 */       nbttagcompound.set("CustomPotionEffects", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 177 */     super.a(nbttagcompound);
/* 178 */     if (nbttagcompound.hasKeyOfType("Potion", 8)) {
/* 179 */       this.potionRegistry = PotionUtil.c(nbttagcompound);
/*     */     }
/*     */     
/* 182 */     Iterator<MobEffect> iterator = PotionUtil.b(nbttagcompound).iterator();
/*     */     
/* 184 */     while (iterator.hasNext()) {
/* 185 */       MobEffect mobeffect = iterator.next();
/*     */       
/* 187 */       a(mobeffect);
/*     */     } 
/*     */     
/* 190 */     if (nbttagcompound.hasKeyOfType("Color", 99)) {
/* 191 */       setColor(nbttagcompound.getInt("Color"));
/*     */     } else {
/* 193 */       q();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(EntityLiving entityliving) {
/* 199 */     super.a(entityliving);
/* 200 */     Iterator<MobEffect> iterator = this.potionRegistry.a().iterator();
/*     */ 
/*     */ 
/*     */     
/* 204 */     while (iterator.hasNext()) {
/* 205 */       MobEffect mobeffect = iterator.next();
/* 206 */       entityliving.addEffect(new MobEffect(mobeffect.getMobEffect(), Math.max(mobeffect.getDuration() / 8, 1), mobeffect.getAmplifier(), mobeffect.isAmbient(), mobeffect.isShowParticles()));
/*     */     } 
/*     */     
/* 209 */     if (!this.effects.isEmpty()) {
/* 210 */       iterator = this.effects.iterator();
/*     */       
/* 212 */       while (iterator.hasNext()) {
/* 213 */         MobEffect mobeffect = iterator.next();
/* 214 */         entityliving.addEffect(mobeffect);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack j() {
/* 221 */     if (this.effects.isEmpty() && this.potionRegistry == Potions.EMPTY) {
/* 222 */       return new ItemStack(Items.ARROW);
/*     */     }
/* 224 */     ItemStack itemstack = new ItemStack(Items.TIPPED_ARROW);
/*     */     
/* 226 */     PotionUtil.a(itemstack, this.potionRegistry);
/* 227 */     PotionUtil.a(itemstack, this.effects);
/* 228 */     if (this.hasColor) {
/* 229 */       NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */       
/* 231 */       if (nbttagcompound == null) {
/* 232 */         nbttagcompound = new NBTTagCompound();
/* 233 */         itemstack.setTag(nbttagcompound);
/*     */       } 
/*     */       
/* 236 */       nbttagcompound.setInt("CustomPotionColor", getColor());
/*     */     } 
/*     */     
/* 239 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */