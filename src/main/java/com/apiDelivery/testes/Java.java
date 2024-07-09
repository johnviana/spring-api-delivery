package com.apiDelivery.testes;

import java.util.Scanner;

public class Java {

    public static void main(String[] args){

        Scanner entreda = new Scanner(System.in);

        System.out.print("Peso ");
        int peso = entreda.nextInt();

        System.out.print("Altura ");
        double altura = entreda.nextDouble();

        double imc = peso / (altura * altura);

    }
}
