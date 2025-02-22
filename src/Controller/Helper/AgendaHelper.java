package Controller.Helper;

import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import View.Agenda;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class AgendaHelper implements IHelper{
    private final Agenda view;

    public AgendaHelper(Agenda view) {
        this.view = view;
    }
    
    public void preencherTabela(ArrayList<Agendamento> agendamentos){
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableAgendamentos().getModel();
        tableModel.setNumRows(0);
        
        for(Agendamento agendamento : agendamentos){
            tableModel.addRow(new Object[]{
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getServico().getDescrição(),
                agendamento.getValor(),
                agendamento.getDataFormatada(),
                agendamento.getHoraFormatada(),
                agendamento.getObservacao()
            });
    }
}
    public void preencherClientes(ArrayList<Cliente> clientes) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getjComboboxCliente().getModel();
        for (Cliente cliente : clientes) {
            comboBoxModel.addElement(cliente);
        }
    }

    public void preencherServicos(ArrayList<Servico> servicos) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getjComboboxServico().getModel();
        for (Servico servico : servicos) {
            comboBoxModel.addElement(servico);
        }
    }
    
    public Cliente obterCliente(){
        return (Cliente) view.getjComboboxCliente().getSelectedItem();
    }
    
    public Servico obterServico(){
        return (Servico) view.getjComboboxServico().getSelectedItem();
        
    }
    
    public void setarValor(float valor){
        view.getTextValor().setText(valor+"");
    }

    @Override
    public Agendamento obterModelo() {
        String idString = view.getTextId().getText();
        int id = Integer.parseInt(idString);
        Cliente cliente = obterCliente();
        Servico servico = obterServico();
        String valorString = view.getTextValor().getText();
        float valor = Float.parseFloat(valorString);
        String data = view.getTextFormatedData().getText();
        String hora = view.getTextFormatedHora().getText();
        String dataHora = data + " " + hora;
        String observacao = view.getTextObservacao().getText();
        Agendamento agendamento = new Agendamento(id, cliente, servico, valor, dataHora, observacao);
        return agendamento;
    }

    @Override
    public void limparTela() {
        view.getTextId().setText("0");
        view.getTextFormatedData().setText("");
        view.getTextFormatedHora().setText("");
        view.getTextObservacao().setText("");
    }
    
}
