package Ecommerce.model;

import java.util.Random;

public abstract class product {

   private  String name;
   private  String code;
   private int quantidade;
   private  double price;
   private static final String ALPHABETICAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private static final Random RANDOM = new Random();

   
   // standard builder
   public product(){
      this.name="Product";
      this.code="code123";
      this.quantidade=1;
      this.price=0.0;
   }
   // parameterized builder
   public product(String name, double price, int qtd){
      this.name=name;
      this.price=price;
      this.quantidade=qtd;
      this.code = generateCode();

   }

   private static String generateCode() {
        String parteFixa = "2025";

        char letra1 = ALPHABETICAL_CHARACTERS.charAt(RANDOM.nextInt(ALPHABETICAL_CHARACTERS.length()));

        int numero1 = RANDOM.nextInt(100);

        char letra2 = ALPHABETICAL_CHARACTERS.charAt(RANDOM.nextInt(ALPHABETICAL_CHARACTERS.length()));

        int numero2 = RANDOM.nextInt(10);
        
        String numero1Formatado = String.format("%02d", numero1);
        
        return parteFixa + letra1 + numero1Formatado + letra2 + numero2;
    }


   void SetName(String name){
      this.name = name;
   }
   void SetCode(String code){
      this.code=code;
   }
   
   public double getPrice(){
      return this.price;
   } 
   public String getName(){
      return this.name;
   }
   public int getqtd(){
      return this.quantidade;
   }
   public String getCode(){
   return this.code;
   }
   public void setAmount(int amount) {
      this.quantidade = amount;
    }
   public abstract double freight();
}