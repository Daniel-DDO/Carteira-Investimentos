package br.com.investimentos.controladores;

import br.com.investimentos.usuarios.Conta;

import java.io.*;

public class ControladorArquivos {

    public static void escreverNoArquivo(Conta conta) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contas.dat"));
            oos.writeObject(conta);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lerDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Contas.dat"));
            Conta conta = (Conta) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
