/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class CommandSetBlock
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  27 */     return "setblock";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  32 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  37 */     return "commands.setblock.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*     */     IBlockData iBlockData;
/*  42 */     if (paramArrayOfString.length < 4) {
/*  43 */       throw new ExceptionUsage("commands.setblock.usage", new Object[0]);
/*     */     }
/*  45 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
/*     */     
/*  47 */     BlockPosition blockPosition = a(paramICommandListener, paramArrayOfString, 0, false);
/*  48 */     Block block = CommandAbstract.b(paramICommandListener, paramArrayOfString[3]);
/*     */ 
/*     */     
/*  51 */     if (paramArrayOfString.length >= 5) {
/*  52 */       iBlockData = a(block, paramArrayOfString[4]);
/*     */     } else {
/*  54 */       iBlockData = block.getBlockData();
/*     */     } 
/*     */     
/*  57 */     World world = paramICommandListener.getWorld();
/*  58 */     if (!world.isLoaded(blockPosition)) {
/*  59 */       throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
/*     */     }
/*     */     
/*  62 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*  63 */     boolean bool = false;
/*  64 */     if (paramArrayOfString.length >= 7 && block.isTileEntity()) {
/*  65 */       String str = a(paramArrayOfString, 6);
/*     */       try {
/*  67 */         nBTTagCompound = MojangsonParser.parse(str);
/*  68 */         bool = true;
/*  69 */       } catch (MojangsonParseException mojangsonParseException) {
/*  70 */         throw new CommandException("commands.setblock.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     if (paramArrayOfString.length >= 6) {
/*  75 */       if ("destroy".equals(paramArrayOfString[5])) {
/*  76 */         world.setAir(blockPosition, true);
/*  77 */         if (block == Blocks.AIR) {
/*  78 */           a(paramICommandListener, this, "commands.setblock.success", new Object[0]);
/*     */           return;
/*     */         } 
/*  81 */       } else if ("keep".equals(paramArrayOfString[5]) && 
/*  82 */         !world.isEmpty(blockPosition)) {
/*  83 */         throw new CommandException("commands.setblock.noChange", new Object[0]);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  88 */     TileEntity tileEntity = world.getTileEntity(blockPosition);
/*  89 */     if (tileEntity != null && 
/*  90 */       tileEntity instanceof IInventory) {
/*  91 */       ((IInventory)tileEntity).clear();
/*     */     }
/*     */ 
/*     */     
/*  95 */     if (!world.setTypeAndData(blockPosition, iBlockData, 2)) {
/*  96 */       throw new CommandException("commands.setblock.noChange", new Object[0]);
/*     */     }
/*     */     
/*  99 */     if (bool) {
/* 100 */       TileEntity tileEntity1 = world.getTileEntity(blockPosition);
/* 101 */       if (tileEntity1 != null) {
/* 102 */         nBTTagCompound.setInt("x", blockPosition.getX());
/* 103 */         nBTTagCompound.setInt("y", blockPosition.getY());
/* 104 */         nBTTagCompound.setInt("z", blockPosition.getZ());
/*     */         
/* 106 */         tileEntity1.a(nBTTagCompound);
/*     */       } 
/*     */     } 
/* 109 */     world.update(blockPosition, iBlockData.getBlock(), false);
/* 110 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 1);
/* 111 */     a(paramICommandListener, this, "commands.setblock.success", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 116 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3)
/* 117 */       return a(paramArrayOfString, 0, paramBlockPosition); 
/* 118 */     if (paramArrayOfString.length == 4)
/* 119 */       return a(paramArrayOfString, Block.REGISTRY.keySet()); 
/* 120 */     if (paramArrayOfString.length == 6) {
/* 121 */       return a(paramArrayOfString, new String[] { "replace", "destroy", "keep" });
/*     */     }
/*     */     
/* 124 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSetBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */