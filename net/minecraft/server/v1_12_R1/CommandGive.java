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
/*    */ 
/*    */ public class CommandGive
/*    */   extends CommandAbstract
/*    */ {
/*    */   public String getCommand() {
/* 26 */     return "give";
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 31 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUsage(ICommandListener paramICommandListener) {
/* 36 */     return "commands.give.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/* 41 */     if (paramArrayOfString.length < 2) {
/* 42 */       throw new ExceptionUsage("commands.give.usage", new Object[0]);
/*    */     }
/*    */     
/* 45 */     EntityPlayer entityPlayer = b(paramMinecraftServer, paramICommandListener, paramArrayOfString[0]);
/* 46 */     Item item = a(paramICommandListener, paramArrayOfString[1]);
/* 47 */     byte b = (paramArrayOfString.length >= 3) ? a(paramArrayOfString[2], 1, item.getMaxStackSize()) : 1;
/* 48 */     boolean bool = (paramArrayOfString.length >= 4) ? a(paramArrayOfString[3]) : false;
/* 49 */     ItemStack itemStack = new ItemStack(item, b, bool);
/*    */     
/* 51 */     if (paramArrayOfString.length >= 5) {
/* 52 */       String str = a(paramArrayOfString, 4);
/*    */       try {
/* 54 */         itemStack.setTag(MojangsonParser.parse(str));
/* 55 */       } catch (MojangsonParseException mojangsonParseException) {
/* 56 */         throw new CommandException("commands.give.tagError", new Object[] { mojangsonParseException.getMessage() });
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     boolean bool1 = entityPlayer.inventory.pickup(itemStack);
/* 61 */     if (bool1) {
/* 62 */       entityPlayer.world.a((EntityHuman)null, entityPlayer.locX, entityPlayer.locY, entityPlayer.locZ, SoundEffects.dx, SoundCategory.PLAYERS, 0.2F, ((entityPlayer.getRandom().nextFloat() - entityPlayer.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 63 */       entityPlayer.defaultContainer.b();
/*    */     } 
/* 65 */     if (!bool1 || !itemStack.isEmpty()) {
/* 66 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, b - itemStack.getCount());
/* 67 */       EntityItem entityItem = entityPlayer.drop(itemStack, false);
/* 68 */       if (entityItem != null) {
/* 69 */         entityItem.r();
/* 70 */         entityItem.d(entityPlayer.getName());
/*    */       } 
/*    */     } else {
/* 73 */       itemStack.setCount(1);
/* 74 */       paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, b);
/* 75 */       EntityItem entityItem = entityPlayer.drop(itemStack, false);
/* 76 */       if (entityItem != null) {
/* 77 */         entityItem.w();
/*    */       }
/*    */     } 
/*    */     
/* 81 */     a(paramICommandListener, this, "commands.give.success", new Object[] { itemStack.C(), Integer.valueOf(b), entityPlayer.getName() });
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 86 */     if (paramArrayOfString.length == 1) {
/* 87 */       return a(paramArrayOfString, paramMinecraftServer.getPlayers());
/*    */     }
/* 89 */     if (paramArrayOfString.length == 2) {
/* 90 */       return a(paramArrayOfString, Item.REGISTRY.keySet());
/*    */     }
/*    */     
/* 93 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isListStart(String[] paramArrayOfString, int paramInt) {
/* 98 */     return (paramInt == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandGive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */