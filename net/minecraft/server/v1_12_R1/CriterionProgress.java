/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class CriterionProgress
/*    */ {
/* 14 */   private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*    */   
/*    */   private final AdvancementProgress b;
/*    */   private Date c;
/*    */   
/*    */   public CriterionProgress(AdvancementProgress paramAdvancementProgress) {
/* 20 */     this.b = paramAdvancementProgress;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 24 */     return (this.c != null);
/*    */   }
/*    */   
/*    */   public void b() {
/* 28 */     this.c = new Date();
/*    */   }
/*    */   
/*    */   public void c() {
/* 32 */     this.c = null;
/*    */   }
/*    */   
/*    */   public Date getDate() {
/* 36 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "CriterionProgress{obtained=" + ((this.c == null) ? "false" : (String)this.c) + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) {
/* 47 */     paramPacketDataSerializer.writeBoolean((this.c != null));
/* 48 */     if (this.c != null) {
/* 49 */       paramPacketDataSerializer.a(this.c);
/*    */     }
/*    */   }
/*    */   
/*    */   public JsonElement e() {
/* 54 */     if (this.c != null) {
/* 55 */       return (JsonElement)new JsonPrimitive(a.format(this.c));
/*    */     }
/* 57 */     return (JsonElement)JsonNull.INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public static CriterionProgress a(PacketDataSerializer paramPacketDataSerializer, AdvancementProgress paramAdvancementProgress) {
/* 62 */     CriterionProgress criterionProgress = new CriterionProgress(paramAdvancementProgress);
/* 63 */     if (paramPacketDataSerializer.readBoolean()) {
/* 64 */       criterionProgress.c = paramPacketDataSerializer.m();
/*    */     }
/* 66 */     return criterionProgress;
/*    */   }
/*    */   
/*    */   public static CriterionProgress a(AdvancementProgress paramAdvancementProgress, String paramString) {
/* 70 */     CriterionProgress criterionProgress = new CriterionProgress(paramAdvancementProgress);
/*    */     try {
/* 72 */       criterionProgress.c = a.parse(paramString);
/* 73 */     } catch (ParseException parseException) {
/* 74 */       throw new JsonSyntaxException("Invalid datetime: " + paramString, parseException);
/*    */     } 
/* 76 */     return criterionProgress;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */