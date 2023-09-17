package com.example.servingwebcontent;
import java.io.Serializable;
import java.util.*;

public class PurchasedProducts implements Serializable{

    HashMap<String,ArrayList<String[]>> cart=new HashMap<String,ArrayList<String[]>>();

    public HashMap getCart() {
      return cart;
    }
  
    public void addtoCart(String email,String name,String description,String amount) {
      String[] temp=new String[3];
      temp[0]=name;
      temp[1]=description;
      temp[2]=amount;
      if(cart.containsKey(email))cart.get(email).add(temp);
      else{
        ArrayList arr=new ArrayList();
        arr.add(temp);
        cart.put(email,arr);
      }
    }

    public void removeFromCart(String email,String id) {
      for(int i=0;i<cart.get(email).size();i++)
      {
        if(cart.get(email).get(i)[0].equals(id))
        {
          cart.get(email).remove(i);
          break;
        }
      }
    }
  }