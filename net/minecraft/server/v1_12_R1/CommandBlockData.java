/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandBlockData
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 25 */     return "blockdata";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 30 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 35 */     return "commands.blockdata.usage";
/*    */   }
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*    */     NBTTagCompound nBTTagCompound3;
/* 40 */     if (paramArrayOfString.length < 4) {
/* 41 */       throw new ExceptionUsage("commands.blockdata.usage", new Object[0]);
/*    */     }
/* 43 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
/*    */     
/* 45 */     BlockPosition blockPosition = a(paramICommandListener, paramArrayOfString, 0, false);
/*    */     
/* 47 */     World world = paramICommandListener.getWorld();
/* 48 */     if (!world.isLoaded(blockPosition)) {
/* 49 */       throw new CommandException("commands.blockdata.outOfWorld", new Object[0]);
/*    */     }
/*    */     
/* 52 */     IBlockData iBlockData = world.getType(blockPosition);
/* 53 */     TileEntity tileEntity = world.getTileEntity(blockPosition);
/* 54 */     if (tileEntity == null) {
/* 55 */       throw new CommandException("commands.blockdata.notValid", new Object[0]);
/*    */     }
/*    */     
/* 58 */     NBTTagCompound nBTTagCompound1 = tileEntity.save(new NBTTagCompound());
/* 59 */     NBTTagCompound nBTTagCompound2 = nBTTagCompound1.g();
/*    */ 
/*    */     
/*    */     try {
/* 63 */       nBTTagCompound3 = MojangsonParser.parse(a(paramArrayOfString, 3));
/* 64 */     } catch (MojangsonParseException mojangsonParseException) {
/* 65 */       throw new CommandException("commands.blockdata.tagError", new Object[] { mojangsonParseException.getMessage() });
/*    */     } 
/*    */     
/* 68 */     nBTTagCompound1.a(nBTTagCompound3);
/*    */     
/* 70 */     nBTTagCompound1.setInt("x", blockPosition.getX());
/* 71 */     nBTTagCompound1.setInt("y", blockPosition.getY());
/* 72 */     nBTTagCompound1.setInt("z", blockPosition.getZ());
/*    */     
/* 74 */     if (nBTTagCompound1.equals(nBTTagCompound2)) {
/* 75 */       throw new CommandException("commands.blockdata.failed", new Object[] { nBTTagCompound1.toString() });
/*    */     }
/*    */     
/* 78 */     tileEntity.a(nBTTagCompound1);
/* 79 */     tileEntity.update();
/* 80 */     world.notify(blockPosition, iBlockData, iBlockData, 3);
/*    */     
/* 82 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 1);
/* 83 */     a(paramICommandListener, this, "commands.blockdata.success", new Object[] { nBTTagCompound1.toString() });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 88 */     if (paramArrayOfString.length > 0 && paramArrayOfString.length <= 3) {
/* 89 */       return a(paramArrayOfString, 0, paramBlockPosition);
/*    */     }
/* 91 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */