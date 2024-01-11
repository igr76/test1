package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;


public class Demo4Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo4Application.class, args);
        int[] array=generateArray(10,50);
        equals(array);
        int[] array1=generateArray(100,100);
        equals(array1);
        int[] array2=generateArray(300,500);
        equals(array2);
        int[] array3=generateArray(300,1000);
        equals(array3);
        int[] array4=generateArrayDuplicates(100,900);
        equals(array4);
    }
    public static String serializeArray(int[] arr) {
        if (arr.length>1000){return "массив должен быть до 1000 чисел";}
        byte a=0;byte b=0;
        String plus=null;String duplicates=null;
        Arrays.sort(arr);
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<arr.length;i++) {
            if ( arr[i]<0 || arr[i]>300){return  "Числа должны быть от 0 до 300";}
            if (a == 1||(i<arr.length-1 && arr[i]==arr[i+1]-1 )) {
                if (a == 1){
                    if (i<arr.length-1 && arr[i]==arr[i+1]-1 ){ plus=plus.substring(0,2)+arr[i+1];a=1;} else {builder.append(plus+"|");a=-1;plus=null;}
                }else {if (i<arr.length-1 && arr[i]==arr[i+1]-1 ){ plus=arr[i]+"-"+arr[i+1];a=1;}}
            }
            if (b > 0||(i<arr.length-2 && arr[i]==arr[i+1] && arr[i+1]==arr[i+2])) {
                if (b>0){
                    if (i<arr.length-2 && arr[i]==arr[i+1] && arr[i+1]==arr[i+2]){duplicates=duplicates.substring(0,2)+b;b++;} else {builder.append(duplicates+"|");b=-1;duplicates=null;}
                }else {duplicates=arr[i]+"*"+3;b=3;}
            }
            if (a == 0 && b == 0) {
                if (a == -1 || b == -1) {
                    a=0;b=0;
                }else builder.append(arr[i]+"|");
            }
        }
        return String.valueOf(builder);
    }
    public static void equals(int[] arr) {
        String string1=Arrays.toString(arr);
        String stringResult=serializeArray(arr);
        int comparisonString=stringResult.length()*100/string1.length();
        System.out.println("cтрока до сериализации длина:"+string1.length());
        System.out.println("cтрока после сериализации длина:"+stringResult.length());
        System.out.println("процент сжатия :"+comparisonString+" %");
    }
    
    public static int[] generateArray(int volume,int size) {
        int[] arr=new  int[size];
        for (int i = 0; i < size; i++) {
            arr[i]= (int) (Math.random()*volume);}return arr;
    }
    public static int[] generateArrayDuplicates(int volume,int size) {
        int[] arr=new  int[size];
        for (int i = 0; i < size-2; i++) {
            arr[i]= (int) (Math.random()*volume);
            arr[i+1]=arr[i];i++;
            arr[i+1]=arr[i];i++;}return arr;
    }

}
