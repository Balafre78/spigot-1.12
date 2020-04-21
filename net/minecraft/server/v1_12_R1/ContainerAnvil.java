/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryAnvil;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerAnvil extends Container {
/*  15 */   private static final Logger f = LogManager.getLogger();
/*  16 */   private final IInventory g = new InventoryCraftResult();
/*  17 */   private final IInventory h = new InventorySubcontainer("Repair", true, 2) {
/*     */       public void update() {
/*  19 */         super.update();
/*  20 */         ContainerAnvil.this.a(this);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private final World i;
/*     */   private final BlockPosition j;
/*     */   public int levelCost;
/*     */   private int k;
/*     */   public String renameText;
/*     */   private final EntityHuman m;
/*     */   private int lastLevelCost;
/*     */   private CraftInventoryView bukkitEntity;
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerAnvil(PlayerInventory playerinventory, final World world, final BlockPosition blockposition, EntityHuman entityhuman) {
/*  36 */     this.player = playerinventory;
/*  37 */     this.j = blockposition;
/*  38 */     this.i = world;
/*  39 */     this.m = entityhuman;
/*  40 */     a(new Slot(this.h, 0, 27, 47));
/*  41 */     a(new Slot(this.h, 1, 76, 47));
/*  42 */     a(new Slot(this.g, 2, 134, 47) {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  44 */             return false;
/*     */           }
/*     */           
/*     */           public boolean isAllowed(EntityHuman entityhuman) {
/*  48 */             return ((entityhuman.abilities.canInstantlyBuild || entityhuman.expLevel >= ContainerAnvil.this.levelCost) && ContainerAnvil.this.levelCost > 0 && hasItem());
/*     */           }
/*     */           
/*     */           public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/*  52 */             if (!entityhuman.abilities.canInstantlyBuild) {
/*  53 */               entityhuman.levelDown(-ContainerAnvil.this.levelCost);
/*     */             }
/*     */             
/*  56 */             ContainerAnvil.this.h.setItem(0, ItemStack.a);
/*  57 */             if (ContainerAnvil.this.k > 0) {
/*  58 */               ItemStack itemstack1 = ContainerAnvil.this.h.getItem(1);
/*     */               
/*  60 */               if (!itemstack1.isEmpty() && itemstack1.getCount() > ContainerAnvil.this.k) {
/*  61 */                 itemstack1.subtract(ContainerAnvil.this.k);
/*  62 */                 ContainerAnvil.this.h.setItem(1, itemstack1);
/*     */               } else {
/*  64 */                 ContainerAnvil.this.h.setItem(1, ItemStack.a);
/*     */               } 
/*     */             } else {
/*  67 */               ContainerAnvil.this.h.setItem(1, ItemStack.a);
/*     */             } 
/*     */             
/*  70 */             ContainerAnvil.this.levelCost = 0;
/*  71 */             IBlockData iblockdata = world.getType(blockposition);
/*     */             
/*  73 */             if (!entityhuman.abilities.canInstantlyBuild && !world.isClientSide && iblockdata.getBlock() == Blocks.ANVIL && entityhuman.getRandom().nextFloat() < 0.12F) {
/*  74 */               int i = ((Integer)iblockdata.<Integer>get(BlockAnvil.DAMAGE)).intValue();
/*     */               
/*  76 */               i++;
/*  77 */               if (i > 2) {
/*  78 */                 world.setAir(blockposition);
/*  79 */                 world.triggerEffect(1029, blockposition, 0);
/*     */               } else {
/*  81 */                 world.setTypeAndData(blockposition, iblockdata.set(BlockAnvil.DAMAGE, Integer.valueOf(i)), 2);
/*  82 */                 world.triggerEffect(1030, blockposition, 0);
/*     */               } 
/*  84 */             } else if (!world.isClientSide) {
/*  85 */               world.triggerEffect(1030, blockposition, 0);
/*     */             } 
/*     */             
/*  88 */             return itemstack;
/*     */           }
/*     */         });
/*     */     
/*     */     int i;
/*     */     
/*  94 */     for (i = 0; i < 3; i++) {
/*  95 */       for (int j = 0; j < 9; j++) {
/*  96 */         a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*     */       }
/*     */     } 
/*     */     
/* 100 */     for (i = 0; i < 9; i++) {
/* 101 */       a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 107 */     super.a(iinventory);
/* 108 */     if (iinventory == this.h) {
/* 109 */       e();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/* 115 */     ItemStack itemstack = this.h.getItem(0);
/*     */     
/* 117 */     this.levelCost = 1;
/* 118 */     int i = 0;
/* 119 */     byte b0 = 0;
/* 120 */     byte b1 = 0;
/*     */     
/* 122 */     if (itemstack.isEmpty()) {
/* 123 */       CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.a);
/* 124 */       this.levelCost = 0;
/*     */     } else {
/* 126 */       ItemStack itemstack1 = itemstack.cloneItemStack();
/* 127 */       ItemStack itemstack2 = this.h.getItem(1);
/* 128 */       Map<Enchantment, Integer> map = EnchantmentManager.a(itemstack1);
/* 129 */       int j = b0 + itemstack.getRepairCost() + (itemstack2.isEmpty() ? 0 : itemstack2.getRepairCost());
/*     */       
/* 131 */       this.k = 0;
/* 132 */       if (!itemstack2.isEmpty()) {
/* 133 */         boolean flag = (itemstack2.getItem() == Items.ENCHANTED_BOOK && !ItemEnchantedBook.h(itemstack2).isEmpty());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 138 */         if (itemstack1.f() && itemstack1.getItem().a(itemstack, itemstack2)) {
/* 139 */           int k = Math.min(itemstack1.i(), itemstack1.k() / 4);
/* 140 */           if (k <= 0) {
/* 141 */             CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.a);
/* 142 */             this.levelCost = 0;
/*     */             return;
/*     */           } 
/*     */           int l;
/* 146 */           for (l = 0; k > 0 && l < itemstack2.getCount(); l++) {
/* 147 */             int i1 = itemstack1.i() - k;
/* 148 */             itemstack1.setData(i1);
/* 149 */             i++;
/* 150 */             k = Math.min(itemstack1.i(), itemstack1.k() / 4);
/*     */           } 
/*     */           
/* 153 */           this.k = l;
/*     */         } else {
/* 155 */           if (!flag && (itemstack1.getItem() != itemstack2.getItem() || !itemstack1.f())) {
/* 156 */             CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.a);
/* 157 */             this.levelCost = 0;
/*     */             
/*     */             return;
/*     */           } 
/* 161 */           if (itemstack1.f() && !flag) {
/* 162 */             int k = itemstack.k() - itemstack.i();
/* 163 */             int l = itemstack2.k() - itemstack2.i();
/* 164 */             int i1 = l + itemstack1.k() * 12 / 100;
/* 165 */             int j1 = k + i1;
/* 166 */             int k1 = itemstack1.k() - j1;
/*     */             
/* 168 */             if (k1 < 0) {
/* 169 */               k1 = 0;
/*     */             }
/*     */             
/* 172 */             if (k1 < itemstack1.getData()) {
/* 173 */               itemstack1.setData(k1);
/* 174 */               i += 2;
/*     */             } 
/*     */           } 
/*     */           
/* 178 */           Map<Enchantment, Integer> map1 = EnchantmentManager.a(itemstack2);
/* 179 */           boolean flag1 = false;
/* 180 */           boolean flag2 = false;
/* 181 */           Iterator<Enchantment> iterator = map1.keySet().iterator();
/*     */           
/* 183 */           while (iterator.hasNext()) {
/* 184 */             Enchantment enchantment = iterator.next();
/*     */             
/* 186 */             if (enchantment != null) {
/* 187 */               int l1 = map.containsKey(enchantment) ? ((Integer)map.get(enchantment)).intValue() : 0;
/* 188 */               int i2 = ((Integer)map1.get(enchantment)).intValue();
/*     */               
/* 190 */               i2 = (l1 == i2) ? (i2 + 1) : Math.max(i2, l1);
/* 191 */               boolean flag3 = enchantment.canEnchant(itemstack);
/*     */               
/* 193 */               if (this.m.abilities.canInstantlyBuild || itemstack.getItem() == Items.ENCHANTED_BOOK) {
/* 194 */                 flag3 = true;
/*     */               }
/*     */               
/* 197 */               Iterator<Enchantment> iterator1 = map.keySet().iterator();
/*     */               
/* 199 */               while (iterator1.hasNext()) {
/* 200 */                 Enchantment enchantment1 = iterator1.next();
/*     */                 
/* 202 */                 if (enchantment1 != enchantment && !enchantment.c(enchantment1)) {
/* 203 */                   flag3 = false;
/* 204 */                   i++;
/*     */                 } 
/*     */               } 
/*     */               
/* 208 */               if (!flag3) {
/* 209 */                 flag2 = true; continue;
/*     */               } 
/* 211 */               flag1 = true;
/* 212 */               if (i2 > enchantment.getMaxLevel()) {
/* 213 */                 i2 = enchantment.getMaxLevel();
/*     */               }
/*     */               
/* 216 */               map.put(enchantment, Integer.valueOf(i2));
/* 217 */               int j2 = 0;
/*     */               
/* 219 */               switch (enchantment.e()) {
/*     */                 case null:
/* 221 */                   j2 = 1;
/*     */                   break;
/*     */                 
/*     */                 case UNCOMMON:
/* 225 */                   j2 = 2;
/*     */                   break;
/*     */                 
/*     */                 case RARE:
/* 229 */                   j2 = 4;
/*     */                   break;
/*     */                 
/*     */                 case VERY_RARE:
/* 233 */                   j2 = 8;
/*     */                   break;
/*     */               } 
/* 236 */               if (flag) {
/* 237 */                 j2 = Math.max(1, j2 / 2);
/*     */               }
/*     */               
/* 240 */               i += j2 * i2;
/* 241 */               if (itemstack.getCount() > 1) {
/* 242 */                 i = 40;
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 248 */           if (flag2 && !flag1) {
/* 249 */             CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.a);
/* 250 */             this.levelCost = 0;
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 256 */       if (StringUtils.isBlank(this.renameText)) {
/* 257 */         if (itemstack.hasName()) {
/* 258 */           b1 = 1;
/* 259 */           i += b1;
/* 260 */           itemstack1.s();
/*     */         } 
/* 262 */       } else if (!this.renameText.equals(itemstack.getName())) {
/* 263 */         b1 = 1;
/* 264 */         i += b1;
/* 265 */         itemstack1.g(this.renameText);
/*     */       } 
/*     */       
/* 268 */       this.levelCost = j + i;
/* 269 */       if (i <= 0) {
/* 270 */         itemstack1 = ItemStack.a;
/*     */       }
/*     */       
/* 273 */       if (b1 == i && b1 > 0 && this.levelCost >= 40) {
/* 274 */         this.levelCost = 39;
/*     */       }
/*     */       
/* 277 */       if (this.levelCost >= 40 && !this.m.abilities.canInstantlyBuild) {
/* 278 */         itemstack1 = ItemStack.a;
/*     */       }
/*     */       
/* 281 */       if (!itemstack1.isEmpty()) {
/* 282 */         int k2 = itemstack1.getRepairCost();
/*     */         
/* 284 */         if (!itemstack2.isEmpty() && k2 < itemstack2.getRepairCost()) {
/* 285 */           k2 = itemstack2.getRepairCost();
/*     */         }
/*     */         
/* 288 */         if (b1 != i || b1 == 0) {
/* 289 */           k2 = k2 * 2 + 1;
/*     */         }
/*     */         
/* 292 */         itemstack1.setRepairCost(k2);
/* 293 */         EnchantmentManager.a(map, itemstack1);
/*     */       } 
/*     */       
/* 296 */       CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), itemstack1);
/* 297 */       b();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addSlotListener(ICrafting icrafting) {
/* 302 */     super.addSlotListener(icrafting);
/* 303 */     icrafting.setContainerData(this, 0, this.levelCost);
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 307 */     super.b(entityhuman);
/* 308 */     if (!this.i.isClientSide) {
/* 309 */       a(entityhuman, this.i, this.h);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 314 */     if (!this.checkReachable) return true; 
/* 315 */     return (this.i.getType(this.j).getBlock() != Blocks.ANVIL) ? false : ((entityhuman.d(this.j.getX() + 0.5D, this.j.getY() + 0.5D, this.j.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/* 319 */     ItemStack itemstack = ItemStack.a;
/* 320 */     Slot slot = this.c.get(i);
/*     */     
/* 322 */     if (slot != null && slot.hasItem()) {
/* 323 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 325 */       itemstack = itemstack1.cloneItemStack();
/* 326 */       if (i == 2) {
/* 327 */         if (!a(itemstack1, 3, 39, true)) {
/* 328 */           return ItemStack.a;
/*     */         }
/*     */         
/* 331 */         slot.a(itemstack1, itemstack);
/* 332 */       } else if (i != 0 && i != 1) {
/* 333 */         if (i >= 3 && i < 39 && !a(itemstack1, 0, 2, false)) {
/* 334 */           return ItemStack.a;
/*     */         }
/* 336 */       } else if (!a(itemstack1, 3, 39, false)) {
/* 337 */         return ItemStack.a;
/*     */       } 
/*     */       
/* 340 */       if (itemstack1.isEmpty()) {
/* 341 */         slot.set(ItemStack.a);
/*     */       } else {
/* 343 */         slot.f();
/*     */       } 
/*     */       
/* 346 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 347 */         return ItemStack.a;
/*     */       }
/*     */       
/* 350 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 353 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void a(String s) {
/* 357 */     this.renameText = s;
/* 358 */     if (getSlot(2).hasItem()) {
/* 359 */       ItemStack itemstack = getSlot(2).getItem();
/*     */       
/* 361 */       if (StringUtils.isBlank(s)) {
/* 362 */         itemstack.s();
/*     */       } else {
/* 364 */         itemstack.g(this.renameText);
/*     */       } 
/*     */     } 
/*     */     
/* 368 */     e();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/* 374 */     super.b();
/*     */     
/* 376 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 377 */       ICrafting icrafting = this.listeners.get(i);
/*     */       
/* 379 */       if (this.lastLevelCost != this.levelCost) {
/* 380 */         icrafting.setContainerData(this, 0, this.levelCost);
/*     */       }
/*     */     } 
/*     */     
/* 384 */     this.lastLevelCost = this.levelCost;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 389 */     if (this.bukkitEntity != null) {
/* 390 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 393 */     CraftInventoryAnvil craftInventoryAnvil = new CraftInventoryAnvil(
/* 394 */         new Location((World)this.i.getWorld(), this.j.getX(), this.j.getY(), this.j.getZ()), this.h, this.g, this);
/* 395 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)craftInventoryAnvil, this);
/* 396 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */