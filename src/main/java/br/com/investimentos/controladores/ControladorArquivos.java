package br.com.investimentos.controladores;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.ContaUsuario;

import java.io.*;

public class ControladorArquivos {

    private static int tamanho = 100;

    public static void atualizarArquivo(Object[] objects, final String ARQUIVO) {
        Object[] objectsAtualizados = new Object[objects.length];
        int posicaoLivre = 0;

        for (Object object : objects) {
            if (object != null) {
                objectsAtualizados[posicaoLivre] =  object;
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(objectsAtualizados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getTamanho() {
        return tamanho;
    }

    public static void setTamanho(int tamanho) {
        ControladorArquivos.tamanho = tamanho;
    }
}
