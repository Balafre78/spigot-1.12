/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ 
/*     */ public class CommandTp
/*     */   extends CommandAbstract {
/*     */   public String getCommand() {
/*  13 */     return "tp";
/*     */   }
/*     */   
/*     */   public int a() {
/*  17 */     return 2;
/*     */   }
/*     */   
/*     */   public String getUsage(ICommandListener icommandlistener) {
/*  21 */     return "commands.tp.usage";
/*     */   }
/*     */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/*     */     Object object;
/*  25 */     if (astring.length < 1) {
/*  26 */       throw new ExceptionUsage("commands.tp.usage", new Object[0]);
/*     */     }
/*  28 */     byte b0 = 0;
/*     */ 
/*     */     
/*  31 */     if (astring.length != 2 && astring.length != 4 && astring.length != 6) {
/*  32 */       object = a(icommandlistener);
/*     */     } else {
/*  34 */       object = c(minecraftserver, icommandlistener, astring[0]);
/*  35 */       b0 = 1;
/*     */     } 
/*     */     
/*  38 */     if (astring.length != 1 && astring.length != 2) {
/*  39 */       if (astring.length < b0 + 3)
/*  40 */         throw new ExceptionUsage("commands.tp.usage", new Object[0]); 
/*  41 */       if (((Entity)object).world != null) {
/*     */         
/*  43 */         int i = b0 + 1;
/*  44 */         CommandAbstract.CommandNumber commandabstract_commandnumber = a(((Entity)object).locX, astring[b0], true);
/*  45 */         CommandAbstract.CommandNumber commandabstract_commandnumber1 = a(((Entity)object).locY, astring[i++], -4096, 4096, false);
/*  46 */         CommandAbstract.CommandNumber commandabstract_commandnumber2 = a(((Entity)object).locZ, astring[i++], true);
/*  47 */         CommandAbstract.CommandNumber commandabstract_commandnumber3 = a(((Entity)object).yaw, (astring.length > i) ? astring[i++] : "~", false);
/*  48 */         CommandAbstract.CommandNumber commandabstract_commandnumber4 = a(((Entity)object).pitch, (astring.length > i) ? astring[i] : "~", false);
/*     */         
/*  50 */         a((Entity)object, commandabstract_commandnumber, commandabstract_commandnumber1, commandabstract_commandnumber2, commandabstract_commandnumber3, commandabstract_commandnumber4);
/*  51 */         a(icommandlistener, this, "commands.tp.success.coordinates", new Object[] { ((Entity)object).getName(), Double.valueOf(commandabstract_commandnumber.a()), Double.valueOf(commandabstract_commandnumber1.a()), Double.valueOf(commandabstract_commandnumber2.a()) });
/*     */       } 
/*     */     } else {
/*  54 */       Entity entity = c(minecraftserver, icommandlistener, astring[astring.length - 1]);
/*     */ 
/*     */ 
/*     */       
/*  58 */       if (((Entity)object).getBukkitEntity().teleport((Entity)entity.getBukkitEntity(), PlayerTeleportEvent.TeleportCause.COMMAND)) {
/*  59 */         a(icommandlistener, this, "commands.tp.success", new Object[] { ((Entity)object).getName(), entity.getName() });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(Entity entity, CommandAbstract.CommandNumber commandabstract_commandnumber, CommandAbstract.CommandNumber commandabstract_commandnumber1, CommandAbstract.CommandNumber commandabstract_commandnumber2, CommandAbstract.CommandNumber commandabstract_commandnumber3, CommandAbstract.CommandNumber commandabstract_commandnumber4) {
/*  69 */     if (entity instanceof EntityPlayer) {
/*  70 */       EnumSet<PacketPlayOutPosition.EnumPlayerTeleportFlags> enumset = EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class);
/*     */       
/*  72 */       if (commandabstract_commandnumber.c()) {
/*  73 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X);
/*     */       }
/*     */       
/*  76 */       if (commandabstract_commandnumber1.c()) {
/*  77 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y);
/*     */       }
/*     */       
/*  80 */       if (commandabstract_commandnumber2.c()) {
/*  81 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Z);
/*     */       }
/*     */       
/*  84 */       if (commandabstract_commandnumber4.c()) {
/*  85 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*     */       }
/*     */       
/*  88 */       if (commandabstract_commandnumber3.c()) {
/*  89 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */       }
/*     */       
/*  92 */       float f = (float)commandabstract_commandnumber3.b();
/*  93 */       if (!commandabstract_commandnumber3.c()) {
/*  94 */         f = MathHelper.g(f);
/*     */       }
/*     */       
/*  97 */       float f1 = (float)commandabstract_commandnumber4.b();
/*     */       
/*  99 */       if (!commandabstract_commandnumber4.c()) {
/* 100 */         f1 = MathHelper.g(f1);
/*     */       }
/*     */       
/* 103 */       entity.stopRiding();
/* 104 */       ((EntityPlayer)entity).playerConnection.a(commandabstract_commandnumber.b(), commandabstract_commandnumber1.b(), commandabstract_commandnumber2.b(), f, f1, enumset, PlayerTeleportEvent.TeleportCause.COMMAND);
/* 105 */       entity.setHeadRotation(f);
/*     */     } else {
/* 107 */       float f2 = (float)MathHelper.g(commandabstract_commandnumber3.a());
/*     */       
/* 109 */       float f = (float)MathHelper.g(commandabstract_commandnumber4.a());
/* 110 */       f = MathHelper.a(f, -90.0F, 90.0F);
/* 111 */       entity.setPositionRotation(commandabstract_commandnumber.a(), commandabstract_commandnumber1.a(), commandabstract_commandnumber2.a(), f2, f);
/* 112 */       entity.setHeadRotation(f2);
/*     */     } 
/*     */     
/* 115 */     if (!(entity instanceof EntityLiving) || !((EntityLiving)entity).cP()) {
/* 116 */       entity.motY = 0.0D;
/* 117 */       entity.onGround = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/* 123 */     return (astring.length != 1 && astring.length != 2) ? Collections.<String>emptyList() : a(astring, minecraftserver.getPlayers());
/*     */   }
/*     */   
/*     */   public boolean isListStart(String[] astring, int i) {
/* 127 */     return (i == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/* 133 */     return a(o);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */