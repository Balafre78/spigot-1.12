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
/*     */ public class CommandTestForBlock
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  27 */     return "testforblock";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  32 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  37 */     return "commands.testforblock.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  42 */     if (paramArrayOfString.length < 4) {
/*  43 */       throw new ExceptionUsage("commands.testforblock.usage", new Object[0]);
/*     */     }
/*  45 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
/*     */     
/*  47 */     BlockPosition blockPosition = a(paramICommandListener, paramArrayOfString, 0, false);
/*  48 */     Block block1 = b(paramICommandListener, paramArrayOfString[3]);
/*  49 */     if (block1 == null) {
/*  50 */       throw new ExceptionInvalidNumber("commands.setblock.notFound", new Object[] { paramArrayOfString[3] });
/*     */     }
/*     */     
/*  53 */     World world = paramICommandListener.getWorld();
/*  54 */     if (!world.isLoaded(blockPosition)) {
/*  55 */       throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
/*     */     }
/*     */     
/*  58 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*  59 */     boolean bool = false;
/*  60 */     if (paramArrayOfString.length >= 6 && block1.isTileEntity()) {
/*  61 */       String str = a(paramArrayOfString, 5);
/*     */       try {
/*  63 */         nBTTagCompound = MojangsonParser.parse(str);
/*  64 */         bool = true;
/*  65 */       } catch (MojangsonParseException mojangsonParseException) {
/*  66 */         throw new CommandException("commands.setblock.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     IBlockData iBlockData = world.getType(blockPosition);
/*  71 */     Block block2 = iBlockData.getBlock();
/*  72 */     if (block2 != block1) {
/*  73 */       throw new CommandException("commands.testforblock.failed.tile", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()), block2.getName(), block1.getName() });
/*     */     }
/*     */     
/*  76 */     if (paramArrayOfString.length >= 5 && 
/*  77 */       !CommandAbstract.b(block1, paramArrayOfString[4]).apply(iBlockData)) {
/*     */       try {
/*  79 */         int i = iBlockData.getBlock().toLegacyData(iBlockData);
/*  80 */         throw new CommandException("commands.testforblock.failed.data", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()), Integer.valueOf(i), Integer.valueOf(Integer.parseInt(paramArrayOfString[4])) });
/*  81 */       } catch (NumberFormatException numberFormatException) {
/*  82 */         throw new CommandException("commands.testforblock.failed.data", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()), iBlockData.toString(), paramArrayOfString[4] });
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  87 */     if (bool) {
/*  88 */       TileEntity tileEntity = world.getTileEntity(blockPosition);
/*  89 */       if (tileEntity == null) {
/*  90 */         throw new CommandException("commands.testforblock.failed.tileEntity", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*     */       }
/*  92 */       NBTTagCompound nBTTagCompound1 = tileEntity.save(new NBTTagCompound());
/*     */       
/*  94 */       if (!GameProfileSerializer.a(nBTTagCompound, nBTTagCompound1, true)) {
/*  95 */         throw new CommandException("commands.testforblock.failed.nbt", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*     */       }
/*     */     } 
/*  98 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 1);
/*  99 */     a(paramICommandListener, this, "commands.testforblock.success", new Object[] { Integer.valueOf(blockPosition.getX()), Integer.valueOf(blockPosition.getY()), Integer.valueOf(blockPosition.getZ()) });
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 104 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3)
/* 105 */       return a(paramArrayOfString, 0, paramBlockPosition); 
/* 106 */     if (paramArrayOfString.length == 4) {
/* 107 */       return a(paramArrayOfString, Block.REGISTRY.keySet());
/*     */     }
/*     */     
/* 110 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTestForBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */