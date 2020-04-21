/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockCommand extends BlockTileEntity {
/*  11 */   private static final Logger c = LogManager.getLogger();
/*  12 */   public static final BlockStateDirection a = BlockDirectional.FACING;
/*  13 */   public static final BlockStateBoolean b = BlockStateBoolean.of("conditional");
/*     */   
/*     */   public BlockCommand(MaterialMapColor materialmapcolor) {
/*  16 */     super(Material.ORE, materialmapcolor);
/*  17 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(a, EnumDirection.NORTH).set(b, Boolean.valueOf(false)));
/*     */   }
/*     */   
/*     */   public TileEntity a(World world, int i) {
/*  21 */     TileEntityCommand tileentitycommand = new TileEntityCommand();
/*     */     
/*  23 */     tileentitycommand.b((this == Blocks.dd));
/*  24 */     return tileentitycommand;
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/*  28 */     if (!world.isClientSide) {
/*  29 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  31 */       if (tileentity instanceof TileEntityCommand) {
/*  32 */         TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/*  33 */         boolean flag = world.isBlockIndirectlyPowered(blockposition);
/*  34 */         boolean flag1 = tileentitycommand.f();
/*     */         
/*  36 */         Block bukkitBlock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  37 */         int old = flag1 ? 15 : 0;
/*  38 */         int current = flag ? 15 : 0;
/*     */         
/*  40 */         BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bukkitBlock, old, current);
/*  41 */         world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*  42 */         flag = (eventRedstone.getNewCurrent() > 0);
/*     */ 
/*     */         
/*  45 */         tileentitycommand.a(flag);
/*  46 */         if (!flag1 && !tileentitycommand.h() && tileentitycommand.l() != TileEntityCommand.Type.SEQUENCE && 
/*  47 */           flag) {
/*  48 */           tileentitycommand.j();
/*  49 */           world.a(blockposition, this, a(world));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  58 */     if (!world.isClientSide) {
/*  59 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  61 */       if (tileentity instanceof TileEntityCommand) {
/*  62 */         TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/*  63 */         CommandBlockListenerAbstract commandblocklistenerabstract = tileentitycommand.getCommandBlock();
/*  64 */         boolean flag = !UtilColor.b(commandblocklistenerabstract.getCommand());
/*  65 */         TileEntityCommand.Type tileentitycommand_type = tileentitycommand.l();
/*  66 */         boolean flag1 = tileentitycommand.i();
/*     */         
/*  68 */         if (tileentitycommand_type == TileEntityCommand.Type.AUTO) {
/*  69 */           tileentitycommand.j();
/*  70 */           if (flag1) {
/*  71 */             a(iblockdata, world, blockposition, commandblocklistenerabstract, flag);
/*  72 */           } else if (tileentitycommand.m()) {
/*  73 */             commandblocklistenerabstract.a(0);
/*     */           } 
/*     */           
/*  76 */           if (tileentitycommand.f() || tileentitycommand.h()) {
/*  77 */             world.a(blockposition, this, a(world));
/*     */           }
/*  79 */         } else if (tileentitycommand_type == TileEntityCommand.Type.REDSTONE) {
/*  80 */           if (flag1) {
/*  81 */             a(iblockdata, world, blockposition, commandblocklistenerabstract, flag);
/*  82 */           } else if (tileentitycommand.m()) {
/*  83 */             commandblocklistenerabstract.a(0);
/*     */           } 
/*     */         } 
/*     */         
/*  87 */         world.updateAdjacentComparators(blockposition, this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(IBlockData iblockdata, World world, BlockPosition blockposition, CommandBlockListenerAbstract commandblocklistenerabstract, boolean flag) {
/*  94 */     if (flag) {
/*  95 */       commandblocklistenerabstract.a(world);
/*     */     } else {
/*  97 */       commandblocklistenerabstract.a(0);
/*     */     } 
/*     */     
/* 100 */     c(world, blockposition, iblockdata.<EnumDirection>get(a));
/*     */   }
/*     */   
/*     */   public int a(World world) {
/* 104 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 108 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 110 */     if (tileentity instanceof TileEntityCommand && entityhuman.isCreativeAndOp()) {
/* 111 */       entityhuman.a((TileEntityCommand)tileentity);
/* 112 */       return true;
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 123 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 125 */     return (tileentity instanceof TileEntityCommand) ? ((TileEntityCommand)tileentity).getCommandBlock().k() : 0;
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 129 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 131 */     if (tileentity instanceof TileEntityCommand) {
/* 132 */       TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/* 133 */       CommandBlockListenerAbstract commandblocklistenerabstract = tileentitycommand.getCommandBlock();
/*     */       
/* 135 */       if (itemstack.hasName()) {
/* 136 */         commandblocklistenerabstract.setName(itemstack.getName());
/*     */       }
/*     */       
/* 139 */       if (!world.isClientSide) {
/* 140 */         NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */         
/* 142 */         if (nbttagcompound == null || !nbttagcompound.hasKeyOfType("BlockEntityTag", 10)) {
/* 143 */           commandblocklistenerabstract.a(world.getGameRules().getBoolean("sendCommandFeedback"));
/* 144 */           tileentitycommand.b((this == Blocks.dd));
/*     */         } 
/*     */         
/* 147 */         if (tileentitycommand.l() == TileEntityCommand.Type.SEQUENCE) {
/* 148 */           boolean flag = world.isBlockIndirectlyPowered(blockposition);
/*     */           
/* 150 */           tileentitycommand.a(flag);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random random) {
/* 158 */     return 0;
/*     */   }
/*     */   
/*     */   public EnumRenderType a(IBlockData iblockdata) {
/* 162 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 166 */     return getBlockData().<Comparable, EnumDirection>set(a, EnumDirection.fromType1(i & 0x7)).set(b, Boolean.valueOf(((i & 0x8) != 0)));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 170 */     return ((EnumDirection)iblockdata.<EnumDirection>get(a)).a() | (((Boolean)iblockdata.<Boolean>get(b)).booleanValue() ? 8 : 0);
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 174 */     return iblockdata.set(a, enumblockrotation.a(iblockdata.<EnumDirection>get(a)));
/*     */   }
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 178 */     return iblockdata.a(enumblockmirror.a(iblockdata.<EnumDirection>get(a)));
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 182 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */   
/*     */   public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
/* 186 */     return getBlockData().<Comparable, EnumDirection>set(a, EnumDirection.a(blockposition, entityliving)).set(b, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   private static void c(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 190 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(blockposition);
/* 191 */     GameRules gamerules = world.getGameRules();
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/* 196 */     for (i = gamerules.c("maxCommandChainLength"); i-- > 0; enumdirection = iblockdata.<EnumDirection>get(a)) {
/* 197 */       blockposition_mutableblockposition.c(enumdirection);
/* 198 */       IBlockData iblockdata = world.getType(blockposition_mutableblockposition);
/* 199 */       Block block = iblockdata.getBlock();
/*     */       
/* 201 */       if (block != Blocks.dd) {
/*     */         break;
/*     */       }
/*     */       
/* 205 */       TileEntity tileentity = world.getTileEntity(blockposition_mutableblockposition);
/*     */       
/* 207 */       if (!(tileentity instanceof TileEntityCommand)) {
/*     */         break;
/*     */       }
/*     */       
/* 211 */       TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/*     */       
/* 213 */       if (tileentitycommand.l() != TileEntityCommand.Type.SEQUENCE) {
/*     */         break;
/*     */       }
/*     */       
/* 217 */       if (tileentitycommand.f() || tileentitycommand.h()) {
/* 218 */         CommandBlockListenerAbstract commandblocklistenerabstract = tileentitycommand.getCommandBlock();
/*     */         
/* 220 */         if (tileentitycommand.j()) {
/* 221 */           if (!commandblocklistenerabstract.a(world)) {
/*     */             break;
/*     */           }
/*     */           
/* 225 */           world.updateAdjacentComparators(blockposition_mutableblockposition, block);
/* 226 */         } else if (tileentitycommand.m()) {
/* 227 */           commandblocklistenerabstract.a(0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     if (i <= 0) {
/* 233 */       int j = Math.max(gamerules.c("maxCommandChainLength"), 0);
/*     */       
/* 235 */       c.warn("Commandblock chain tried to execure more than " + j + " steps!");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */