/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ 
/*     */ public class CommandTeleport
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  13 */     return "teleport";
/*     */   }
/*     */   
/*     */   public int a() {
/*  17 */     return 2;
/*     */   }
/*     */   
/*     */   public String getUsage(ICommandListener icommandlistener) {
/*  21 */     return "commands.teleport.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/*  25 */     if (astring.length < 4) {
/*  26 */       throw new ExceptionUsage("commands.teleport.usage", new Object[0]);
/*     */     }
/*  28 */     Entity entity = c(minecraftserver, icommandlistener, astring[0]);
/*     */     
/*  30 */     if (entity.world != null) {
/*     */       
/*  32 */       Vec3D vec3d = icommandlistener.d();
/*  33 */       byte b0 = 1;
/*  34 */       int i = b0 + 1;
/*  35 */       CommandAbstract.CommandNumber commandabstract_commandnumber = a(vec3d.x, astring[b0], true);
/*  36 */       CommandAbstract.CommandNumber commandabstract_commandnumber1 = a(vec3d.y, astring[i++], -4096, 4096, false);
/*  37 */       CommandAbstract.CommandNumber commandabstract_commandnumber2 = a(vec3d.z, astring[i++], true);
/*  38 */       Entity entity1 = (icommandlistener.f() == null) ? entity : icommandlistener.f();
/*  39 */       CommandAbstract.CommandNumber commandabstract_commandnumber3 = a((astring.length > i) ? entity1.yaw : entity.yaw, (astring.length > i) ? astring[i] : "~", false);
/*     */       
/*  41 */       i++;
/*  42 */       CommandAbstract.CommandNumber commandabstract_commandnumber4 = a((astring.length > i) ? entity1.pitch : entity.pitch, (astring.length > i) ? astring[i] : "~", false);
/*     */       
/*  44 */       a(entity, commandabstract_commandnumber, commandabstract_commandnumber1, commandabstract_commandnumber2, commandabstract_commandnumber3, commandabstract_commandnumber4);
/*  45 */       a(icommandlistener, this, "commands.teleport.success.coordinates", new Object[] { entity.getName(), Double.valueOf(commandabstract_commandnumber.a()), Double.valueOf(commandabstract_commandnumber1.a()), Double.valueOf(commandabstract_commandnumber2.a()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(Entity entity, CommandAbstract.CommandNumber commandabstract_commandnumber, CommandAbstract.CommandNumber commandabstract_commandnumber1, CommandAbstract.CommandNumber commandabstract_commandnumber2, CommandAbstract.CommandNumber commandabstract_commandnumber3, CommandAbstract.CommandNumber commandabstract_commandnumber4) {
/*  53 */     if (entity instanceof EntityPlayer) {
/*  54 */       EnumSet<PacketPlayOutPosition.EnumPlayerTeleportFlags> enumset = EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class);
/*     */       
/*  56 */       float f = (float)commandabstract_commandnumber3.b();
/*  57 */       if (commandabstract_commandnumber3.c()) {
/*  58 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */       } else {
/*  60 */         f = MathHelper.g(f);
/*     */       } 
/*     */       
/*  63 */       float f1 = (float)commandabstract_commandnumber4.b();
/*     */       
/*  65 */       if (commandabstract_commandnumber4.c()) {
/*  66 */         enumset.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*     */       } else {
/*  68 */         f1 = MathHelper.g(f1);
/*     */       } 
/*     */       
/*  71 */       entity.stopRiding();
/*  72 */       ((EntityPlayer)entity).playerConnection.a(commandabstract_commandnumber.a(), commandabstract_commandnumber1.a(), commandabstract_commandnumber2.a(), f, f1, enumset, PlayerTeleportEvent.TeleportCause.COMMAND);
/*  73 */       entity.setHeadRotation(f);
/*     */     } else {
/*  75 */       float f2 = (float)MathHelper.g(commandabstract_commandnumber3.a());
/*     */       
/*  77 */       float f = (float)MathHelper.g(commandabstract_commandnumber4.a());
/*  78 */       f = MathHelper.a(f, -90.0F, 90.0F);
/*  79 */       entity.setPositionRotation(commandabstract_commandnumber.a(), commandabstract_commandnumber1.a(), commandabstract_commandnumber2.a(), f2, f);
/*  80 */       entity.setHeadRotation(f2);
/*     */     } 
/*     */     
/*  83 */     if (!(entity instanceof EntityLiving) || !((EntityLiving)entity).cP()) {
/*  84 */       entity.motY = 0.0D;
/*  85 */       entity.onGround = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/*  91 */     return (astring.length == 1) ? a(astring, minecraftserver.getPlayers()) : ((astring.length > 1 && astring.length <= 4) ? a(astring, 1, blockposition) : Collections.<String>emptyList());
/*     */   }
/*     */   
/*     */   public boolean isListStart(String[] astring, int i) {
/*  95 */     return (i == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/* 101 */     return a(o);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */