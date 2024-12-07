package br.com.investimentos.controladores;

import br.com.investimentos.usuarios.Conta;

import java.io.*;

public class ControladorArquivos {

    private static final String CONTAS_ARQUIVO = "Contas.dat";
    private static int tamanho = 100;

    public static void escreverNoArquivo(Conta conta) {
        Conta[] contas = lerDoArquivo();

        if (contas == null) {
            contas = new Conta[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < contas.length; i++) {
            if (contas[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            contas[posicaoLivre] = conta;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTAS_ARQUIVO))) {
                oos.writeObject(contas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array de contas está cheio.");
        }

    }

    public static Conta[] lerDoArquivo() {
        File arquivo = new File(CONTAS_ARQUIVO);
        if (!arquivo.exists()) {
            return new Conta[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Conta[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Conta[tamanho];
        }
    }

    public static Conta buscarConta(String entrada) {
        Conta[] contas = lerDoArquivo();
        if (contas == null) {
            return null;
        }

        for (Conta conta : contas) {
            if (conta != null && (conta.getEmail().equals(entrada) || conta.getNomeUsuario().equals(entrada))) {
                return conta;
            }
        }
        return null;
    }


    public static int getTamanho() {
        return tamanho;
    }

    public static void setTamanho(int tamanho) {
        ControladorArquivos.tamanho = tamanho;
    }
}
