/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import interfaces.IRepositorio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIOO
 */
public class RepositorioTXT implements IRepositorio{
    private String[] listas;
    private String diretorio;
    char spr = File.separatorChar;//Recupera o tipo de separador de diretórios e arqivos do sistema corrente
    final Charset utf8 = StandardCharsets.UTF_8;//Recupera uma constante do tipo de codificação UTF_8
    public RepositorioTXT(String[] lista, String dir){
        this.listas = lista;
        this.diretorio = dir;
    }
    /**
     * @return the listas
     */
    public String[] getListas() {
        return listas;
    }

    /**
     * @param listas the listas to set
     */
    public void setListas(String[] listas) {
        this.listas = listas;
    }
    
    /**
     * @return the diretorio
     */
    public String getDiretorio() {
        return diretorio;
    }

    /**
     * @param diretorio the diretorio to set
     */
    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }
    
    @Override
    public void armazenar() {
        String arq = JOptionPane.showInputDialog("Digite seu nome:");//String que obtém o nome para um arquivo
        
        if(arq != null && !(arq.equals(""))){//Esse bloco não executa caso o usuário não entre com um nome para o arquivo
            int i = 0;
            String x = null;
            Iterable c = FileSystems.getDefault().getRootDirectories();//Obtém uma lista dos diretórios raiz do sistema
            for (Object c1 : c) {
                if(i == 0){
                    x = c1.toString();
                }//Seleciona o diretório raiz principal
                 i++;
            }
            Path path = Paths.get(x+getDiretorio()+""+spr+""+arq+".txt");//Cria um caminho para salvar os arquivos
            try {
                Files.createDirectories(path.getParent());//Cria um diretório no caminho criado
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar diretorio.");
            }//Mensagem Caixa de diálogo caso erro ao criar diretório
            
            /*Tenta criar um BufferedWriter para escrita de arquivos*/
        try(BufferedWriter writer = Files.newBufferedWriter(path, utf8))
            {            
                for (String list : getListas())//Peercorre cada elemento dentro do Array
                {
                    writer.write(list);//Escreve em arquivo o elemento corrente
                    writer.newLine();//Passa para uma nova linha
                }
            }
        catch(IOException e)
               {
                    JOptionPane.showMessageDialog(null, "Erro! Disco insuficiente.");
               }//Mensagem caixa de diálogo caso erro ao criar diretorio ou arquivo
        }//Fim do bloco if
        else{
                JOptionPane.showMessageDialog(null, "Para salvar digite um nome.");
            }//Mensagem caixa de diálogo caso usuário não digite um nome para o arquivo
    }

    @Override
    public String[] recuperaArquivo() {
        String arq = JOptionPane.showInputDialog("Digite seu nome:");//Obtém um nome para recuperar arquivo
        if(arq != null && !(arq.equals(""))){//Esse bloco não executa caso o usuário não entre com um nome para o arquivo
            int i = 0;
            String x = null;
            Iterable c = FileSystems.getDefault().getRootDirectories();//Obtém uma lista dos diretórios raiz do sistema
            for (Object c1 : c) {
                if(i == 0){
                    x = c1.toString();
                }//Seleciona o diretório raiz principal
                 i++;
            }
            Path path = Paths.get(x+spr+getDiretorio()+spr+arq+".txt");
            
            /*Tenta criar um BufferedReader para ler arquivos*/
        try(BufferedReader reader = Files.newBufferedReader(path, utf8)){
            int cont = 0;
            String line;
            while((line = reader.readLine())!= null){
                    getListas()[cont] = line;//Carrega uma linha do arquivo em cada posição do Array
                
                cont++;
            }
            }
        catch(IOException e2){
                JOptionPane.showMessageDialog(null, "Arquivo inexistente!");
                return getListas();//retorna o um Array com os valores do Jogo atual
            }//Mensagem caixa de diálogo caso arquivo não exista
                
        return getListas();//Retorna um Array com os valores do arquivo lido
    }//Fim do primeiro bloco if
        else{
             JOptionPane.showMessageDialog(null, "Campo do nome não preenchido.");
             return getListas();//retorna o um Array com os valores do Jogo atual
        }
    }

    

    
    
}
