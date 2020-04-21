/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryEnchanting;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.enchantments.EnchantmentOffer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*     */ import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ContainerEnchantTable extends Container {
/*  21 */   public IInventory enchantSlots = new InventorySubcontainer("Enchant", true, 2) {
/*     */       public int getMaxStackSize() {
/*  23 */         return 64;
/*     */       }
/*     */       
/*     */       public void update() {
/*  27 */         super.update();
/*  28 */         ContainerEnchantTable.this.a(this);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Location getLocation() {
/*  34 */         return new Location((World)ContainerEnchantTable.this.world.getWorld(), ContainerEnchantTable.this.position.getX(), ContainerEnchantTable.this.position.getY(), ContainerEnchantTable.this.position.getZ());
/*     */       }
/*     */     };
/*     */   
/*     */   public World world;
/*     */   private final BlockPosition position;
/*  40 */   private final Random l = new Random();
/*     */   public int f;
/*  42 */   public int[] costs = new int[3];
/*  43 */   public int[] h = new int[] { -1, -1, -1 };
/*  44 */   public int[] i = new int[] { -1, -1, -1 };
/*     */   
/*  46 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private Player player;
/*     */   
/*     */   public ContainerEnchantTable(PlayerInventory playerinventory, World world, BlockPosition blockposition) {
/*  51 */     this.world = world;
/*  52 */     this.position = blockposition;
/*  53 */     this.f = playerinventory.player.dg();
/*  54 */     a(new Slot(this.enchantSlots, 0, 15, 47) {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  56 */             return true;
/*     */           }
/*     */           
/*     */           public int getMaxStackSize() {
/*  60 */             return 1;
/*     */           }
/*     */         });
/*  63 */     a(new Slot(this.enchantSlots, 1, 35, 47) {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  65 */             return (itemstack.getItem() == Items.DYE && EnumColor.fromInvColorIndex(itemstack.getData()) == EnumColor.BLUE);
/*     */           }
/*     */         });
/*     */     
/*     */     int i;
/*     */     
/*  71 */     for (i = 0; i < 3; i++) {
/*  72 */       for (int j = 0; j < 9; j++) {
/*  73 */         a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/*  77 */     for (i = 0; i < 9; i++) {
/*  78 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */ 
/*     */     
/*  82 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(ICrafting icrafting) {
/*  87 */     icrafting.setContainerData(this, 0, this.costs[0]);
/*  88 */     icrafting.setContainerData(this, 1, this.costs[1]);
/*  89 */     icrafting.setContainerData(this, 2, this.costs[2]);
/*  90 */     icrafting.setContainerData(this, 3, this.f & 0xFFFFFFF0);
/*  91 */     icrafting.setContainerData(this, 4, this.h[0]);
/*  92 */     icrafting.setContainerData(this, 5, this.h[1]);
/*  93 */     icrafting.setContainerData(this, 6, this.h[2]);
/*  94 */     icrafting.setContainerData(this, 7, this.i[0]);
/*  95 */     icrafting.setContainerData(this, 8, this.i[1]);
/*  96 */     icrafting.setContainerData(this, 9, this.i[2]);
/*     */   }
/*     */   
/*     */   public void addSlotListener(ICrafting icrafting) {
/* 100 */     super.addSlotListener(icrafting);
/* 101 */     c(icrafting);
/*     */   }
/*     */   
/*     */   public void b() {
/* 105 */     super.b();
/*     */     
/* 107 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 108 */       ICrafting icrafting = this.listeners.get(i);
/*     */       
/* 110 */       c(icrafting);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 116 */     if (iinventory == this.enchantSlots) {
/* 117 */       ItemStack itemstack = iinventory.getItem(0);
/*     */ 
/*     */       
/* 120 */       if (!itemstack.isEmpty()) {
/* 121 */         if (!this.world.isClientSide) {
/* 122 */           int i = 0;
/*     */           
/*     */           int j;
/*     */           
/* 126 */           for (j = -1; j <= 1; j++) {
/* 127 */             for (int k = -1; k <= 1; k++) {
/* 128 */               if ((j != 0 || k != 0) && this.world.isEmpty(this.position.a(k, 0, j)) && this.world.isEmpty(this.position.a(k, 1, j))) {
/* 129 */                 if (this.world.getType(this.position.a(k * 2, 0, j * 2)).getBlock() == Blocks.BOOKSHELF) {
/* 130 */                   i++;
/*     */                 }
/*     */                 
/* 133 */                 if (this.world.getType(this.position.a(k * 2, 1, j * 2)).getBlock() == Blocks.BOOKSHELF) {
/* 134 */                   i++;
/*     */                 }
/*     */                 
/* 137 */                 if (k != 0 && j != 0) {
/* 138 */                   if (this.world.getType(this.position.a(k * 2, 0, j)).getBlock() == Blocks.BOOKSHELF) {
/* 139 */                     i++;
/*     */                   }
/*     */                   
/* 142 */                   if (this.world.getType(this.position.a(k * 2, 1, j)).getBlock() == Blocks.BOOKSHELF) {
/* 143 */                     i++;
/*     */                   }
/*     */                   
/* 146 */                   if (this.world.getType(this.position.a(k, 0, j * 2)).getBlock() == Blocks.BOOKSHELF) {
/* 147 */                     i++;
/*     */                   }
/*     */                   
/* 150 */                   if (this.world.getType(this.position.a(k, 1, j * 2)).getBlock() == Blocks.BOOKSHELF) {
/* 151 */                     i++;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 158 */           this.l.setSeed(this.f);
/*     */           
/* 160 */           for (j = 0; j < 3; j++) {
/* 161 */             this.costs[j] = EnchantmentManager.a(this.l, j, i, itemstack);
/* 162 */             this.h[j] = -1;
/* 163 */             this.i[j] = -1;
/* 164 */             if (this.costs[j] < j + 1) {
/* 165 */               this.costs[j] = 0;
/*     */             }
/*     */           } 
/*     */           
/* 169 */           for (j = 0; j < 3; j++) {
/* 170 */             if (this.costs[j] > 0) {
/* 171 */               List<WeightedRandomEnchant> list = a(itemstack, j, this.costs[j]);
/*     */               
/* 173 */               if (list != null && !list.isEmpty()) {
/* 174 */                 WeightedRandomEnchant weightedrandomenchant = list.get(this.l.nextInt(list.size()));
/*     */                 
/* 176 */                 this.h[j] = Enchantment.getId(weightedrandomenchant.enchantment);
/* 177 */                 this.i[j] = weightedrandomenchant.level;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 183 */           CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
/* 184 */           EnchantmentOffer[] offers = new EnchantmentOffer[3];
/* 185 */           for (j = 0; j < 3; j++) {
/* 186 */             Enchantment enchantment = (this.h[j] >= 0) ? Enchantment.getById(this.h[j]) : null;
/* 187 */             offers[j] = (enchantment != null) ? new EnchantmentOffer(enchantment, this.i[j], this.costs[j]) : null;
/*     */           } 
/*     */           
/* 190 */           PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(this.player, (InventoryView)getBukkitView(), this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)item, offers, i);
/* 191 */           event.setCancelled(!itemstack.canEnchant());
/* 192 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 194 */           if (event.isCancelled()) {
/* 195 */             for (j = 0; j < 3; j++) {
/* 196 */               this.costs[j] = 0;
/* 197 */               this.h[j] = -1;
/* 198 */               this.i[j] = -1;
/*     */             } 
/*     */             
/*     */             return;
/*     */           } 
/* 203 */           for (j = 0; j < 3; j++) {
/* 204 */             EnchantmentOffer offer = event.getOffers()[j];
/* 205 */             if (offer != null) {
/* 206 */               this.costs[j] = offer.getCost();
/* 207 */               this.h[j] = offer.getEnchantment().getId();
/* 208 */               this.i[j] = offer.getEnchantmentLevel();
/*     */             } else {
/* 210 */               this.costs[j] = 0;
/* 211 */               this.h[j] = -1;
/* 212 */               this.i[j] = -1;
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 217 */           b();
/*     */         } 
/*     */       } else {
/* 220 */         for (int i = 0; i < 3; i++) {
/* 221 */           this.costs[i] = 0;
/* 222 */           this.h[i] = -1;
/* 223 */           this.i[i] = -1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/* 231 */     ItemStack itemstack = this.enchantSlots.getItem(0);
/* 232 */     ItemStack itemstack1 = this.enchantSlots.getItem(1);
/* 233 */     int j = i + 1;
/*     */     
/* 235 */     if ((itemstack1.isEmpty() || itemstack1.getCount() < j) && !entityhuman.abilities.canInstantlyBuild)
/* 236 */       return false; 
/* 237 */     if (this.costs[i] > 0 && !itemstack.isEmpty() && ((entityhuman.expLevel >= j && entityhuman.expLevel >= this.costs[i]) || entityhuman.abilities.canInstantlyBuild)) {
/* 238 */       if (!this.world.isClientSide) {
/* 239 */         List<WeightedRandomEnchant> list = a(itemstack, i, this.costs[i]);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 244 */         boolean flag = (itemstack.getItem() == Items.BOOK);
/* 245 */         Map<Enchantment, Integer> enchants = new HashMap<>();
/* 246 */         for (WeightedRandomEnchant obj : list) {
/* 247 */           WeightedRandomEnchant instance = obj;
/* 248 */           enchants.put(Enchantment.getById(Enchantment.getId(instance.enchantment)), Integer.valueOf(instance.level));
/*     */         } 
/* 250 */         CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
/*     */         
/* 252 */         EnchantItemEvent event = new EnchantItemEvent((Player)entityhuman.getBukkitEntity(), (InventoryView)getBukkitView(), this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)item, this.costs[i], enchants, i);
/* 253 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 255 */         int level = event.getExpLevelCost();
/* 256 */         if (event.isCancelled() || (level > entityhuman.expLevel && !entityhuman.abilities.canInstantlyBuild) || event.getEnchantsToAdd().isEmpty()) {
/* 257 */           return false;
/*     */         }
/*     */         
/* 260 */         if (flag) {
/* 261 */           itemstack = new ItemStack(Items.ENCHANTED_BOOK);
/* 262 */           this.enchantSlots.setItem(0, itemstack);
/*     */         } 
/*     */         
/* 265 */         for (Map.Entry<Enchantment, Integer> entry : (Iterable<Map.Entry<Enchantment, Integer>>)event.getEnchantsToAdd().entrySet()) {
/*     */           try {
/* 267 */             if (flag) {
/* 268 */               int enchantId = ((Enchantment)entry.getKey()).getId();
/* 269 */               if (Enchantment.c(enchantId) == null) {
/*     */                 continue;
/*     */               }
/*     */               
/* 273 */               WeightedRandomEnchant weightedrandomenchant = new WeightedRandomEnchant(Enchantment.c(enchantId), ((Integer)entry.getValue()).intValue());
/* 274 */               ItemEnchantedBook.a(itemstack, weightedrandomenchant); continue;
/*     */             } 
/* 276 */             item.addUnsafeEnchantment(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */           }
/* 278 */           catch (IllegalArgumentException illegalArgumentException) {}
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 283 */         entityhuman.enchantDone(itemstack, j);
/*     */ 
/*     */ 
/*     */         
/* 287 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 288 */           itemstack1.subtract(j);
/* 289 */           if (itemstack1.isEmpty()) {
/* 290 */             this.enchantSlots.setItem(1, ItemStack.a);
/*     */           }
/*     */         } 
/*     */         
/* 294 */         entityhuman.b(StatisticList.W);
/* 295 */         if (entityhuman instanceof EntityPlayer) {
/* 296 */           CriterionTriggers.i.a((EntityPlayer)entityhuman, itemstack, j);
/*     */         }
/*     */         
/* 299 */         this.enchantSlots.update();
/* 300 */         this.f = entityhuman.dg();
/* 301 */         a(this.enchantSlots);
/* 302 */         this.world.a(null, this.position, SoundEffects.aR, SoundCategory.BLOCKS, 1.0F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/*     */ 
/*     */       
/* 306 */       return true;
/*     */     } 
/* 308 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<WeightedRandomEnchant> a(ItemStack itemstack, int i, int j) {
/* 313 */     this.l.setSeed((this.f + i));
/* 314 */     List<WeightedRandomEnchant> list = EnchantmentManager.b(this.l, itemstack, j, false);
/*     */     
/* 316 */     if (itemstack.getItem() == Items.BOOK && list.size() > 1) {
/* 317 */       list.remove(this.l.nextInt(list.size()));
/*     */     }
/*     */     
/* 320 */     return list;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 324 */     super.b(entityhuman);
/*     */     
/* 326 */     if (this.world == null) {
/* 327 */       this.world = entityhuman.getWorld();
/*     */     }
/*     */     
/* 330 */     if (!this.world.isClientSide) {
/* 331 */       a(entityhuman, entityhuman.world, this.enchantSlots);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 336 */     if (!this.checkReachable) return true; 
/* 337 */     return (this.world.getType(this.position).getBlock() != Blocks.ENCHANTING_TABLE) ? false : ((entityhuman.d(this.position.getX() + 0.5D, this.position.getY() + 0.5D, this.position.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/* 341 */     ItemStack itemstack = ItemStack.a;
/* 342 */     Slot slot = this.c.get(i);
/*     */     
/* 344 */     if (slot != null && slot.hasItem()) {
/* 345 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 347 */       itemstack = itemstack1.cloneItemStack();
/* 348 */       if (i == 0) {
/* 349 */         if (!a(itemstack1, 2, 38, true)) {
/* 350 */           return ItemStack.a;
/*     */         }
/* 352 */       } else if (i == 1) {
/* 353 */         if (!a(itemstack1, 2, 38, true)) {
/* 354 */           return ItemStack.a;
/*     */         }
/* 356 */       } else if (itemstack1.getItem() == Items.DYE && EnumColor.fromInvColorIndex(itemstack1.getData()) == EnumColor.BLUE) {
/* 357 */         if (!a(itemstack1, 1, 2, true)) {
/* 358 */           return ItemStack.a;
/*     */         }
/*     */       } else {
/* 361 */         if (((Slot)this.c.get(0)).hasItem() || !((Slot)this.c.get(0)).isAllowed(itemstack1)) {
/* 362 */           return ItemStack.a;
/*     */         }
/*     */         
/* 365 */         if (itemstack1.hasTag() && itemstack1.getCount() == 1) {
/* 366 */           ((Slot)this.c.get(0)).set(itemstack1.cloneItemStack());
/* 367 */           itemstack1.setCount(0);
/* 368 */         } else if (!itemstack1.isEmpty()) {
/*     */           
/* 370 */           ItemStack clone = itemstack1.cloneItemStack();
/* 371 */           clone.setCount(1);
/* 372 */           ((Slot)this.c.get(0)).set(clone);
/*     */           
/* 374 */           itemstack1.subtract(1);
/*     */         } 
/*     */       } 
/*     */       
/* 378 */       if (itemstack1.isEmpty()) {
/* 379 */         slot.set(ItemStack.a);
/*     */       } else {
/* 381 */         slot.f();
/*     */       } 
/*     */       
/* 384 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 385 */         return ItemStack.a;
/*     */       }
/*     */       
/* 388 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 391 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 397 */     if (this.bukkitEntity != null) {
/* 398 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 401 */     CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.enchantSlots);
/* 402 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/* 403 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerEnchantTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */