package Ecommerce;

public class Customer {

   private String name;
   private long CPF;


   //construtor padrao
   public Customer(){
      this.name = "user";
      this.CPF = 12345678910L;
   }
   //construtor parametrizado
   public Customer(String name, long CPF){
      this.name = name;
      this.CPF = CPF;
   }
   public String getName(){
      return this.name;
   }
   public long getCPF(){
      return this.CPF;
   }
   void setName(String name){
      this.name = name;
   }
   // void setCPF(long CPF){
   //    this.CPF = CPF;
   // }
   
}