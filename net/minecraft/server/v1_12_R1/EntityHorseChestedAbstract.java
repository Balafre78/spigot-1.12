/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public abstract class EntityHorseChestedAbstract
/*     */   extends EntityHorseAbstract {
/*   5 */   private static final DataWatcherObject<Boolean> bH = DataWatcher.a((Class)EntityHorseChestedAbstract.class, DataWatcherRegistry.h);
/*     */   
/*     */   public EntityHorseChestedAbstract(World world) {
/*   8 */     super(world);
/*   9 */     this.bF = false;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  13 */     super.i();
/*  14 */     this.datawatcher.register(bH, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  18 */     super.initAttributes();
/*  19 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(dM());
/*  20 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.17499999701976776D);
/*  21 */     getAttributeInstance(attributeJumpStrength).setValue(0.5D);
/*     */   }
/*     */   
/*     */   public boolean isCarryingChest() {
/*  25 */     return ((Boolean)this.datawatcher.<Boolean>get(bH)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setCarryingChest(boolean flag) {
/*  29 */     this.datawatcher.set(bH, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   protected int dn() {
/*  33 */     return isCarryingChest() ? 17 : super.dn();
/*     */   }
/*     */   
/*     */   public double aG() {
/*  37 */     return super.aG() - 0.25D;
/*     */   }
/*     */   
/*     */   protected SoundEffect do_() {
/*  41 */     super.do_();
/*  42 */     return SoundEffects.aD;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/*  47 */     if (isCarryingChest()) {
/*  48 */       if (!this.world.isClientSide) {
/*  49 */         a(Item.getItemOf(Blocks.CHEST), 1);
/*     */       }
/*     */       
/*  52 */       setCarryingChest(false);
/*     */     } 
/*  54 */     super.die(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void b(DataConverterManager dataconvertermanager, Class<?> oclass) {
/*  59 */     EntityHorseAbstract.c(dataconvertermanager, oclass);
/*  60 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItemList(oclass, new String[] { "Items" }));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  64 */     super.b(nbttagcompound);
/*  65 */     nbttagcompound.setBoolean("ChestedHorse", isCarryingChest());
/*  66 */     if (isCarryingChest()) {
/*  67 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/*  69 */       for (int i = 2; i < this.inventoryChest.getSize(); i++) {
/*  70 */         ItemStack itemstack = this.inventoryChest.getItem(i);
/*     */         
/*  72 */         if (!itemstack.isEmpty()) {
/*  73 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */           
/*  75 */           nbttagcompound1.setByte("Slot", (byte)i);
/*  76 */           itemstack.save(nbttagcompound1);
/*  77 */           nbttaglist.add(nbttagcompound1);
/*     */         } 
/*     */       } 
/*     */       
/*  81 */       nbttagcompound.set("Items", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  87 */     super.a(nbttagcompound);
/*  88 */     setCarryingChest(nbttagcompound.getBoolean("ChestedHorse"));
/*  89 */     if (isCarryingChest()) {
/*  90 */       NBTTagList nbttaglist = nbttagcompound.getList("Items", 10);
/*     */       
/*  92 */       loadChest();
/*     */       
/*  94 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  95 */         NBTTagCompound nbttagcompound1 = nbttaglist.get(i);
/*  96 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*     */         
/*  98 */         if (j >= 2 && j < this.inventoryChest.getSize()) {
/*  99 */           this.inventoryChest.setItem(j, new ItemStack(nbttagcompound1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     dD();
/*     */   }
/*     */   
/*     */   public boolean c(int i, ItemStack itemstack) {
/* 108 */     if (i == 499) {
/* 109 */       if (isCarryingChest() && itemstack.isEmpty()) {
/* 110 */         setCarryingChest(false);
/* 111 */         loadChest();
/* 112 */         return true;
/*     */       } 
/*     */       
/* 115 */       if (!isCarryingChest() && itemstack.getItem() == Item.getItemOf(Blocks.CHEST)) {
/* 116 */         setCarryingChest(true);
/* 117 */         loadChest();
/* 118 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return super.c(i, itemstack);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 126 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 128 */     if (itemstack.getItem() == Items.SPAWN_EGG) {
/* 129 */       return super.a(entityhuman, enumhand);
/*     */     }
/* 131 */     if (!isBaby()) {
/* 132 */       if (isTamed() && entityhuman.isSneaking()) {
/* 133 */         c(entityhuman);
/* 134 */         return true;
/*     */       } 
/*     */       
/* 137 */       if (isVehicle()) {
/* 138 */         return super.a(entityhuman, enumhand);
/*     */       }
/*     */     } 
/*     */     
/* 142 */     if (!itemstack.isEmpty()) {
/* 143 */       boolean flag = b(entityhuman, itemstack);
/*     */       
/* 145 */       if (!flag && !isTamed()) {
/* 146 */         if (itemstack.a(entityhuman, this, enumhand)) {
/* 147 */           return true;
/*     */         }
/*     */         
/* 150 */         dK();
/* 151 */         return true;
/*     */       } 
/*     */       
/* 154 */       if (!flag && !isCarryingChest() && itemstack.getItem() == Item.getItemOf(Blocks.CHEST)) {
/* 155 */         setCarryingChest(true);
/* 156 */         dp();
/* 157 */         flag = true;
/* 158 */         loadChest();
/*     */       } 
/*     */       
/* 161 */       if (!flag && !isBaby() && !dG() && itemstack.getItem() == Items.SADDLE) {
/* 162 */         c(entityhuman);
/* 163 */         return true;
/*     */       } 
/*     */       
/* 166 */       if (flag) {
/* 167 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 168 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 171 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 175 */     if (isBaby())
/* 176 */       return super.a(entityhuman, enumhand); 
/* 177 */     if (itemstack.a(entityhuman, this, enumhand)) {
/* 178 */       return true;
/*     */     }
/* 180 */     g(entityhuman);
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dp() {
/* 187 */     a(SoundEffects.aE, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */   }
/*     */   
/*     */   public int dt() {
/* 191 */     return 5;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorseChestedAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */