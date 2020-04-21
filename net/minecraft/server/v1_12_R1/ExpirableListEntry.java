/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class ExpirableListEntry<T>
/*    */   extends JsonListEntry<T> {
/* 10 */   public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*    */   protected final Date b;
/*    */   protected final String c;
/*    */   protected final Date d;
/*    */   protected final String e;
/*    */   
/*    */   public ExpirableListEntry(T t0, Date date, String s, Date date1, String s1) {
/* 17 */     super(t0);
/* 18 */     this.b = (date == null) ? new Date() : date;
/* 19 */     this.c = (s == null) ? "(Unknown)" : s;
/* 20 */     this.d = date1;
/* 21 */     this.e = (s1 == null) ? "Banned by an operator." : s1;
/*    */   }
/*    */   
/*    */   protected ExpirableListEntry(T t0, JsonObject jsonobject) {
/* 25 */     super(checkExpiry(t0, jsonobject), jsonobject);
/*    */     
/*    */     Date date, date1;
/*    */     
/*    */     try {
/* 30 */       date = jsonobject.has("created") ? a.parse(jsonobject.get("created").getAsString()) : new Date();
/* 31 */     } catch (ParseException parseException) {
/* 32 */       date = new Date();
/*    */     } 
/*    */     
/* 35 */     this.b = date;
/* 36 */     this.c = jsonobject.has("source") ? jsonobject.get("source").getAsString() : "(Unknown)";
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 41 */       date1 = jsonobject.has("expires") ? a.parse(jsonobject.get("expires").getAsString()) : null;
/* 42 */     } catch (ParseException parseException) {
/* 43 */       date1 = null;
/*    */     } 
/*    */     
/* 46 */     this.d = date1;
/* 47 */     this.e = jsonobject.has("reason") ? jsonobject.get("reason").getAsString() : "Banned by an operator.";
/*    */   }
/*    */   
/*    */   public Date getExpires() {
/* 51 */     return this.d;
/*    */   }
/*    */   
/*    */   public String getReason() {
/* 55 */     return this.e;
/*    */   }
/*    */   
/*    */   boolean hasExpired() {
/* 59 */     return (this.d == null) ? false : this.d.before(new Date());
/*    */   }
/*    */   
/*    */   protected void a(JsonObject jsonobject) {
/* 63 */     jsonobject.addProperty("created", a.format(this.b));
/* 64 */     jsonobject.addProperty("source", this.c);
/* 65 */     jsonobject.addProperty("expires", (this.d == null) ? "forever" : a.format(this.d));
/* 66 */     jsonobject.addProperty("reason", this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSource() {
/* 71 */     return this.c;
/*    */   }
/*    */   
/*    */   public Date getCreated() {
/* 75 */     return this.b;
/*    */   }
/*    */   
/*    */   private static <T> T checkExpiry(T object, JsonObject jsonobject) {
/* 79 */     Date expires = null;
/*    */     
/*    */     try {
/* 82 */       expires = jsonobject.has("expires") ? a.parse(jsonobject.get("expires").getAsString()) : null;
/* 83 */     } catch (ParseException parseException) {}
/*    */ 
/*    */ 
/*    */     
/* 87 */     if (expires == null || expires.after(new Date())) {
/* 88 */       return object;
/*    */     }
/* 90 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ExpirableListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */