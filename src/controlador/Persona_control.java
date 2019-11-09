package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.Persona_model;
import vista.Persona_view;

public class Persona_control implements ActionListener {

    Persona_model mdl = new Persona_model();
    Persona p = new Persona();
    Persona_view vista = new Persona_view();
    DefaultTableModel modelo = new DefaultTableModel();

    public Persona_control(Persona_view v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGrabar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        listar(vista.tbpersona);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tbpersona);
            nuevo();
        }
        if (e.getSource() == vista.btnGrabar) {
            agregar();
            limpiarTabla();
            listar(vista.tbpersona);
            nuevo();
        }
        if (e.getSource() == vista.btnEditar) {
            Editar();
        }
        if (e.getSource() == vista.btnActualizar) {
            Actualizar();
            limpiarTabla();
            listar(vista.tbpersona);
            nuevo();
        }
        if (e.getSource() == vista.btnEliminar) {
            eleminar();
            limpiarTabla();
            listar(vista.tbpersona);
        }

    }

    void Editar() {
        int fila = vista.tbpersona.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar una fila");
        } else {
            int id = Integer.parseInt((String) vista.tbpersona.getValueAt(fila, 0).toString());
            String nom = (String) vista.tbpersona.getValueAt(fila, 1);
            String correo = (String) vista.tbpersona.getValueAt(fila, 2);
            String telf = (String) vista.tbpersona.getValueAt(fila, 3);
            vista.txtid.setText("" + id);
            vista.txtnombres.setText(nom);
            vista.txtcorreo.setText(correo);
            vista.txttelefono.setText(telf);
        }
    }

    void nuevo() {
        vista.txtid.setText("");
        vista.txtnombres.setText("");
        vista.txttelefono.setText("");
        vista.txtcorreo.setText("");
        vista.txtnombres.requestFocus();
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tbpersona.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void eleminar() {
        int fila = vista.tbpersona.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una Persona");
        } else {
            int id = Integer.parseInt((String) vista.tbpersona.getValueAt(fila, 0).toString());
            mdl.eleminar(id);
            //System.out.println("El Reusltaod es" + id);
            JOptionPane.showMessageDialog(vista, "Persona Desactivado...!!! con Id: " + id);
        }
    }

    public void Actualizar() {
        int id = Integer.parseInt(vista.txtid.getText());
        String Nombre = vista.txtnombres.getText();
        String Correo = vista.txtcorreo.getText();
        String telf = vista.txttelefono.getText();
        p.setId(id);
        p.setNom(Nombre);
        p.setCorreo(Correo);
        p.setTelf(telf);
        int r = mdl.actualizar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Actualizado Correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }

    }

    public void agregar() {
        String Nombre = vista.txtnombres.getText();
        String Correo = vista.txtcorreo.getText();
        String telf = vista.txttelefono.getText();
        p.setNom(Nombre);
        p.setCorreo(Correo);
        p.setTelf(telf);
        int r = mdl.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Agregado con Exito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        vista.tbpersona.setModel(modelo);
        List<Persona> lista = mdl.listar();
        Object[] object = new Object[4]; //se pone cantidad de colunas de qe tiene
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNom();
            object[2] = lista.get(i).getCorreo();
            object[3] = lista.get(i).getTelf();
            modelo.addRow(object);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbpersona.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
}
