/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class ItemMinecart extends Item {
/*  10 */   private static final IDispenseBehavior a = new DispenseBehaviorItem() {
/*  11 */       private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */       public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/*     */         double d3;
/*  14 */         EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/*  15 */         World world = isourceblock.getWorld();
/*  16 */         double d0 = isourceblock.getX() + enumdirection.getAdjacentX() * 1.125D;
/*  17 */         double d1 = Math.floor(isourceblock.getY()) + enumdirection.getAdjacentY();
/*  18 */         double d2 = isourceblock.getZ() + enumdirection.getAdjacentZ() * 1.125D;
/*  19 */         BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/*  20 */         IBlockData iblockdata = world.getType(blockposition);
/*  21 */         BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = (iblockdata.getBlock() instanceof BlockMinecartTrackAbstract) ? iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(((BlockMinecartTrackAbstract)iblockdata.getBlock()).g()) : BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */ 
/*     */         
/*  24 */         if (BlockMinecartTrackAbstract.i(iblockdata)) {
/*  25 */           if (blockminecarttrackabstract_enumtrackposition.c()) {
/*  26 */             d3 = 0.6D;
/*     */           } else {
/*  28 */             d3 = 0.1D;
/*     */           } 
/*     */         } else {
/*  31 */           if (iblockdata.getMaterial() != Material.AIR || !BlockMinecartTrackAbstract.i(world.getType(blockposition.down()))) {
/*  32 */             return this.b.a(isourceblock, itemstack);
/*     */           }
/*     */           
/*  35 */           IBlockData iblockdata1 = world.getType(blockposition.down());
/*  36 */           BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition1 = (iblockdata1.getBlock() instanceof BlockMinecartTrackAbstract) ? iblockdata1.<BlockMinecartTrackAbstract.EnumTrackPosition>get(((BlockMinecartTrackAbstract)iblockdata1.getBlock()).g()) : BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/*     */           
/*  38 */           if (enumdirection != EnumDirection.DOWN && blockminecarttrackabstract_enumtrackposition1.c()) {
/*  39 */             d3 = -0.4D;
/*     */           } else {
/*  41 */             d3 = -0.9D;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  47 */         ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*  48 */         Block block2 = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/*  49 */         CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */         
/*  51 */         BlockDispenseEvent event = new BlockDispenseEvent(block2, (ItemStack)craftItem.clone(), new Vector(d0, d1 + d3, d2));
/*  52 */         if (!BlockDispenser.eventFired) {
/*  53 */           world.getServer().getPluginManager().callEvent((Event)event);
/*     */         }
/*     */         
/*  56 */         if (event.isCancelled()) {
/*  57 */           itemstack.add(1);
/*  58 */           return itemstack;
/*     */         } 
/*     */         
/*  61 */         if (!event.getItem().equals(craftItem)) {
/*  62 */           itemstack.add(1);
/*     */           
/*  64 */           ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/*  65 */           IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/*  66 */           if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/*  67 */             idispensebehavior.a(isourceblock, eventStack);
/*  68 */             return itemstack;
/*     */           } 
/*     */         } 
/*     */         
/*  72 */         itemstack1 = CraftItemStack.asNMSCopy(event.getItem());
/*  73 */         EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), ((ItemMinecart)itemstack1.getItem()).b);
/*     */         
/*  75 */         if (itemstack.hasName()) {
/*  76 */           entityminecartabstract.setCustomName(itemstack.getName());
/*     */         }
/*     */         
/*  79 */         if (!world.addEntity(entityminecartabstract)) itemstack.add(1);
/*     */ 
/*     */         
/*  82 */         return itemstack;
/*     */       }
/*     */       
/*     */       protected void a(ISourceBlock isourceblock) {
/*  86 */         isourceblock.getWorld().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
/*     */       }
/*     */     };
/*     */   private final EntityMinecartAbstract.EnumMinecartType b;
/*     */   
/*     */   public ItemMinecart(EntityMinecartAbstract.EnumMinecartType entityminecartabstract_enumminecarttype) {
/*  92 */     this.maxStackSize = 1;
/*  93 */     this.b = entityminecartabstract_enumminecarttype;
/*  94 */     b(CreativeModeTab.e);
/*  95 */     BlockDispenser.REGISTRY.a(this, a);
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  99 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/* 101 */     if (!BlockMinecartTrackAbstract.i(iblockdata)) {
/* 102 */       return EnumInteractionResult.FAIL;
/*     */     }
/* 104 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 106 */     if (!world.isClientSide) {
/* 107 */       BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = (iblockdata.getBlock() instanceof BlockMinecartTrackAbstract) ? iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(((BlockMinecartTrackAbstract)iblockdata.getBlock()).g()) : BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
/* 108 */       double d0 = 0.0D;
/*     */       
/* 110 */       if (blockminecarttrackabstract_enumtrackposition.c()) {
/* 111 */         d0 = 0.5D;
/*     */       }
/*     */       
/* 114 */       EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, blockposition.getX() + 0.5D, blockposition.getY() + 0.0625D + d0, blockposition.getZ() + 0.5D, this.b);
/*     */       
/* 116 */       if (itemstack.hasName()) {
/* 117 */         entityminecartabstract.setCustomName(itemstack.getName());
/*     */       }
/*     */       
/* 120 */       if (!world.addEntity(entityminecartabstract)) return EnumInteractionResult.PASS;
/*     */     
/*     */     } 
/* 123 */     itemstack.subtract(1);
/* 124 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */