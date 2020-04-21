/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ChatClickable
/*    */ {
/*    */   private final EnumClickAction a;
/*    */   private final String b;
/*    */   
/*    */   public ChatClickable(EnumClickAction paramEnumClickAction, String paramString) {
/* 12 */     this.a = paramEnumClickAction;
/* 13 */     this.b = paramString;
/*    */   }
/*    */   
/*    */   public EnumClickAction a() {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   public String b() {
/* 21 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 26 */     if (this == paramObject) {
/* 27 */       return true;
/*    */     }
/* 29 */     if (paramObject == null || getClass() != paramObject.getClass()) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     ChatClickable chatClickable = (ChatClickable)paramObject;
/*    */     
/* 35 */     if (this.a != chatClickable.a) {
/* 36 */       return false;
/*    */     }
/* 38 */     if ((this.b != null) ? !this.b.equals(chatClickable.b) : (chatClickable.b != null)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return "ClickEvent{action=" + this.a + ", value='" + this.b + '\'' + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 55 */     int i = this.a.hashCode();
/* 56 */     i = 31 * i + ((this.b != null) ? this.b.hashCode() : 0);
/* 57 */     return i;
/*    */   }
/*    */   
/*    */   public enum EnumClickAction {
/* 61 */     OPEN_URL("open_url", true),
/* 62 */     OPEN_FILE("open_file", false),
/* 63 */     RUN_COMMAND("run_command", true),
/* 64 */     SUGGEST_COMMAND("suggest_command", true),
/* 65 */     CHANGE_PAGE("change_page", true);
/*    */     
/* 67 */     private static final Map<String, EnumClickAction> f = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     private final boolean g;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     private final String h;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     static {
/* 85 */       for (EnumClickAction enumClickAction : values()) {
/* 86 */         f.put(enumClickAction.b(), enumClickAction);
/*    */       }
/*    */     }
/*    */     
/*    */     public static EnumClickAction a(String param1String) {
/* 91 */       return f.get(param1String);
/*    */     }
/*    */     
/*    */     EnumClickAction(String param1String1, boolean param1Boolean) {
/*    */       this.h = param1String1;
/*    */       this.g = param1Boolean;
/*    */     }
/*    */     
/*    */     public boolean a() {
/*    */       return this.g;
/*    */     }
/*    */     
/*    */     public String b() {
/*    */       return this.h;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatClickable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */