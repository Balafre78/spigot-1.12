/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class ItemSkull
/*     */   extends Item {
/*   9 */   private static final String[] a = new String[] { "skeleton", "wither", "zombie", "char", "creeper", "dragon" };
/*     */   
/*     */   public ItemSkull() {
/*  12 */     b(CreativeModeTab.c);
/*  13 */     setMaxDurability(0);
/*  14 */     a(true);
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  18 */     if (enumdirection == EnumDirection.DOWN) {
/*  19 */       return EnumInteractionResult.FAIL;
/*     */     }
/*  21 */     IBlockData iblockdata = world.getType(blockposition);
/*  22 */     Block block = iblockdata.getBlock();
/*  23 */     boolean flag = block.a(world, blockposition);
/*     */     
/*  25 */     if (!flag) {
/*  26 */       if (!world.getType(blockposition).getMaterial().isBuildable()) {
/*  27 */         return EnumInteractionResult.FAIL;
/*     */       }
/*     */       
/*  30 */       blockposition = blockposition.shift(enumdirection);
/*     */     } 
/*     */     
/*  33 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  35 */     if (entityhuman.a(blockposition, enumdirection, itemstack) && Blocks.SKULL.canPlace(world, blockposition)) {
/*  36 */       if (world.isClientSide) {
/*  37 */         return EnumInteractionResult.SUCCESS;
/*     */       }
/*     */       
/*  40 */       if (!Blocks.SKULL.canPlace(world, blockposition))
/*     */       {
/*  42 */         return EnumInteractionResult.FAIL;
/*     */       }
/*     */       
/*  45 */       world.setTypeAndData(blockposition, Blocks.SKULL.getBlockData().set(BlockSkull.FACING, enumdirection), 11);
/*  46 */       int i = 0;
/*     */       
/*  48 */       if (enumdirection == EnumDirection.UP) {
/*  49 */         i = MathHelper.floor((entityhuman.yaw * 16.0F / 360.0F) + 0.5D) & 0xF;
/*     */       }
/*     */       
/*  52 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  54 */       if (tileentity instanceof TileEntitySkull) {
/*  55 */         TileEntitySkull tileentityskull = (TileEntitySkull)tileentity;
/*     */         
/*  57 */         if (itemstack.getData() == 3) {
/*  58 */           GameProfile gameprofile = null;
/*     */           
/*  60 */           if (itemstack.hasTag()) {
/*  61 */             NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */             
/*  63 */             if (nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
/*  64 */               gameprofile = GameProfileSerializer.deserialize(nbttagcompound.getCompound("SkullOwner"));
/*  65 */             } else if (nbttagcompound.hasKeyOfType("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner"))) {
/*  66 */               gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
/*     */             } 
/*     */           } 
/*     */           
/*  70 */           tileentityskull.setGameProfile(gameprofile);
/*     */         } else {
/*  72 */           tileentityskull.setSkullType(itemstack.getData());
/*     */         } 
/*     */         
/*  75 */         tileentityskull.setRotation(i);
/*  76 */         Blocks.SKULL.a(world, blockposition, tileentityskull);
/*     */       } 
/*     */       
/*  79 */       if (entityhuman instanceof EntityPlayer) {
/*  80 */         CriterionTriggers.x.a((EntityPlayer)entityhuman, blockposition, itemstack);
/*     */       }
/*     */       
/*  83 */       itemstack.subtract(1);
/*  84 */       return EnumInteractionResult.SUCCESS;
/*     */     } 
/*     */     
/*  87 */     return EnumInteractionResult.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int filterData(int i) {
/*  93 */     return i;
/*     */   }
/*     */   
/*     */   public String a(ItemStack itemstack) {
/*  97 */     int i = itemstack.getData();
/*     */     
/*  99 */     if (i < 0 || i >= a.length) {
/* 100 */       i = 0;
/*     */     }
/*     */     
/* 103 */     return String.valueOf(getName()) + "." + a[i];
/*     */   }
/*     */   
/*     */   public String b(ItemStack itemstack) {
/* 107 */     if (itemstack.getData() == 3 && itemstack.hasTag()) {
/* 108 */       if (itemstack.getTag().hasKeyOfType("SkullOwner", 8)) {
/* 109 */         return LocaleI18n.a("item.skull.player.name", new Object[] { itemstack.getTag().getString("SkullOwner") });
/*     */       }
/*     */       
/* 112 */       if (itemstack.getTag().hasKeyOfType("SkullOwner", 10)) {
/* 113 */         NBTTagCompound nbttagcompound = itemstack.getTag().getCompound("SkullOwner");
/*     */         
/* 115 */         if (nbttagcompound.hasKeyOfType("Name", 8)) {
/* 116 */           return LocaleI18n.a("item.skull.player.name", new Object[] { nbttagcompound.getString("Name") });
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     return super.b(itemstack);
/*     */   }
/*     */   
/*     */   public boolean a(final NBTTagCompound nbttagcompound) {
/* 125 */     super.a(nbttagcompound);
/* 126 */     if (nbttagcompound.hasKeyOfType("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner"))) {
/* 127 */       GameProfile gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
/*     */ 
/*     */       
/* 130 */       TileEntitySkull.b(gameprofile, new Predicate<GameProfile>()
/*     */           {
/*     */             public boolean apply(GameProfile gameprofile)
/*     */             {
/* 134 */               nbttagcompound.set("SkullOwner", GameProfileSerializer.serialize(new NBTTagCompound(), gameprofile));
/* 135 */               return false;
/*     */             }
/*     */           });
/*     */       
/* 139 */       return true;
/*     */     } 
/*     */     
/* 142 */     NBTTagList textures = nbttagcompound.getCompound("SkullOwner").getCompound("Properties").getList("textures", 10);
/* 143 */     for (int i = 0; i < textures.size(); i++) {
/* 144 */       if (textures.get(i) instanceof NBTTagCompound && !textures.get(i).hasKeyOfType("Signature", 8) && textures.get(i).getString("Value").trim().isEmpty()) {
/* 145 */         nbttagcompound.remove("SkullOwner");
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */