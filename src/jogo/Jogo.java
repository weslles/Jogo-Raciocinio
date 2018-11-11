/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import interfaces.IJogo;
import interfaces.IRepositorio;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIOO
 */
public class Jogo extends javax.swing.JFrame implements IJogo{
    private  Date tem;
      public Jogo(Date t){
          this.tem = t;
      }
    /**
     * @return the tem
     */
    public Date getTem() {
        return tem;
    }

    /**
     * @param tem the tem to set
     */
    public void setTem(Date tem) {
        this.tem = tem;
    }

    @Override
    public int tempo() {
        Date tmp = new Date();
        long diferenca = tmp.getTime()- tem.getTime();
        int horas = (int) (diferenca/1000/60/60);
        int minutos = (int) (diferenca/1000/60)-(horas*60);
        return (minutos);
    }
    @Override
    public void desempenhoJogo() {
        int min = tempo();
        if(min < 15){
        JOptionPane.showMessageDialog(null, "Desempenho ótimo");
        }
        else
        {
            if(min > 15 && min < 25){
                JOptionPane.showMessageDialog(null, "Desempenho regular");
            }
            else
                JOptionPane.showMessageDialog(null, "Desempenho ruim");
        }
    }       
}
