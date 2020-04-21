/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftMerchantRecipe;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.VillagerAcquireTradeEvent;
/*     */ import org.bukkit.event.entity.VillagerReplenishTradeEvent;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ 
/*     */ public class EntityVillager
/*     */   extends EntityAgeable implements NPC, IMerchant {
/*  20 */   private static final Logger by = LogManager.getLogger();
/*  21 */   private static final DataWatcherObject<Integer> bz = DataWatcher.a((Class)EntityVillager.class, DataWatcherRegistry.b);
/*     */   private int profession;
/*     */   private boolean bB;
/*     */   private boolean bC;
/*     */   Village village;
/*     */   @Nullable
/*     */   private EntityHuman tradingPlayer;
/*     */   @Nullable
/*     */   private MerchantRecipeList trades;
/*     */   private int bF;
/*     */   private boolean bG;
/*     */   private boolean bH;
/*     */   public int riches;
/*     */   private String bJ;
/*     */   private int bK;
/*     */   private int bL;
/*     */   private boolean bM;
/*     */   private boolean bN;
/*     */   public final InventorySubcontainer inventory;
/*  40 */   private static final IMerchantRecipeOption[][][][] bP = new IMerchantRecipeOption[][][][] { { { { new MerchantRecipeOptionBuy(Items.WHEAT, new MerchantOptionRandomRange(18, 22)), new MerchantRecipeOptionBuy(Items.POTATO, new MerchantOptionRandomRange(15, 19)), new MerchantRecipeOptionBuy(Items.CARROT, new MerchantOptionRandomRange(15, 19)), new MerchantRecipeOptionSell(Items.BREAD, new MerchantOptionRandomRange(-4, -2)) }, { new MerchantRecipeOptionBuy(Item.getItemOf(Blocks.PUMPKIN), new MerchantOptionRandomRange(8, 13)), new MerchantRecipeOptionSell(Items.PUMPKIN_PIE, new MerchantOptionRandomRange(-3, -2)) }, { new MerchantRecipeOptionBuy(Item.getItemOf(Blocks.MELON_BLOCK), new MerchantOptionRandomRange(7, 12)), new MerchantRecipeOptionSell(Items.APPLE, new MerchantOptionRandomRange(-7, -5)) }, { new MerchantRecipeOptionSell(Items.COOKIE, new MerchantOptionRandomRange(-10, -6)), new MerchantRecipeOptionSell(Items.CAKE, new MerchantOptionRandomRange(1, 1)) } }, { { new MerchantRecipeOptionBuy(Items.STRING, new MerchantOptionRandomRange(15, 20)), new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionProcess(Items.FISH, new MerchantOptionRandomRange(6, 6), Items.COOKED_FISH, new MerchantOptionRandomRange(6, 6)) }, { new MerchantRecipeOptionEnchant(Items.FISHING_ROD, new MerchantOptionRandomRange(7, 8)) } }, { { new MerchantRecipeOptionBuy(Item.getItemOf(Blocks.WOOL), new MerchantOptionRandomRange(16, 22)), new MerchantRecipeOptionSell(Items.SHEARS, new MerchantOptionRandomRange(3, 4)) }, { new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL)), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 1), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 2), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 3), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 4), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 5), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 6), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 7), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 8), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 9), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 10), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 11), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 12), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 13), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 14), new MerchantOptionRandomRange(1, 2)), new MerchantRecipeOptionSell(new ItemStack(Item.getItemOf(Blocks.WOOL), 1, 15), new MerchantOptionRandomRange(1, 2)) } }, { { new MerchantRecipeOptionBuy(Items.STRING, new MerchantOptionRandomRange(15, 20)), new MerchantRecipeOptionSell(Items.ARROW, new MerchantOptionRandomRange(-12, -8)) }, { new MerchantRecipeOptionSell(Items.BOW, new MerchantOptionRandomRange(2, 3)), new MerchantRecipeOptionProcess(Item.getItemOf(Blocks.GRAVEL), new MerchantOptionRandomRange(10, 10), Items.FLINT, new MerchantOptionRandomRange(6, 10)) } } }, { { { new MerchantRecipeOptionBuy(Items.PAPER, new MerchantOptionRandomRange(24, 36)), new MerchantRecipeOptionBook() }, { new MerchantRecipeOptionBuy(Items.BOOK, new MerchantOptionRandomRange(8, 10)), new MerchantRecipeOptionSell(Items.COMPASS, new MerchantOptionRandomRange(10, 12)), new MerchantRecipeOptionSell(Item.getItemOf(Blocks.BOOKSHELF), new MerchantOptionRandomRange(3, 4)) }, { new MerchantRecipeOptionBuy(Items.WRITTEN_BOOK, new MerchantOptionRandomRange(2, 2)), new MerchantRecipeOptionSell(Items.CLOCK, new MerchantOptionRandomRange(10, 12)), new MerchantRecipeOptionSell(Item.getItemOf(Blocks.GLASS), new MerchantOptionRandomRange(-5, -3)) }, { new MerchantRecipeOptionBook() }, { new MerchantRecipeOptionBook() }, { new MerchantRecipeOptionSell(Items.NAME_TAG, new MerchantOptionRandomRange(20, 22)) } }, { { new MerchantRecipeOptionBuy(Items.PAPER, new MerchantOptionRandomRange(24, 36)) }, { new MerchantRecipeOptionBuy(Items.COMPASS, new MerchantOptionRandomRange(1, 1)) }, { new MerchantRecipeOptionSell(Items.MAP, new MerchantOptionRandomRange(7, 11)) }, { new h(new MerchantOptionRandomRange(12, 20), "Monument", MapIcon.Type.MONUMENT), new h(new MerchantOptionRandomRange(16, 28), "Mansion", MapIcon.Type.MANSION) } } }, { { { new MerchantRecipeOptionBuy(Items.ROTTEN_FLESH, new MerchantOptionRandomRange(36, 40)), new MerchantRecipeOptionBuy(Items.GOLD_INGOT, new MerchantOptionRandomRange(8, 10)) }, { new MerchantRecipeOptionSell(Items.REDSTONE, new MerchantOptionRandomRange(-4, -1)), new MerchantRecipeOptionSell(new ItemStack(Items.DYE, 1, EnumColor.BLUE.getInvColorIndex()), new MerchantOptionRandomRange(-2, -1)) }, { new MerchantRecipeOptionSell(Items.ENDER_PEARL, new MerchantOptionRandomRange(4, 7)), new MerchantRecipeOptionSell(Item.getItemOf(Blocks.GLOWSTONE), new MerchantOptionRandomRange(-3, -1)) }, { new MerchantRecipeOptionSell(Items.EXPERIENCE_BOTTLE, new MerchantOptionRandomRange(3, 11)) } } }, { { { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionSell(Items.IRON_HELMET, new MerchantOptionRandomRange(4, 6)) }, { new MerchantRecipeOptionBuy(Items.IRON_INGOT, new MerchantOptionRandomRange(7, 9)), new MerchantRecipeOptionSell(Items.IRON_CHESTPLATE, new MerchantOptionRandomRange(10, 14)) }, { new MerchantRecipeOptionBuy(Items.DIAMOND, new MerchantOptionRandomRange(3, 4)), new MerchantRecipeOptionEnchant(Items.DIAMOND_CHESTPLATE, new MerchantOptionRandomRange(16, 19)) }, { new MerchantRecipeOptionSell(Items.CHAINMAIL_BOOTS, new MerchantOptionRandomRange(5, 7)), new MerchantRecipeOptionSell(Items.CHAINMAIL_LEGGINGS, new MerchantOptionRandomRange(9, 11)), new MerchantRecipeOptionSell(Items.CHAINMAIL_HELMET, new MerchantOptionRandomRange(5, 7)), new MerchantRecipeOptionSell(Items.CHAINMAIL_CHESTPLATE, new MerchantOptionRandomRange(11, 15)) } }, { { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionSell(Items.IRON_AXE, new MerchantOptionRandomRange(6, 8)) }, { new MerchantRecipeOptionBuy(Items.IRON_INGOT, new MerchantOptionRandomRange(7, 9)), new MerchantRecipeOptionEnchant(Items.IRON_SWORD, new MerchantOptionRandomRange(9, 10)) }, { new MerchantRecipeOptionBuy(Items.DIAMOND, new MerchantOptionRandomRange(3, 4)), new MerchantRecipeOptionEnchant(Items.DIAMOND_SWORD, new MerchantOptionRandomRange(12, 15)), new MerchantRecipeOptionEnchant(Items.DIAMOND_AXE, new MerchantOptionRandomRange(9, 12)) } }, { { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionEnchant(Items.IRON_SHOVEL, new MerchantOptionRandomRange(5, 7)) }, { new MerchantRecipeOptionBuy(Items.IRON_INGOT, new MerchantOptionRandomRange(7, 9)), new MerchantRecipeOptionEnchant(Items.IRON_PICKAXE, new MerchantOptionRandomRange(9, 11)) }, { new MerchantRecipeOptionBuy(Items.DIAMOND, new MerchantOptionRandomRange(3, 4)), new MerchantRecipeOptionEnchant(Items.DIAMOND_PICKAXE, new MerchantOptionRandomRange(12, 15)) } } }, { { { new MerchantRecipeOptionBuy(Items.PORKCHOP, new MerchantOptionRandomRange(14, 18)), new MerchantRecipeOptionBuy(Items.CHICKEN, new MerchantOptionRandomRange(14, 18)) }, { new MerchantRecipeOptionBuy(Items.COAL, new MerchantOptionRandomRange(16, 24)), new MerchantRecipeOptionSell(Items.COOKED_PORKCHOP, new MerchantOptionRandomRange(-7, -5)), new MerchantRecipeOptionSell(Items.COOKED_CHICKEN, new MerchantOptionRandomRange(-8, -6)) } }, { { new MerchantRecipeOptionBuy(Items.LEATHER, new MerchantOptionRandomRange(9, 12)), new MerchantRecipeOptionSell(Items.LEATHER_LEGGINGS, new MerchantOptionRandomRange(2, 4)) }, { new MerchantRecipeOptionEnchant(Items.LEATHER_CHESTPLATE, new MerchantOptionRandomRange(7, 12)) }, { new MerchantRecipeOptionSell(Items.SADDLE, new MerchantOptionRandomRange(8, 10)) } } }, { {} } };
/*     */   
/*     */   public EntityVillager(World world) {
/*  43 */     this(world, 0);
/*     */   }
/*     */   
/*     */   public EntityVillager(World world, int i) {
/*  47 */     super(world);
/*  48 */     this.inventory = new InventorySubcontainer("Items", false, 8, (InventoryHolder)getBukkitEntity());
/*  49 */     setProfession(i);
/*  50 */     setSize(0.6F, 1.95F);
/*  51 */     ((Navigation)getNavigation()).a(true);
/*  52 */     m(true);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  56 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  57 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
/*  58 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityEvoker.class, 12.0F, 0.8D, 0.8D));
/*  59 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityVindicator.class, 8.0F, 0.8D, 0.8D));
/*  60 */     this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this, EntityVex.class, 8.0F, 0.6D, 0.6D));
/*  61 */     this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
/*  62 */     this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
/*  63 */     this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
/*  64 */     this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
/*  65 */     this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
/*  66 */     this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
/*  67 */     this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
/*  68 */     this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
/*  69 */     this.goalSelector.a(9, new PathfinderGoalInteract(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  70 */     this.goalSelector.a(9, new PathfinderGoalInteractVillagers(this));
/*  71 */     this.goalSelector.a(9, new PathfinderGoalRandomStrollLand(this, 0.6D));
/*  72 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*     */   }
/*     */   
/*     */   private void dw() {
/*  76 */     if (!this.bN) {
/*  77 */       this.bN = true;
/*  78 */       if (isBaby()) {
/*  79 */         this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32D));
/*  80 */       } else if (getProfession() == 0) {
/*  81 */         this.goalSelector.a(6, new PathfinderGoalVillagerFarm(this, 0.6D));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void p() {
/*  88 */     if (getProfession() == 0) {
/*  89 */       this.goalSelector.a(8, new PathfinderGoalVillagerFarm(this, 0.6D));
/*     */     }
/*     */     
/*  92 */     super.p();
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  96 */     super.initAttributes();
/*  97 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
/*     */   }
/*     */   
/*     */   protected void M() {
/* 101 */     if (--this.profession <= 0) {
/* 102 */       BlockPosition blockposition = new BlockPosition(this);
/*     */       
/* 104 */       this.world.ak().a(blockposition);
/* 105 */       this.profession = 70 + this.random.nextInt(50);
/* 106 */       this.village = this.world.ak().getClosestVillage(blockposition, 32);
/* 107 */       if (this.village == null) {
/* 108 */         di();
/*     */       } else {
/* 110 */         BlockPosition blockposition1 = this.village.a();
/*     */         
/* 112 */         a(blockposition1, this.village.b());
/* 113 */         if (this.bM) {
/* 114 */           this.bM = false;
/* 115 */           this.village.b(5);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     if (!do_() && this.bF > 0) {
/* 121 */       this.bF--;
/* 122 */       if (this.bF <= 0) {
/* 123 */         if (this.bG) {
/* 124 */           Iterator<MerchantRecipe> iterator = this.trades.iterator();
/*     */           
/* 126 */           while (iterator.hasNext()) {
/* 127 */             MerchantRecipe merchantrecipe = iterator.next();
/*     */             
/* 129 */             if (merchantrecipe.h()) {
/*     */               
/* 131 */               int bonus = this.random.nextInt(6) + this.random.nextInt(6) + 2;
/* 132 */               VillagerReplenishTradeEvent event = new VillagerReplenishTradeEvent((Villager)getBukkitEntity(), (MerchantRecipe)merchantrecipe.asBukkit(), bonus);
/* 133 */               Bukkit.getPluginManager().callEvent((Event)event);
/* 134 */               if (!event.isCancelled()) {
/* 135 */                 merchantrecipe.a(event.getBonus());
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 141 */           dx();
/* 142 */           this.bG = false;
/* 143 */           if (this.village != null && this.bJ != null) {
/* 144 */             this.world.broadcastEntityEffect(this, (byte)14);
/* 145 */             this.village.a(this.bJ, 1);
/*     */           } 
/*     */         } 
/*     */         
/* 149 */         addEffect(new MobEffect(MobEffects.REGENERATION, 200, 0));
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     super.M();
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 157 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 158 */     boolean flag = (itemstack.getItem() == Items.NAME_TAG);
/*     */     
/* 160 */     if (flag) {
/* 161 */       itemstack.a(entityhuman, this, enumhand);
/* 162 */       return true;
/* 163 */     }  if (!a(itemstack, (Class)getClass()) && isAlive() && !do_() && !isBaby()) {
/* 164 */       if (this.trades == null) {
/* 165 */         dx();
/*     */       }
/*     */       
/* 168 */       if (enumhand == EnumHand.MAIN_HAND) {
/* 169 */         entityhuman.b(StatisticList.F);
/*     */       }
/*     */       
/* 172 */       if (!this.world.isClientSide && !this.trades.isEmpty()) {
/* 173 */         setTradingPlayer(entityhuman);
/* 174 */         entityhuman.openTrade(this);
/* 175 */       } else if (this.trades.isEmpty()) {
/* 176 */         return super.a(entityhuman, enumhand);
/*     */       } 
/*     */       
/* 179 */       return true;
/*     */     } 
/* 181 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/* 186 */     super.i();
/* 187 */     this.datawatcher.register(bz, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 191 */     EntityInsentient.a(dataconvertermanager, EntityVillager.class);
/* 192 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItemList(EntityVillager.class, new String[] { "Inventory" }));
/* 193 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspector() {
/*     */           public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) {
/* 195 */             if (EntityTypes.getName((Class)EntityVillager.class).equals(new MinecraftKey(nbttagcompound.getString("id"))) && nbttagcompound.hasKeyOfType("Offers", 10)) {
/* 196 */               NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Offers");
/*     */               
/* 198 */               if (nbttagcompound1.hasKeyOfType("Recipes", 9)) {
/* 199 */                 NBTTagList nbttaglist = nbttagcompound1.getList("Recipes", 10);
/*     */                 
/* 201 */                 for (int j = 0; j < nbttaglist.size(); j++) {
/* 202 */                   NBTTagCompound nbttagcompound2 = nbttaglist.get(j);
/*     */                   
/* 204 */                   DataConverterRegistry.a(dataconverter, nbttagcompound2, i, "buy");
/* 205 */                   DataConverterRegistry.a(dataconverter, nbttagcompound2, i, "buyB");
/* 206 */                   DataConverterRegistry.a(dataconverter, nbttagcompound2, i, "sell");
/* 207 */                   nbttaglist.a(j, nbttagcompound2);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 212 */             return nbttagcompound;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 218 */     super.b(nbttagcompound);
/* 219 */     nbttagcompound.setInt("Profession", getProfession());
/* 220 */     nbttagcompound.setInt("Riches", this.riches);
/* 221 */     nbttagcompound.setInt("Career", this.bK);
/* 222 */     nbttagcompound.setInt("CareerLevel", this.bL);
/* 223 */     nbttagcompound.setBoolean("Willing", this.bH);
/* 224 */     if (this.trades != null) {
/* 225 */       nbttagcompound.set("Offers", this.trades.a());
/*     */     }
/*     */     
/* 228 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 230 */     for (int i = 0; i < this.inventory.getSize(); i++) {
/* 231 */       ItemStack itemstack = this.inventory.getItem(i);
/*     */       
/* 233 */       if (!itemstack.isEmpty()) {
/* 234 */         nbttaglist.add(itemstack.save(new NBTTagCompound()));
/*     */       }
/*     */     } 
/*     */     
/* 238 */     nbttagcompound.set("Inventory", nbttaglist);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 242 */     super.a(nbttagcompound);
/* 243 */     setProfession(nbttagcompound.getInt("Profession"));
/* 244 */     this.riches = nbttagcompound.getInt("Riches");
/* 245 */     this.bK = nbttagcompound.getInt("Career");
/* 246 */     this.bL = nbttagcompound.getInt("CareerLevel");
/* 247 */     this.bH = nbttagcompound.getBoolean("Willing");
/* 248 */     if (nbttagcompound.hasKeyOfType("Offers", 10)) {
/* 249 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Offers");
/*     */       
/* 251 */       this.trades = new MerchantRecipeList(nbttagcompound1);
/*     */     } 
/*     */     
/* 254 */     NBTTagList nbttaglist = nbttagcompound.getList("Inventory", 10);
/*     */     
/* 256 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 257 */       ItemStack itemstack = new ItemStack(nbttaglist.get(i));
/*     */       
/* 259 */       if (!itemstack.isEmpty()) {
/* 260 */         this.inventory.a(itemstack);
/*     */       }
/*     */     } 
/*     */     
/* 264 */     m(true);
/* 265 */     dw();
/*     */   }
/*     */   
/*     */   protected boolean isTypeNotPersistent() {
/* 269 */     return false;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/* 273 */     return do_() ? SoundEffects.io : SoundEffects.ik;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 277 */     return SoundEffects.im;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 281 */     return SoundEffects.il;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 286 */     return LootTables.at;
/*     */   }
/*     */   
/*     */   public void setProfession(int i) {
/* 290 */     this.datawatcher.set(bz, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getProfession() {
/* 294 */     return Math.max(((Integer)this.datawatcher.<Integer>get(bz)).intValue() % 6, 0);
/*     */   }
/*     */   
/*     */   public boolean isInLove() {
/* 298 */     return this.bB;
/*     */   }
/*     */   
/*     */   public void p(boolean flag) {
/* 302 */     this.bB = flag;
/*     */   }
/*     */   
/*     */   public void q(boolean flag) {
/* 306 */     this.bC = flag;
/*     */   }
/*     */   
/*     */   public boolean dn() {
/* 310 */     return this.bC;
/*     */   }
/*     */   
/*     */   public void a(@Nullable EntityLiving entityliving) {
/* 314 */     super.a(entityliving);
/* 315 */     if (this.village != null && entityliving != null) {
/* 316 */       this.village.a(entityliving);
/* 317 */       if (entityliving instanceof EntityHuman) {
/* 318 */         byte b0 = -1;
/*     */         
/* 320 */         if (isBaby()) {
/* 321 */           b0 = -3;
/*     */         }
/*     */         
/* 324 */         this.village.a(entityliving.getName(), b0);
/* 325 */         if (isAlive()) {
/* 326 */           this.world.broadcastEntityEffect(this, (byte)13);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 334 */     if (this.village != null) {
/* 335 */       Entity entity = damagesource.getEntity();
/*     */       
/* 337 */       if (entity != null) {
/* 338 */         if (entity instanceof EntityHuman) {
/* 339 */           this.village.a(entity.getName(), -2);
/* 340 */         } else if (entity instanceof IMonster) {
/* 341 */           this.village.h();
/*     */         } 
/*     */       } else {
/* 344 */         EntityHuman entityhuman = this.world.findNearbyPlayer(this, 16.0D);
/*     */         
/* 346 */         if (entityhuman != null) {
/* 347 */           this.village.h();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 352 */     super.die(damagesource);
/*     */   }
/*     */   
/*     */   public void setTradingPlayer(@Nullable EntityHuman entityhuman) {
/* 356 */     this.tradingPlayer = entityhuman;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityHuman getTrader() {
/* 361 */     return this.tradingPlayer;
/*     */   }
/*     */   
/*     */   public boolean do_() {
/* 365 */     return (this.tradingPlayer != null);
/*     */   }
/*     */   
/*     */   public boolean r(boolean flag) {
/* 369 */     if (!this.bH && flag && dr()) {
/* 370 */       boolean flag1 = false;
/*     */       
/* 372 */       for (int i = 0; i < this.inventory.getSize(); i++) {
/* 373 */         ItemStack itemstack = this.inventory.getItem(i);
/*     */         
/* 375 */         if (!itemstack.isEmpty()) {
/* 376 */           if (itemstack.getItem() == Items.BREAD && itemstack.getCount() >= 3) {
/* 377 */             flag1 = true;
/* 378 */             this.inventory.splitStack(i, 3);
/* 379 */           } else if ((itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT) && itemstack.getCount() >= 12) {
/* 380 */             flag1 = true;
/* 381 */             this.inventory.splitStack(i, 12);
/*     */           } 
/*     */         }
/*     */         
/* 385 */         if (flag1) {
/* 386 */           this.world.broadcastEntityEffect(this, (byte)18);
/* 387 */           this.bH = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 393 */     return this.bH;
/*     */   }
/*     */   
/*     */   public void s(boolean flag) {
/* 397 */     this.bH = flag;
/*     */   }
/*     */   
/*     */   public void a(MerchantRecipe merchantrecipe) {
/* 401 */     merchantrecipe.increaseUses();
/* 402 */     this.a_ = -C();
/* 403 */     a(SoundEffects.ip, cq(), cr());
/* 404 */     int i = 3 + this.random.nextInt(4);
/*     */     
/* 406 */     if (merchantrecipe.e() == 1 || this.random.nextInt(5) == 0) {
/* 407 */       this.bF = 40;
/* 408 */       this.bG = true;
/* 409 */       this.bH = true;
/* 410 */       if (this.tradingPlayer != null) {
/* 411 */         this.bJ = this.tradingPlayer.getName();
/*     */       } else {
/* 413 */         this.bJ = null;
/*     */       } 
/*     */       
/* 416 */       i += 5;
/*     */     } 
/*     */     
/* 419 */     if (merchantrecipe.getBuyItem1().getItem() == Items.EMERALD) {
/* 420 */       this.riches += merchantrecipe.getBuyItem1().getCount();
/*     */     }
/*     */     
/* 423 */     if (merchantrecipe.j()) {
/* 424 */       this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY + 0.5D, this.locZ, i));
/*     */     }
/*     */     
/* 427 */     if (this.tradingPlayer instanceof EntityPlayer) {
/* 428 */       CriterionTriggers.r.a((EntityPlayer)this.tradingPlayer, this, merchantrecipe.getBuyItem3());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack) {
/* 434 */     if (!this.world.isClientSide && this.a_ > -C() + 20) {
/* 435 */       this.a_ = -C();
/* 436 */       a(itemstack.isEmpty() ? SoundEffects.in : SoundEffects.ip, cq(), cr());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MerchantRecipeList getOffers(EntityHuman entityhuman) {
/* 443 */     if (this.trades == null) {
/* 444 */       dx();
/*     */     }
/*     */     
/* 447 */     return this.trades;
/*     */   }
/*     */   
/*     */   private void dx() {
/* 451 */     IMerchantRecipeOption[][][] aentityvillager_imerchantrecipeoption = bP[getProfession()];
/*     */     
/* 453 */     if (this.bK != 0 && this.bL != 0) {
/* 454 */       this.bL++;
/*     */     } else {
/* 456 */       this.bK = this.random.nextInt(aentityvillager_imerchantrecipeoption.length) + 1;
/* 457 */       this.bL = 1;
/*     */     } 
/*     */     
/* 460 */     if (this.trades == null) {
/* 461 */       this.trades = new MerchantRecipeList();
/*     */     }
/*     */     
/* 464 */     int i = this.bK - 1;
/* 465 */     int j = this.bL - 1;
/*     */     
/* 467 */     if (i >= 0 && i < aentityvillager_imerchantrecipeoption.length) {
/* 468 */       IMerchantRecipeOption[][] aentityvillager_imerchantrecipeoption1 = aentityvillager_imerchantrecipeoption[i];
/*     */       
/* 470 */       if (j >= 0 && j < aentityvillager_imerchantrecipeoption1.length) {
/* 471 */         IMerchantRecipeOption[] aentityvillager_imerchantrecipeoption2 = aentityvillager_imerchantrecipeoption1[j];
/* 472 */         IMerchantRecipeOption[] aentityvillager_imerchantrecipeoption3 = aentityvillager_imerchantrecipeoption2;
/* 473 */         int k = aentityvillager_imerchantrecipeoption2.length;
/*     */         
/* 475 */         for (int l = 0; l < k; l++) {
/* 476 */           IMerchantRecipeOption entityvillager_imerchantrecipeoption = aentityvillager_imerchantrecipeoption3[l];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 482 */           MerchantRecipeList list = new MerchantRecipeList();
/* 483 */           entityvillager_imerchantrecipeoption.a(this, list, this.random);
/* 484 */           for (MerchantRecipe recipe : list) {
/* 485 */             VillagerAcquireTradeEvent event = new VillagerAcquireTradeEvent((Villager)getBukkitEntity(), (MerchantRecipe)recipe.asBukkit());
/* 486 */             Bukkit.getPluginManager().callEvent((Event)event);
/* 487 */             if (!event.isCancelled()) {
/* 488 */               this.trades.add(CraftMerchantRecipe.fromBukkit(event.getRecipe()).toMinecraft());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public World u_() {
/* 499 */     return this.world;
/*     */   }
/*     */   
/*     */   public BlockPosition v_() {
/* 503 */     return new BlockPosition(this);
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getScoreboardDisplayName() {
/* 507 */     ScoreboardTeamBase scoreboardteambase = aY();
/* 508 */     String s = getCustomName();
/*     */     
/* 510 */     if (s != null && !s.isEmpty()) {
/* 511 */       ChatComponentText chatcomponenttext = new ChatComponentText(ScoreboardTeam.getPlayerDisplayName(scoreboardteambase, s));
/*     */       
/* 513 */       chatcomponenttext.getChatModifier().setChatHoverable(bv());
/* 514 */       chatcomponenttext.getChatModifier().setInsertion(bn());
/* 515 */       return chatcomponenttext;
/*     */     } 
/* 517 */     if (this.trades == null) {
/* 518 */       dx();
/*     */     }
/*     */     
/* 521 */     String s1 = null;
/*     */     
/* 523 */     switch (getProfession()) {
/*     */       case 0:
/* 525 */         if (this.bK == 1) {
/* 526 */           s1 = "farmer"; break;
/* 527 */         }  if (this.bK == 2) {
/* 528 */           s1 = "fisherman"; break;
/* 529 */         }  if (this.bK == 3) {
/* 530 */           s1 = "shepherd"; break;
/* 531 */         }  if (this.bK == 4) {
/* 532 */           s1 = "fletcher";
/*     */         }
/*     */         break;
/*     */       
/*     */       case 1:
/* 537 */         if (this.bK == 1) {
/* 538 */           s1 = "librarian"; break;
/* 539 */         }  if (this.bK == 2) {
/* 540 */           s1 = "cartographer";
/*     */         }
/*     */         break;
/*     */       
/*     */       case 2:
/* 545 */         s1 = "cleric";
/*     */         break;
/*     */       
/*     */       case 3:
/* 549 */         if (this.bK == 1) {
/* 550 */           s1 = "armor"; break;
/* 551 */         }  if (this.bK == 2) {
/* 552 */           s1 = "weapon"; break;
/* 553 */         }  if (this.bK == 3) {
/* 554 */           s1 = "tool";
/*     */         }
/*     */         break;
/*     */       
/*     */       case 4:
/* 559 */         if (this.bK == 1) {
/* 560 */           s1 = "butcher"; break;
/* 561 */         }  if (this.bK == 2) {
/* 562 */           s1 = "leather";
/*     */         }
/*     */         break;
/*     */       
/*     */       case 5:
/* 567 */         s1 = "nitwit";
/*     */         break;
/*     */     } 
/* 570 */     if (s1 != null) {
/* 571 */       ChatMessage chatmessage = new ChatMessage("entity.Villager." + s1, new Object[0]);
/*     */       
/* 573 */       chatmessage.getChatModifier().setChatHoverable(bv());
/* 574 */       chatmessage.getChatModifier().setInsertion(bn());
/* 575 */       if (scoreboardteambase != null) {
/* 576 */         chatmessage.getChatModifier().setColor(scoreboardteambase.getColor());
/*     */       }
/*     */       
/* 579 */       return chatmessage;
/*     */     } 
/* 581 */     return super.getScoreboardDisplayName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 587 */     return isBaby() ? 0.81F : 1.62F;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 592 */     return a(difficultydamagescaler, groupdataentity, true);
/*     */   }
/*     */   
/*     */   public GroupDataEntity a(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity, boolean flag) {
/* 596 */     groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
/* 597 */     if (flag) {
/* 598 */       setProfession(this.world.random.nextInt(6));
/*     */     }
/*     */     
/* 601 */     dw();
/* 602 */     dx();
/* 603 */     return groupdataentity;
/*     */   }
/*     */   
/*     */   public void dp() {
/* 607 */     this.bM = true;
/*     */   }
/*     */   
/*     */   public EntityVillager b(EntityAgeable entityageable) {
/* 611 */     EntityVillager entityvillager = new EntityVillager(this.world);
/*     */     
/* 613 */     entityvillager.prepare(this.world.D(new BlockPosition(entityvillager)), (GroupDataEntity)null);
/* 614 */     return entityvillager;
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 618 */     return false;
/*     */   }
/*     */   
/*     */   public void onLightningStrike(EntityLightning entitylightning) {
/* 622 */     if (!this.world.isClientSide && !this.dead) {
/* 623 */       EntityWitch entitywitch = new EntityWitch(this.world);
/*     */       
/* 625 */       entitywitch.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 626 */       entitywitch.prepare(this.world.D(new BlockPosition(entitywitch)), (GroupDataEntity)null);
/* 627 */       entitywitch.setNoAI(isNoAI());
/* 628 */       if (hasCustomName()) {
/* 629 */         entitywitch.setCustomName(getCustomName());
/* 630 */         entitywitch.setCustomNameVisible(getCustomNameVisible());
/*     */       } 
/*     */       
/* 633 */       this.world.addEntity(entitywitch);
/* 634 */       die();
/*     */     } 
/*     */   }
/*     */   
/*     */   public InventorySubcontainer dq() {
/* 639 */     return this.inventory;
/*     */   }
/*     */   
/*     */   protected void a(EntityItem entityitem) {
/* 643 */     ItemStack itemstack = entityitem.getItemStack();
/* 644 */     Item item = itemstack.getItem();
/*     */     
/* 646 */     if (a(item)) {
/* 647 */       ItemStack itemstack1 = this.inventory.a(itemstack);
/*     */       
/* 649 */       if (itemstack1.isEmpty()) {
/* 650 */         entityitem.die();
/*     */       } else {
/* 652 */         itemstack.setCount(itemstack1.getCount());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(Item item) {
/* 659 */     return !(item != Items.BREAD && item != Items.POTATO && item != Items.CARROT && item != Items.WHEAT && item != Items.WHEAT_SEEDS && item != Items.BEETROOT && item != Items.BEETROOT_SEEDS);
/*     */   }
/*     */   
/*     */   public boolean dr() {
/* 663 */     return m(1);
/*     */   }
/*     */   
/*     */   public boolean ds() {
/* 667 */     return m(2);
/*     */   }
/*     */   
/*     */   public boolean dt() {
/* 671 */     boolean flag = (getProfession() == 0);
/*     */     
/* 673 */     return flag ? (!m(5)) : (!m(1));
/*     */   }
/*     */   
/*     */   private boolean m(int i) {
/* 677 */     boolean flag = (getProfession() == 0);
/*     */     
/* 679 */     for (int j = 0; j < this.inventory.getSize(); j++) {
/* 680 */       ItemStack itemstack = this.inventory.getItem(j);
/*     */       
/* 682 */       if (!itemstack.isEmpty()) {
/* 683 */         if ((itemstack.getItem() == Items.BREAD && itemstack.getCount() >= 3 * i) || (itemstack.getItem() == Items.POTATO && itemstack.getCount() >= 12 * i) || (itemstack.getItem() == Items.CARROT && itemstack.getCount() >= 12 * i) || (itemstack.getItem() == Items.BEETROOT && itemstack.getCount() >= 12 * i)) {
/* 684 */           return true;
/*     */         }
/*     */         
/* 687 */         if (flag && itemstack.getItem() == Items.WHEAT && itemstack.getCount() >= 9 * i) {
/* 688 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 693 */     return false;
/*     */   }
/*     */   
/*     */   public boolean du() {
/* 697 */     for (int i = 0; i < this.inventory.getSize(); i++) {
/* 698 */       ItemStack itemstack = this.inventory.getItem(i);
/*     */       
/* 700 */       if (!itemstack.isEmpty() && (itemstack.getItem() == Items.WHEAT_SEEDS || itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.BEETROOT_SEEDS)) {
/* 701 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 705 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(int i, ItemStack itemstack) {
/* 709 */     if (super.c(i, itemstack)) {
/* 710 */       return true;
/*     */     }
/* 712 */     int j = i - 300;
/*     */     
/* 714 */     if (j >= 0 && j < this.inventory.getSize()) {
/* 715 */       this.inventory.setItem(j, itemstack);
/* 716 */       return true;
/*     */     } 
/* 718 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 724 */     return b(entityageable);
/*     */   }
/*     */   
/*     */   static class MerchantRecipeOptionProcess
/*     */     implements IMerchantRecipeOption {
/*     */     public ItemStack a;
/*     */     public EntityVillager.MerchantOptionRandomRange b;
/*     */     public ItemStack c;
/*     */     public EntityVillager.MerchantOptionRandomRange d;
/*     */     
/*     */     public MerchantRecipeOptionProcess(Item item, EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange, Item item1, EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange1) {
/* 735 */       this.a = new ItemStack(item);
/* 736 */       this.b = entityvillager_merchantoptionrandomrange;
/* 737 */       this.c = new ItemStack(item1);
/* 738 */       this.d = entityvillager_merchantoptionrandomrange1;
/*     */     }
/*     */     
/*     */     public void a(IMerchant imerchant, MerchantRecipeList merchantrecipelist, Random random) {
/* 742 */       int i = this.b.a(random);
/* 743 */       int j = this.d.a(random);
/*     */       
/* 745 */       merchantrecipelist.add(new MerchantRecipe(new ItemStack(this.a.getItem(), i, this.a.getData()), new ItemStack(Items.EMERALD), new ItemStack(this.c.getItem(), j, this.c.getData())));
/*     */     }
/*     */   }
/*     */   
/*     */   static class h
/*     */     implements IMerchantRecipeOption {
/*     */     public EntityVillager.MerchantOptionRandomRange a;
/*     */     public String b;
/*     */     public MapIcon.Type c;
/*     */     
/*     */     public h(EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange, String s, MapIcon.Type mapicon_type) {
/* 756 */       this.a = entityvillager_merchantoptionrandomrange;
/* 757 */       this.b = s;
/* 758 */       this.c = mapicon_type;
/*     */     }
/*     */     
/*     */     public void a(IMerchant imerchant, MerchantRecipeList merchantrecipelist, Random random) {
/* 762 */       int i = this.a.a(random);
/* 763 */       World world = imerchant.u_();
/* 764 */       BlockPosition blockposition = world.a(this.b, imerchant.v_(), true);
/*     */       
/* 766 */       if (blockposition != null) {
/* 767 */         ItemStack itemstack = ItemWorldMap.a(world, blockposition.getX(), blockposition.getZ(), (byte)2, true, true);
/*     */         
/* 769 */         ItemWorldMap.a(world, itemstack);
/* 770 */         WorldMap.a(itemstack, blockposition, "+", this.c);
/* 771 */         itemstack.f("filled_map." + this.b.toLowerCase(Locale.ROOT));
/* 772 */         merchantrecipelist.add(new MerchantRecipe(new ItemStack(Items.EMERALD, i), new ItemStack(Items.COMPASS), itemstack));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class MerchantRecipeOptionBook
/*     */     implements IMerchantRecipeOption
/*     */   {
/*     */     public void a(IMerchant imerchant, MerchantRecipeList merchantrecipelist, Random random) {
/* 783 */       Enchantment enchantment = Enchantment.enchantments.a(random);
/* 784 */       int i = MathHelper.nextInt(random, enchantment.getStartLevel(), enchantment.getMaxLevel());
/* 785 */       ItemStack itemstack = ItemEnchantedBook.a(new WeightedRandomEnchant(enchantment, i));
/* 786 */       int j = 2 + random.nextInt(5 + i * 10) + 3 * i;
/*     */       
/* 788 */       if (enchantment.isTreasure()) {
/* 789 */         j *= 2;
/*     */       }
/*     */       
/* 792 */       if (j > 64) {
/* 793 */         j = 64;
/*     */       }
/*     */       
/* 796 */       merchantrecipelist.add(new MerchantRecipe(new ItemStack(Items.BOOK), new ItemStack(Items.EMERALD, j), itemstack));
/*     */     }
/*     */   }
/*     */   
/*     */   static class MerchantRecipeOptionEnchant
/*     */     implements IMerchantRecipeOption {
/*     */     public ItemStack a;
/*     */     public EntityVillager.MerchantOptionRandomRange b;
/*     */     
/*     */     public MerchantRecipeOptionEnchant(Item item, EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange) {
/* 806 */       this.a = new ItemStack(item);
/* 807 */       this.b = entityvillager_merchantoptionrandomrange;
/*     */     }
/*     */     
/*     */     public void a(IMerchant imerchant, MerchantRecipeList merchantrecipelist, Random random) {
/* 811 */       int i = 1;
/*     */       
/* 813 */       if (this.b != null) {
/* 814 */         i = this.b.a(random);
/*     */       }
/*     */       
/* 817 */       ItemStack itemstack = new ItemStack(Items.EMERALD, i, 0);
/* 818 */       ItemStack itemstack1 = EnchantmentManager.a(random, new ItemStack(this.a.getItem(), 1, this.a.getData()), 5 + random.nextInt(15), false);
/*     */       
/* 820 */       merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
/*     */     }
/*     */   }
/*     */   
/*     */   static class MerchantRecipeOptionSell
/*     */     implements IMerchantRecipeOption {
/*     */     public ItemStack a;
/*     */     public EntityVillager.MerchantOptionRandomRange b;
/*     */     
/*     */     public MerchantRecipeOptionSell(Item item, EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange) {
/* 830 */       this.a = new ItemStack(item);
/* 831 */       this.b = entityvillager_merchantoptionrandomrange;
/*     */     }
/*     */     
/*     */     public MerchantRecipeOptionSell(ItemStack itemstack, EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange) {
/* 835 */       this.a = itemstack;
/* 836 */       this.b = entityvillager_merchantoptionrandomrange;
/*     */     }
/*     */     public void a(IMerchant imerchant, MerchantRecipeList merchantrecipelist, Random random) {
/*     */       ItemStack itemstack, itemstack1;
/* 840 */       int i = 1;
/*     */       
/* 842 */       if (this.b != null) {
/* 843 */         i = this.b.a(random);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 849 */       if (i < 0) {
/* 850 */         itemstack = new ItemStack(Items.EMERALD);
/* 851 */         itemstack1 = new ItemStack(this.a.getItem(), -i, this.a.getData());
/*     */       } else {
/* 853 */         itemstack = new ItemStack(Items.EMERALD, i, 0);
/* 854 */         itemstack1 = new ItemStack(this.a.getItem(), 1, this.a.getData());
/*     */       } 
/*     */       
/* 857 */       merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
/*     */     }
/*     */   }
/*     */   
/*     */   static class MerchantRecipeOptionBuy
/*     */     implements IMerchantRecipeOption {
/*     */     public Item a;
/*     */     public EntityVillager.MerchantOptionRandomRange b;
/*     */     
/*     */     public MerchantRecipeOptionBuy(Item item, EntityVillager.MerchantOptionRandomRange entityvillager_merchantoptionrandomrange) {
/* 867 */       this.a = item;
/* 868 */       this.b = entityvillager_merchantoptionrandomrange;
/*     */     }
/*     */     
/*     */     public void a(IMerchant imerchant, MerchantRecipeList merchantrecipelist, Random random) {
/* 872 */       int i = 1;
/*     */       
/* 874 */       if (this.b != null) {
/* 875 */         i = this.b.a(random);
/*     */       }
/*     */       
/* 878 */       merchantrecipelist.add(new MerchantRecipe(new ItemStack(this.a, i, 0), Items.EMERALD));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class MerchantOptionRandomRange
/*     */     extends Tuple<Integer, Integer>
/*     */   {
/*     */     public MerchantOptionRandomRange(int i, int j) {
/* 890 */       super(Integer.valueOf(i), Integer.valueOf(j));
/* 891 */       if (j < i) {
/* 892 */         EntityVillager.by.warn("PriceRange({}, {}) invalid, {} smaller than {}", Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(j), Integer.valueOf(i));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(Random random) {
/* 898 */       return (a().intValue() >= b().intValue()) ? a().intValue() : (a().intValue() + random.nextInt(b().intValue() - a().intValue() + 1));
/*     */     }
/*     */   }
/*     */   
/*     */   static interface IMerchantRecipeOption {
/*     */     void a(IMerchant param1IMerchant, MerchantRecipeList param1MerchantRecipeList, Random param1Random);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */