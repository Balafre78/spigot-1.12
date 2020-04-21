/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public abstract class Container {
/*  23 */   public NonNullList<ItemStack> b = NonNullList.a();
/*  24 */   public List<Slot> c = Lists.newArrayList();
/*     */   public int windowId;
/*  26 */   private int dragType = -1;
/*     */   private int g;
/*  28 */   private final Set<Slot> h = Sets.newHashSet();
/*  29 */   protected List<ICrafting> listeners = Lists.newArrayList();
/*  30 */   private final Set<EntityHuman> i = Sets.newHashSet();
/*     */   private int tickCount;
/*     */   public boolean checkReachable = true;
/*     */   
/*     */   public abstract InventoryView getBukkitView();
/*     */   
/*     */   public void transferTo(Container other, CraftHumanEntity player) {
/*  37 */     InventoryView source = getBukkitView(), destination = other.getBukkitView();
/*  38 */     ((CraftInventory)source.getTopInventory()).getInventory().onClose(player);
/*  39 */     ((CraftInventory)source.getBottomInventory()).getInventory().onClose(player);
/*  40 */     ((CraftInventory)destination.getTopInventory()).getInventory().onOpen(player);
/*  41 */     ((CraftInventory)destination.getBottomInventory()).getInventory().onOpen(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Slot a(Slot slot) {
/*  48 */     slot.rawSlotIndex = this.c.size();
/*  49 */     this.c.add(slot);
/*  50 */     this.b.add(ItemStack.a);
/*  51 */     return slot;
/*     */   }
/*     */   
/*     */   public void addSlotListener(ICrafting icrafting) {
/*  55 */     if (this.listeners.contains(icrafting)) {
/*  56 */       throw new IllegalArgumentException("Listener already listening");
/*     */     }
/*  58 */     this.listeners.add(icrafting);
/*  59 */     icrafting.a(this, a());
/*  60 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<ItemStack> a() {
/*  65 */     NonNullList<?> nonnulllist = NonNullList.a();
/*     */     
/*  67 */     for (int i = 0; i < this.c.size(); i++) {
/*  68 */       nonnulllist.add(((Slot)this.c.get(i)).getItem());
/*     */     }
/*     */     
/*  71 */     return (NonNullList)nonnulllist;
/*     */   }
/*     */   
/*     */   public void b() {
/*  75 */     for (int i = 0; i < this.c.size(); i++) {
/*  76 */       ItemStack itemstack = ((Slot)this.c.get(i)).getItem();
/*  77 */       ItemStack itemstack1 = this.b.get(i);
/*     */       
/*  79 */       if (!ItemStack.fastMatches(itemstack1, itemstack) || (this.tickCount % SpigotConfig.itemDirtyTicks == 0 && !ItemStack.matches(itemstack1, itemstack))) {
/*  80 */         itemstack1 = itemstack.isEmpty() ? ItemStack.a : itemstack.cloneItemStack();
/*  81 */         this.b.set(i, itemstack1);
/*     */         
/*  83 */         for (int j = 0; j < this.listeners.size(); j++) {
/*  84 */           ((ICrafting)this.listeners.get(j)).a(this, i, itemstack1);
/*     */         }
/*     */       } 
/*     */     } 
/*  88 */     this.tickCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Slot getSlot(IInventory iinventory, int i) {
/*  98 */     for (int j = 0; j < this.c.size(); j++) {
/*  99 */       Slot slot = this.c.get(j);
/*     */       
/* 101 */       if (slot.a(iinventory, i)) {
/* 102 */         return slot;
/*     */       }
/*     */     } 
/*     */     
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   public Slot getSlot(int i) {
/* 110 */     return this.c.get(i);
/*     */   }
/*     */   
/*     */   public ItemStack b(EntityHuman entityhuman, int i) {
/* 114 */     Slot slot = this.c.get(i);
/*     */     
/* 116 */     return (slot != null) ? slot.getItem() : ItemStack.a;
/*     */   }
/*     */   
/*     */   public ItemStack a(int i, int j, InventoryClickType inventoryclicktype, EntityHuman entityhuman) {
/* 120 */     ItemStack itemstack = ItemStack.a;
/* 121 */     PlayerInventory playerinventory = entityhuman.inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (inventoryclicktype == InventoryClickType.QUICK_CRAFT) {
/* 128 */       int i1 = this.g;
/*     */       
/* 130 */       this.g = c(j);
/* 131 */       if ((i1 != 1 || this.g != 2) && i1 != this.g) {
/* 132 */         d();
/* 133 */       } else if (playerinventory.getCarried().isEmpty()) {
/* 134 */         d();
/* 135 */       } else if (this.g == 0) {
/* 136 */         this.dragType = b(j);
/* 137 */         if (a(this.dragType, entityhuman)) {
/* 138 */           this.g = 1;
/* 139 */           this.h.clear();
/*     */         } else {
/* 141 */           d();
/*     */         } 
/* 143 */       } else if (this.g == 1) {
/* 144 */         Slot slot = this.c.get(i);
/*     */         
/* 146 */         ItemStack itemstack1 = playerinventory.getCarried();
/* 147 */         if (slot != null && a(slot, itemstack1, true) && slot.isAllowed(itemstack1) && (this.dragType == 2 || itemstack1.getCount() > this.h.size()) && b(slot)) {
/* 148 */           this.h.add(slot);
/*     */         }
/* 150 */       } else if (this.g == 2) {
/* 151 */         if (!this.h.isEmpty()) {
/* 152 */           ItemStack itemstack2 = playerinventory.getCarried().cloneItemStack();
/* 153 */           int l = playerinventory.getCarried().getCount();
/* 154 */           Iterator<Slot> iterator = this.h.iterator();
/*     */           
/* 156 */           Map<Integer, ItemStack> draggedSlots = new HashMap<>();
/* 157 */           while (iterator.hasNext()) {
/* 158 */             Slot slot1 = iterator.next();
/* 159 */             ItemStack itemstack3 = playerinventory.getCarried();
/*     */             
/* 161 */             if (slot1 != null && a(slot1, itemstack3, true) && slot1.isAllowed(itemstack3) && (this.dragType == 2 || itemstack3.getCount() >= this.h.size()) && b(slot1)) {
/* 162 */               ItemStack itemstack4 = itemstack2.cloneItemStack();
/* 163 */               int j1 = slot1.hasItem() ? slot1.getItem().getCount() : 0;
/*     */               
/* 165 */               a(this.h, this.dragType, itemstack4, j1);
/* 166 */               int k = Math.min(itemstack4.getMaxStackSize(), slot1.getMaxStackSize(itemstack4));
/* 167 */               if (itemstack4.getCount() > k) {
/* 168 */                 itemstack4.setCount(k);
/*     */               }
/*     */               
/* 171 */               l -= itemstack4.getCount() - j1;
/*     */               
/* 173 */               draggedSlots.put(Integer.valueOf(slot1.rawSlotIndex), itemstack4);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 178 */           InventoryView view = getBukkitView();
/* 179 */           CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(itemstack2);
/* 180 */           craftItemStack.setAmount(l);
/* 181 */           Map<Integer, ItemStack> eventmap = new HashMap<>();
/* 182 */           for (Map.Entry<Integer, ItemStack> ditem : draggedSlots.entrySet()) {
/* 183 */             eventmap.put(ditem.getKey(), CraftItemStack.asBukkitCopy(ditem.getValue()));
/*     */           }
/*     */ 
/*     */           
/* 187 */           ItemStack oldCursor = playerinventory.getCarried();
/* 188 */           playerinventory.setCarried(CraftItemStack.asNMSCopy((ItemStack)craftItemStack));
/*     */           
/* 190 */           InventoryDragEvent event = new InventoryDragEvent(view, (craftItemStack.getType() != Material.AIR) ? (ItemStack)craftItemStack : null, CraftItemStack.asBukkitCopy(oldCursor), (this.dragType == 1), eventmap);
/* 191 */           entityhuman.world.getServer().getPluginManager().callEvent((Event)event);
/*     */ 
/*     */           
/* 194 */           boolean needsUpdate = (event.getResult() != Event.Result.DEFAULT);
/*     */           
/* 196 */           if (event.getResult() != Event.Result.DENY) {
/* 197 */             for (Map.Entry<Integer, ItemStack> dslot : draggedSlots.entrySet()) {
/* 198 */               view.setItem(((Integer)dslot.getKey()).intValue(), CraftItemStack.asBukkitCopy(dslot.getValue()));
/*     */             }
/*     */ 
/*     */             
/* 202 */             if (playerinventory.getCarried() != null) {
/* 203 */               playerinventory.setCarried(CraftItemStack.asNMSCopy(event.getCursor()));
/* 204 */               needsUpdate = true;
/*     */             } 
/*     */           } else {
/* 207 */             playerinventory.setCarried(oldCursor);
/*     */           } 
/*     */           
/* 210 */           if (needsUpdate && entityhuman instanceof EntityPlayer) {
/* 211 */             ((EntityPlayer)entityhuman).updateInventory(this);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 216 */         d();
/*     */       } else {
/* 218 */         d();
/*     */       } 
/* 220 */     } else if (this.g != 0) {
/* 221 */       d();
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 226 */     else if ((inventoryclicktype == InventoryClickType.PICKUP || inventoryclicktype == InventoryClickType.QUICK_MOVE) && (j == 0 || j == 1)) {
/* 227 */       if (i == -999) {
/* 228 */         if (!playerinventory.getCarried().isEmpty()) {
/* 229 */           if (j == 0) {
/*     */             
/* 231 */             ItemStack carried = playerinventory.getCarried();
/* 232 */             playerinventory.setCarried(ItemStack.a);
/* 233 */             entityhuman.drop(carried, true);
/*     */           } 
/*     */ 
/*     */           
/* 237 */           if (j == 1) {
/* 238 */             entityhuman.drop(playerinventory.getCarried().cloneAndSubtract(1), true);
/*     */           }
/*     */         } 
/* 241 */       } else if (inventoryclicktype == InventoryClickType.QUICK_MOVE) {
/* 242 */         if (i < 0) {
/* 243 */           return ItemStack.a;
/*     */         }
/*     */         
/* 246 */         Slot slot2 = this.c.get(i);
/* 247 */         if (slot2 == null || !slot2.isAllowed(entityhuman)) {
/* 248 */           return ItemStack.a;
/*     */         }
/*     */         
/* 251 */         for (ItemStack itemstack2 = b(entityhuman, i); !itemstack2.isEmpty() && ItemStack.c(slot2.getItem(), itemstack2); itemstack2 = b(entityhuman, i)) {
/* 252 */           itemstack = itemstack2.cloneItemStack();
/*     */         }
/*     */       } else {
/* 255 */         if (i < 0) {
/* 256 */           return ItemStack.a;
/*     */         }
/*     */         
/* 259 */         Slot slot2 = this.c.get(i);
/* 260 */         if (slot2 != null) {
/* 261 */           ItemStack itemstack2 = slot2.getItem();
/* 262 */           ItemStack itemstack1 = playerinventory.getCarried();
/* 263 */           if (!itemstack2.isEmpty()) {
/* 264 */             itemstack = itemstack2.cloneItemStack();
/*     */           }
/*     */           
/* 267 */           if (itemstack2.isEmpty()) {
/* 268 */             if (!itemstack1.isEmpty() && slot2.isAllowed(itemstack1)) {
/* 269 */               int k1 = (j == 0) ? itemstack1.getCount() : 1;
/* 270 */               if (k1 > slot2.getMaxStackSize(itemstack1)) {
/* 271 */                 k1 = slot2.getMaxStackSize(itemstack1);
/*     */               }
/*     */               
/* 274 */               slot2.set(itemstack1.cloneAndSubtract(k1));
/*     */             } 
/* 276 */           } else if (slot2.isAllowed(entityhuman)) {
/* 277 */             if (itemstack1.isEmpty()) {
/* 278 */               if (itemstack2.isEmpty()) {
/* 279 */                 slot2.set(ItemStack.a);
/* 280 */                 playerinventory.setCarried(ItemStack.a);
/*     */               } else {
/* 282 */                 int k1 = (j == 0) ? itemstack2.getCount() : ((itemstack2.getCount() + 1) / 2);
/* 283 */                 playerinventory.setCarried(slot2.a(k1));
/* 284 */                 if (itemstack2.isEmpty()) {
/* 285 */                   slot2.set(ItemStack.a);
/*     */                 }
/*     */                 
/* 288 */                 slot2.a(entityhuman, playerinventory.getCarried());
/*     */               } 
/* 290 */             } else if (slot2.isAllowed(itemstack1)) {
/* 291 */               if (itemstack2.getItem() == itemstack1.getItem() && itemstack2.getData() == itemstack1.getData() && ItemStack.equals(itemstack2, itemstack1)) {
/* 292 */                 int k1 = (j == 0) ? itemstack1.getCount() : 1;
/* 293 */                 if (k1 > slot2.getMaxStackSize(itemstack1) - itemstack2.getCount()) {
/* 294 */                   k1 = slot2.getMaxStackSize(itemstack1) - itemstack2.getCount();
/*     */                 }
/*     */                 
/* 297 */                 if (k1 > itemstack1.getMaxStackSize() - itemstack2.getCount()) {
/* 298 */                   k1 = itemstack1.getMaxStackSize() - itemstack2.getCount();
/*     */                 }
/*     */                 
/* 301 */                 itemstack1.subtract(k1);
/* 302 */                 itemstack2.add(k1);
/* 303 */               } else if (itemstack1.getCount() <= slot2.getMaxStackSize(itemstack1)) {
/* 304 */                 slot2.set(itemstack1);
/* 305 */                 playerinventory.setCarried(itemstack2);
/*     */               } 
/* 307 */             } else if (itemstack2.getItem() == itemstack1.getItem() && itemstack1.getMaxStackSize() > 1 && (!itemstack2.usesData() || itemstack2.getData() == itemstack1.getData()) && ItemStack.equals(itemstack2, itemstack1) && !itemstack2.isEmpty()) {
/* 308 */               int k1 = itemstack2.getCount();
/* 309 */               if (k1 + itemstack1.getCount() <= itemstack1.getMaxStackSize()) {
/* 310 */                 itemstack1.add(k1);
/* 311 */                 itemstack2 = slot2.a(k1);
/* 312 */                 if (itemstack2.isEmpty()) {
/* 313 */                   slot2.set(ItemStack.a);
/*     */                 }
/*     */                 
/* 316 */                 slot2.a(entityhuman, playerinventory.getCarried());
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 321 */           slot2.f();
/*     */           
/* 323 */           if (entityhuman instanceof EntityPlayer && slot2.getMaxStackSize() != 64) {
/* 324 */             ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutSetSlot(this.windowId, slot2.rawSlotIndex, slot2.getItem()));
/*     */             
/* 326 */             if (getBukkitView().getType() == InventoryType.WORKBENCH || getBukkitView().getType() == InventoryType.CRAFTING) {
/* 327 */               ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutSetSlot(this.windowId, 0, getSlot(0).getItem()));
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 333 */     } else if (inventoryclicktype == InventoryClickType.SWAP && j >= 0 && j < 9) {
/* 334 */       Slot slot2 = this.c.get(i);
/* 335 */       ItemStack itemstack2 = playerinventory.getItem(j);
/* 336 */       ItemStack itemstack1 = slot2.getItem();
/* 337 */       if (!itemstack2.isEmpty() || !itemstack1.isEmpty()) {
/* 338 */         if (itemstack2.isEmpty()) {
/* 339 */           if (slot2.isAllowed(entityhuman)) {
/* 340 */             playerinventory.setItem(j, itemstack1);
/* 341 */             slot2.b(itemstack1.getCount());
/* 342 */             slot2.set(ItemStack.a);
/* 343 */             slot2.a(entityhuman, itemstack1);
/*     */           } 
/* 345 */         } else if (itemstack1.isEmpty()) {
/* 346 */           if (slot2.isAllowed(itemstack2)) {
/* 347 */             int k1 = slot2.getMaxStackSize(itemstack2);
/* 348 */             if (itemstack2.getCount() > k1) {
/* 349 */               slot2.set(itemstack2.cloneAndSubtract(k1));
/*     */             } else {
/* 351 */               slot2.set(itemstack2);
/* 352 */               playerinventory.setItem(j, ItemStack.a);
/*     */             } 
/*     */           } 
/* 355 */         } else if (slot2.isAllowed(entityhuman) && slot2.isAllowed(itemstack2)) {
/* 356 */           int k1 = slot2.getMaxStackSize(itemstack2);
/* 357 */           if (itemstack2.getCount() > k1) {
/* 358 */             slot2.set(itemstack2.cloneAndSubtract(k1));
/* 359 */             slot2.a(entityhuman, itemstack1);
/* 360 */             if (!playerinventory.pickup(itemstack1)) {
/* 361 */               entityhuman.drop(itemstack1, true);
/*     */             }
/*     */           } else {
/* 364 */             slot2.set(itemstack2);
/* 365 */             playerinventory.setItem(j, itemstack1);
/* 366 */             slot2.a(entityhuman, itemstack1);
/*     */           } 
/*     */         } 
/*     */       }
/* 370 */     } else if (inventoryclicktype == InventoryClickType.CLONE && entityhuman.abilities.canInstantlyBuild && playerinventory.getCarried().isEmpty() && i >= 0) {
/* 371 */       Slot slot2 = this.c.get(i);
/* 372 */       if (slot2 != null && slot2.hasItem()) {
/* 373 */         ItemStack itemstack2 = slot2.getItem().cloneItemStack();
/* 374 */         itemstack2.setCount(itemstack2.getMaxStackSize());
/* 375 */         playerinventory.setCarried(itemstack2);
/*     */       } 
/* 377 */     } else if (inventoryclicktype == InventoryClickType.THROW && playerinventory.getCarried().isEmpty() && i >= 0) {
/* 378 */       Slot slot2 = this.c.get(i);
/* 379 */       if (slot2 != null && slot2.hasItem() && slot2.isAllowed(entityhuman)) {
/* 380 */         ItemStack itemstack2 = slot2.a((j == 0) ? 1 : slot2.getItem().getCount());
/* 381 */         slot2.a(entityhuman, itemstack2);
/* 382 */         entityhuman.drop(itemstack2, true);
/*     */       } 
/* 384 */     } else if (inventoryclicktype == InventoryClickType.PICKUP_ALL && i >= 0) {
/* 385 */       Slot slot2 = this.c.get(i);
/* 386 */       ItemStack itemstack2 = playerinventory.getCarried();
/* 387 */       if (!itemstack2.isEmpty() && (slot2 == null || !slot2.hasItem() || !slot2.isAllowed(entityhuman))) {
/* 388 */         int l = (j == 0) ? 0 : (this.c.size() - 1);
/* 389 */         int k1 = (j == 0) ? 1 : -1;
/*     */         
/* 391 */         for (int l1 = 0; l1 < 2; l1++) {
/* 392 */           for (int i2 = l; i2 >= 0 && i2 < this.c.size() && itemstack2.getCount() < itemstack2.getMaxStackSize(); i2 += k1) {
/* 393 */             Slot slot3 = this.c.get(i2);
/*     */             
/* 395 */             if (slot3.hasItem() && a(slot3, itemstack2, true) && slot3.isAllowed(entityhuman) && a(itemstack2, slot3)) {
/* 396 */               ItemStack itemstack5 = slot3.getItem();
/*     */               
/* 398 */               if (l1 != 0 || itemstack5.getCount() != itemstack5.getMaxStackSize()) {
/* 399 */                 int k = Math.min(itemstack2.getMaxStackSize() - itemstack2.getCount(), itemstack5.getCount());
/* 400 */                 ItemStack itemstack6 = slot3.a(k);
/*     */                 
/* 402 */                 itemstack2.add(k);
/* 403 */                 if (itemstack6.isEmpty()) {
/* 404 */                   slot3.set(ItemStack.a);
/*     */                 }
/*     */                 
/* 407 */                 slot3.a(entityhuman, itemstack6);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 414 */       b();
/*     */     } 
/*     */ 
/*     */     
/* 418 */     return itemstack;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 422 */     return true;
/*     */   }
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 426 */     PlayerInventory playerinventory = entityhuman.inventory;
/*     */     
/* 428 */     if (!playerinventory.getCarried().isEmpty()) {
/* 429 */       entityhuman.drop(playerinventory.getCarried(), false);
/* 430 */       playerinventory.setCarried(ItemStack.a);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman entityhuman, World world, IInventory iinventory) {
/* 438 */     if (entityhuman.isAlive() && (!(entityhuman instanceof EntityPlayer) || !((EntityPlayer)entityhuman).t())) {
/* 439 */       for (int i = 0; i < iinventory.getSize(); i++) {
/* 440 */         entityhuman.inventory.a(world, iinventory.splitWithoutUpdate(i));
/*     */       }
/*     */     } else {
/*     */       
/* 444 */       for (int i = 0; i < iinventory.getSize(); i++) {
/* 445 */         entityhuman.drop(iinventory.splitWithoutUpdate(i), false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 452 */     b();
/*     */   }
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 456 */     getSlot(i).set(itemstack);
/*     */   }
/*     */   
/*     */   public void b(int i, ItemStack itemstack) {
/* 460 */     ItemStack itemstack1 = getSlot(i).getItem();
/*     */     
/* 462 */     if (itemstack1.isEmpty()) {
/* 463 */       setItem(i, itemstack);
/* 464 */     } else if (itemstack1.a().equals(itemstack.a()) && itemstack1.getCount() < itemstack1.getMaxStackSize()) {
/* 465 */       itemstack1.add(itemstack.getCount());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(EntityHuman entityhuman) {
/* 471 */     return !this.i.contains(entityhuman);
/*     */   }
/*     */   
/*     */   public void a(EntityHuman entityhuman, boolean flag) {
/* 475 */     if (flag) {
/* 476 */       this.i.remove(entityhuman);
/*     */     } else {
/* 478 */       this.i.add(entityhuman);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean a(EntityHuman paramEntityHuman);
/*     */   
/*     */   protected boolean a(ItemStack itemstack, int i, int j, boolean flag) {
/* 486 */     boolean flag1 = false;
/* 487 */     int k = i;
/*     */     
/* 489 */     if (flag) {
/* 490 */       k = j - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 496 */     if (itemstack.isStackable()) {
/* 497 */       while (!itemstack.isEmpty() && (
/* 498 */         flag ? (
/* 499 */         k < i) : (
/*     */ 
/*     */         
/* 502 */         k >= j))) {
/*     */ 
/*     */ 
/*     */         
/* 506 */         Slot slot = this.c.get(k);
/* 507 */         ItemStack itemstack1 = slot.getItem();
/* 508 */         if (!itemstack1.isEmpty() && itemstack1.getItem() == itemstack.getItem() && (!itemstack.usesData() || itemstack.getData() == itemstack1.getData()) && ItemStack.equals(itemstack, itemstack1)) {
/* 509 */           int l = itemstack1.getCount() + itemstack.getCount();
/*     */           
/* 511 */           if (l <= itemstack.getMaxStackSize()) {
/* 512 */             itemstack.setCount(0);
/* 513 */             itemstack1.setCount(l);
/* 514 */             slot.f();
/* 515 */             flag1 = true;
/* 516 */           } else if (itemstack1.getCount() < itemstack.getMaxStackSize()) {
/* 517 */             itemstack.subtract(itemstack.getMaxStackSize() - itemstack1.getCount());
/* 518 */             itemstack1.setCount(itemstack.getMaxStackSize());
/* 519 */             slot.f();
/* 520 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 524 */         if (flag) {
/* 525 */           k--; continue;
/*     */         } 
/* 527 */         k++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 532 */     if (!itemstack.isEmpty()) {
/* 533 */       if (flag) {
/* 534 */         k = j - 1;
/*     */       } else {
/* 536 */         k = i;
/*     */       } 
/*     */ 
/*     */       
/* 540 */       while (flag ? (
/* 541 */         k < i) : (
/*     */ 
/*     */         
/* 544 */         k >= j)) {
/*     */ 
/*     */ 
/*     */         
/* 548 */         Slot slot = this.c.get(k);
/* 549 */         ItemStack itemstack1 = slot.getItem();
/* 550 */         if (itemstack1.isEmpty() && slot.isAllowed(itemstack)) {
/* 551 */           if (itemstack.getCount() > slot.getMaxStackSize()) {
/* 552 */             slot.set(itemstack.cloneAndSubtract(slot.getMaxStackSize()));
/*     */           } else {
/* 554 */             slot.set(itemstack.cloneAndSubtract(itemstack.getCount()));
/*     */           } 
/*     */           
/* 557 */           slot.f();
/* 558 */           flag1 = true;
/*     */           
/*     */           break;
/*     */         } 
/* 562 */         if (flag) {
/* 563 */           k--; continue;
/*     */         } 
/* 565 */         k++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 570 */     return flag1;
/*     */   }
/*     */   
/*     */   public static int b(int i) {
/* 574 */     return i >> 2 & 0x3;
/*     */   }
/*     */   
/*     */   public static int c(int i) {
/* 578 */     return i & 0x3;
/*     */   }
/*     */   
/*     */   public static boolean a(int i, EntityHuman entityhuman) {
/* 582 */     return (i == 0) ? true : ((i == 1) ? true : ((i == 2 && entityhuman.abilities.canInstantlyBuild)));
/*     */   }
/*     */   
/*     */   protected void d() {
/* 586 */     this.g = 0;
/* 587 */     this.h.clear();
/*     */   }
/*     */   
/*     */   public static boolean a(@Nullable Slot slot, ItemStack itemstack, boolean flag) {
/* 591 */     boolean flag1 = !(slot != null && slot.hasItem());
/*     */     
/* 593 */     return (!flag1 && itemstack.doMaterialsMatch(slot.getItem()) && ItemStack.equals(slot.getItem(), itemstack)) ? ((slot.getItem().getCount() + (flag ? 0 : itemstack.getCount()) <= itemstack.getMaxStackSize())) : flag1;
/*     */   }
/*     */   
/*     */   public static void a(Set<Slot> set, int i, ItemStack itemstack, int j) {
/* 597 */     switch (i) {
/*     */       case 0:
/* 599 */         itemstack.setCount(MathHelper.d(itemstack.getCount() / set.size()));
/*     */         break;
/*     */       
/*     */       case 1:
/* 603 */         itemstack.setCount(1);
/*     */         break;
/*     */       
/*     */       case 2:
/* 607 */         itemstack.setCount(itemstack.getItem().getMaxStackSize());
/*     */         break;
/*     */     } 
/* 610 */     itemstack.add(j);
/*     */   }
/*     */   
/*     */   public boolean b(Slot slot) {
/* 614 */     return true;
/*     */   }
/*     */   
/*     */   public static int a(@Nullable TileEntity tileentity) {
/* 618 */     return (tileentity instanceof IInventory) ? b((IInventory)tileentity) : 0;
/*     */   }
/*     */   
/*     */   public static int b(@Nullable IInventory iinventory) {
/* 622 */     if (iinventory == null) {
/* 623 */       return 0;
/*     */     }
/* 625 */     int i = 0;
/* 626 */     float f = 0.0F;
/*     */     
/* 628 */     for (int j = 0; j < iinventory.getSize(); j++) {
/* 629 */       ItemStack itemstack = iinventory.getItem(j);
/*     */       
/* 631 */       if (!itemstack.isEmpty()) {
/* 632 */         f += itemstack.getCount() / Math.min(iinventory.getMaxStackSize(), itemstack.getMaxStackSize());
/* 633 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 637 */     f /= iinventory.getSize();
/* 638 */     return MathHelper.d(f * 14.0F) + ((i > 0) ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World world, EntityHuman entityhuman, InventoryCrafting inventorycrafting, InventoryCraftResult inventorycraftresult) {
/* 643 */     if (!world.isClientSide) {
/* 644 */       EntityPlayer entityplayer = (EntityPlayer)entityhuman;
/* 645 */       ItemStack itemstack = ItemStack.a;
/* 646 */       IRecipe irecipe = CraftingManager.b(inventorycrafting, world);
/*     */       
/* 648 */       if (irecipe != null && (irecipe.c() || !world.getGameRules().getBoolean("doLimitedCrafting") || entityplayer.F().b(irecipe))) {
/* 649 */         inventorycraftresult.a(irecipe);
/* 650 */         itemstack = irecipe.craftItem(inventorycrafting);
/*     */       } 
/* 652 */       itemstack = CraftEventFactory.callPreCraftEvent(inventorycrafting, itemstack, getBukkitView(), false);
/*     */       
/* 654 */       inventorycraftresult.setItem(0, itemstack);
/* 655 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutSetSlot(this.windowId, 0, itemstack));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Container.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */