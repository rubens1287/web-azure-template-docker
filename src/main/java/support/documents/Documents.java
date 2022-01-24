package support.documents;

import lombok.extern.log4j.Log4j2;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;

/**
 * @author Rubens Lobo
 * @since 17/07/2018
 */
@Log4j2
public class Documents {

    private ArrayList<Integer> listaAleatoria = new ArrayList<Integer>();
    private ArrayList<Integer> listaNumMultiplicados = null;


    private int geraNumAleatorio(){
        int numero = (int) (Math.random() * 10);
        return numero;
    }

    private ArrayList<Integer> geraPrimeiroDigito(){
        listaNumMultiplicados = new ArrayList<Integer>();
        int primeiroDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 10;

        for(int item : listaAleatoria){
            listaNumMultiplicados.add(item * peso);

            peso--;
        }

        for(int item : listaNumMultiplicados){
            totalSomatoria += item;
        }

        restoDivisao = (totalSomatoria % 11);

        if(restoDivisao < 2){
            primeiroDigito = 0;
        } else{
            primeiroDigito = 11 - restoDivisao;
        }

        listaAleatoria.add(primeiroDigito);

        return listaAleatoria;
    }

    private ArrayList<Integer> geraCPFParcial(){
        for(int i = 0; i < 9; i++){
            listaAleatoria.add(geraNumAleatorio());
        }

        return listaAleatoria;
    }

    private ArrayList<Integer> geraSegundoDigito(){
        listaNumMultiplicados = new ArrayList<Integer>();
        int segundoDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 11;

        for(int item : listaAleatoria){
            listaNumMultiplicados.add(item * peso);

            peso--;
        }

        for(int item : listaNumMultiplicados){
            totalSomatoria += item;
        }

        restoDivisao = (totalSomatoria % 11);

        if(restoDivisao < 2){
            segundoDigito = 0;
        } else{
            segundoDigito = 11 - restoDivisao;
        }

        listaAleatoria.add(segundoDigito);

        return listaAleatoria;
    }

    /**
     * Get Random CPF documents
     *
     * @param mask - pass true if you want to get cpf with mask
     * @return attachment valid CPF document
     */
    public String getCpf(boolean mask) {
        geraCPFParcial();
        geraPrimeiroDigito();
        geraSegundoDigito();

        String cpf = "";
        String texto = "";


        for(int item : listaAleatoria){
            texto += item;
        }
        if(mask){
            try{
                MaskFormatter mf = new MaskFormatter("###.###.###-##");
                mf.setValueContainsLiteralCharacters(false);
                cpf = mf.valueToString(texto);
            }catch(Exception ex){
                log.error("Erro ao tentar gerar mascara para o cpf " + ex);
            }
        }else {
            cpf = texto;
        }
        return cpf;
    }

    /**
     * Remove special character of the documents
     *
     * @param document - pass cpf or cnpj document to remove especial characters
     * @return String containing only numbers
     */
    private static String removeSpecialCharacter(String document) {
        if (document.contains(".")) {
            document = document.replace(".", "");
        }
        if (document.contains("-")) {
            document = document.replace("-", "");
        }
        if (document.contains("/")) {
            document = document.replace("/", "");
        }
        return document;
    }
}
