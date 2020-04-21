/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.UUID;
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
/*     */ public class EntityHorse
/*     */   extends EntityHorseAbstract
/*     */ {
/*  34 */   private static final UUID bH = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
/*     */   
/*  36 */   private static final DataWatcherObject<Integer> bI = DataWatcher.a((Class)EntityHorse.class, DataWatcherRegistry.b);
/*  37 */   private static final DataWatcherObject<Integer> bJ = DataWatcher.a((Class)EntityHorse.class, DataWatcherRegistry.b);
/*     */ 
/*     */   
/*  40 */   private static final String[] bK = new String[] { "textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static final String[] bL = new String[] { "hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private static final String[] bM = new String[] { null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private static final String[] bN = new String[] { "", "wo_", "wmo", "wdo", "bdo" };
/*     */ 
/*     */   
/*     */   private String bO;
/*     */   
/*  66 */   private final String[] bP = new String[3];
/*     */   
/*     */   public EntityHorse(World paramWorld) {
/*  69 */     super(paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  74 */     super.i();
/*     */     
/*  76 */     this.datawatcher.register(bI, Integer.valueOf(0));
/*  77 */     this.datawatcher.register(bJ, Integer.valueOf(EnumHorseArmor.NONE.a()));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  81 */     EntityHorseAbstract.c(paramDataConverterManager, EntityHorse.class);
/*  82 */     paramDataConverterManager.a(DataConverterTypes.ENTITY, new DataInspectorItem(EntityHorse.class, new String[] { "ArmorItem" }));
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  87 */     super.b(paramNBTTagCompound);
/*     */     
/*  89 */     paramNBTTagCompound.setInt("Variant", getVariant());
/*     */     
/*  91 */     if (!this.inventoryChest.getItem(1).isEmpty()) {
/*  92 */       paramNBTTagCompound.set("ArmorItem", this.inventoryChest.getItem(1).save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/*  98 */     super.a(paramNBTTagCompound);
/*     */     
/* 100 */     setVariant(paramNBTTagCompound.getInt("Variant"));
/*     */     
/* 102 */     if (paramNBTTagCompound.hasKeyOfType("ArmorItem", 10)) {
/* 103 */       ItemStack itemStack = new ItemStack(paramNBTTagCompound.getCompound("ArmorItem"));
/* 104 */       if (!itemStack.isEmpty() && EnumHorseArmor.b(itemStack.getItem())) {
/* 105 */         this.inventoryChest.setItem(1, itemStack);
/*     */       }
/*     */     } 
/*     */     
/* 109 */     dD();
/*     */   }
/*     */   
/*     */   public void setVariant(int paramInt) {
/* 113 */     this.datawatcher.set(bI, Integer.valueOf(paramInt));
/* 114 */     dQ();
/*     */   }
/*     */   
/*     */   public int getVariant() {
/* 118 */     return ((Integer)this.datawatcher.<Integer>get(bI)).intValue();
/*     */   }
/*     */   
/*     */   private void dQ() {
/* 122 */     this.bO = null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dD() {
/* 155 */     super.dD();
/*     */     
/* 157 */     g(this.inventoryChest.getItem(1));
/*     */   }
/*     */   
/*     */   public void g(ItemStack paramItemStack) {
/* 161 */     EnumHorseArmor enumHorseArmor = EnumHorseArmor.a(paramItemStack);
/* 162 */     this.datawatcher.set(bJ, Integer.valueOf(enumHorseArmor.a()));
/* 163 */     dQ();
/*     */     
/* 165 */     if (!this.world.isClientSide) {
/* 166 */       getAttributeInstance(GenericAttributes.h).b(bH);
/* 167 */       int i = enumHorseArmor.c();
/* 168 */       if (i != 0) {
/* 169 */         getAttributeInstance(GenericAttributes.h).b((new AttributeModifier(bH, "Horse armor bonus", i, 0)).a(false));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public EnumHorseArmor dt() {
/* 175 */     return EnumHorseArmor.a(((Integer)this.datawatcher.<Integer>get(bJ)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory paramIInventory) {
/* 180 */     EnumHorseArmor enumHorseArmor1 = dt();
/*     */     
/* 182 */     super.a(paramIInventory);
/*     */     
/* 184 */     EnumHorseArmor enumHorseArmor2 = dt();
/* 185 */     if (this.ticksLived > 20 && enumHorseArmor1 != enumHorseArmor2 && enumHorseArmor2 != EnumHorseArmor.NONE) {
/* 186 */       a(SoundEffects.cH, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(SoundEffectType paramSoundEffectType) {
/* 192 */     super.a(paramSoundEffectType);
/* 193 */     if (this.random.nextInt(10) == 0) {
/* 194 */       a(SoundEffects.cI, paramSoundEffectType.a() * 0.6F, paramSoundEffectType.b());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/* 200 */     super.initAttributes();
/*     */     
/* 202 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(dM());
/* 203 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(dO());
/* 204 */     getAttributeInstance(attributeJumpStrength).setValue(dN());
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 209 */     super.B_();
/*     */ 
/*     */     
/* 212 */     if (this.world.isClientSide && this.datawatcher.a()) {
/* 213 */       this.datawatcher.e();
/* 214 */       dQ();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 220 */     super.F();
/* 221 */     return SoundEffects.cF;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 226 */     super.cf();
/* 227 */     return SoundEffects.cJ;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 232 */     super.d(paramDamageSource);
/* 233 */     return SoundEffects.cM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect do_() {
/* 238 */     super.do_();
/* 239 */     return SoundEffects.cG;
/*     */   }
/*     */ 
/*     */   
/*     */   protected MinecraftKey J() {
/* 244 */     return LootTables.G;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 249 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 250 */     boolean bool = !itemStack.isEmpty() ? true : false;
/* 251 */     if (bool && itemStack.getItem() == Items.SPAWN_EGG) {
/* 252 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/* 255 */     if (!isBaby()) {
/* 256 */       if (isTamed() && paramEntityHuman.isSneaking()) {
/* 257 */         c(paramEntityHuman);
/* 258 */         return true;
/*     */       } 
/*     */       
/* 261 */       if (isVehicle()) {
/* 262 */         return super.a(paramEntityHuman, paramEnumHand);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 267 */     if (bool) {
/* 268 */       if (b(paramEntityHuman, itemStack)) {
/* 269 */         if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 270 */           itemStack.subtract(1);
/*     */         }
/* 272 */         return true;
/*     */       } 
/*     */       
/* 275 */       if (itemStack.a(paramEntityHuman, this, paramEnumHand)) {
/* 276 */         return true;
/*     */       }
/*     */       
/* 279 */       if (!isTamed()) {
/* 280 */         dK();
/* 281 */         return true;
/*     */       } 
/*     */       
/* 284 */       boolean bool1 = (EnumHorseArmor.a(itemStack) != EnumHorseArmor.NONE) ? true : false;
/* 285 */       boolean bool2 = (!isBaby() && !dG() && itemStack.getItem() == Items.SADDLE) ? true : false;
/* 286 */       if (bool1 || bool2) {
/* 287 */         c(paramEntityHuman);
/* 288 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 292 */     if (isBaby()) {
/* 293 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/* 296 */     g(paramEntityHuman);
/* 297 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal paramEntityAnimal) {
/* 302 */     if (paramEntityAnimal == this) {
/* 303 */       return false;
/*     */     }
/*     */     
/* 306 */     if (paramEntityAnimal instanceof EntityHorseDonkey || paramEntityAnimal instanceof EntityHorse) {
/* 307 */       return (dL() && ((EntityHorseAbstract)paramEntityAnimal).dL());
/*     */     }
/*     */     
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable paramEntityAgeable) {
/*     */     EntityHorse entityHorse;
/* 316 */     if (paramEntityAgeable instanceof EntityHorseDonkey) {
/* 317 */       EntityHorseMule entityHorseMule = new EntityHorseMule(this.world);
/*     */     } else {
/* 319 */       int j; EntityHorse entityHorse1 = (EntityHorse)paramEntityAgeable;
/*     */       
/* 321 */       entityHorse = new EntityHorse(this.world);
/*     */       
/* 323 */       int i = this.random.nextInt(9);
/* 324 */       if (i < 4) {
/* 325 */         j = getVariant() & 0xFF;
/* 326 */       } else if (i < 8) {
/* 327 */         j = entityHorse1.getVariant() & 0xFF;
/*     */       } else {
/* 329 */         j = this.random.nextInt(7);
/*     */       } 
/*     */       
/* 332 */       int k = this.random.nextInt(5);
/* 333 */       if (k < 2) {
/* 334 */         j |= getVariant() & 0xFF00;
/* 335 */       } else if (k < 4) {
/* 336 */         j |= entityHorse1.getVariant() & 0xFF00;
/*     */       } else {
/* 338 */         j |= this.random.nextInt(5) << 8 & 0xFF00;
/*     */       } 
/* 340 */       entityHorse.setVariant(j);
/*     */     } 
/*     */     
/* 343 */     a(paramEntityAgeable, entityHorse);
/*     */     
/* 345 */     return entityHorse;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dP() {
/* 350 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(ItemStack paramItemStack) {
/* 355 */     return EnumHorseArmor.b(paramItemStack.getItem());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler paramDifficultyDamageScaler, @Nullable GroupDataEntity paramGroupDataEntity) {
/*     */     int i;
/* 361 */     paramGroupDataEntity = super.prepare(paramDifficultyDamageScaler, paramGroupDataEntity);
/*     */ 
/*     */     
/* 364 */     if (paramGroupDataEntity instanceof a) {
/* 365 */       i = ((a)paramGroupDataEntity).a;
/*     */     } else {
/* 367 */       i = this.random.nextInt(7);
/* 368 */       paramGroupDataEntity = new a(i);
/*     */     } 
/* 370 */     setVariant(i | this.random.nextInt(5) << 8);
/*     */     
/* 372 */     return paramGroupDataEntity;
/*     */   }
/*     */   
/*     */   public static class a implements GroupDataEntity {
/*     */     public int a;
/*     */     
/*     */     public a(int param1Int) {
/* 379 */       this.a = param1Int;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */