package com.fernandezgarcete.appdeuda;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import dbConnection.CuentaDAO;
import dbConnection.EmpleadoDAO;
import entity.Cuenta;
import entity.Empleado;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import util.EmpleadoFilter;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    private Table empleadoList = new Table();
    private TextField buscarField = new TextField();
    private Button agregarEmpleadoButton = new Button();
    private Button removerEmpleadoButton = new Button();
    private Button modificarEmpleadoButton = new Button();
    private FormLayout editorLayout = new FormLayout();
    private FieldGroup editorFields = new FieldGroup();
    
    
    private static final String[] nombreAtrib = new String[] {
                    "Nombre", "Deuda", "A Quien", "Fecha", "Estado"};

IndexedContainer empleadoContainer = crearDataSource();

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.fernandezgarcete.appdeuda.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        initLayout();
        initEmpleadoList();
        initEditor();
        initBuscador();
        initAgregarBorrarButtons();
    }
    
    private void initLayout() {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        setContent(splitPanel);
        
        VerticalLayout leftLayout = new VerticalLayout();
        splitPanel.addComponent(leftLayout);
        splitPanel.addComponent(editorLayout);
        leftLayout.addComponent(empleadoList);
        
        HorizontalLayout bottomLeftLayout = new HorizontalLayout();
        leftLayout.addComponent(bottomLeftLayout);
        bottomLeftLayout.addComponent(buscarField);
        bottomLeftLayout.addComponent(agregarEmpleadoButton);
        
        leftLayout.setSizeFull();
        
        leftLayout.setExpandRatio(empleadoList, 1);
        empleadoList.setSizeFull();
        
        bottomLeftLayout.setWidth("100%");
        buscarField.setWidth("100%");
        bottomLeftLayout.setExpandRatio(buscarField, 1);
        
        editorLayout.setMargin(true);
        editorLayout.setVisible(false);
    }
    
    private void initEmpleadoList() {
                
        empleadoList.setContainerDataSource(empleadoContainer);
        empleadoList.setVisibleColumns(new String[]{"Nombre", "Deuda", "A Quien", "Fecha", "Estado"});
        empleadoList.setSelectable(true);
        empleadoList.setImmediate(true);
        
        empleadoList.addValueChangeListener(new Property.ValueChangeListener(){
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object empleadoId = empleadoList.getValue();
                
                if (empleadoId != null) {
                    editorFields.setItemDataSource(empleadoList.getItem(empleadoId));
                }
                editorLayout.setVisible(empleadoId != null);
            }
        });
    }
    
    private void initEditor(){
        for (String nomAtr : nombreAtrib) {
            TextField campo = new TextField(nomAtr);
            editorLayout.addComponent(campo);
            campo.setWidth("100%");
            
            editorFields.bind(campo, nomAtr);
        }
        editorLayout.addComponent(removerEmpleadoButton);
        editorLayout.addComponent(modificarEmpleadoButton);
        
        editorFields.setBuffered(false);
    }
    
    private void initBuscador() {
        buscarField.setInputPrompt("Buscar nombres");
        
        buscarField.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.LAZY);
        
        buscarField.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(final FieldEvents.TextChangeEvent event) {
                empleadoContainer.removeAllContainerFilters();
                empleadoContainer.addContainerFilter(new EmpleadoFilter(event.getText()));
            }
        });
    }
    
    private void initAgregarBorrarButtons() {
        agregarEmpleadoButton.addClickListener(new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent event) {
                empleadoContainer.removeAllContainerFilters();
                
                Object empleadoId = empleadoContainer.addItemAt(0);
                
                empleadoList.getContainerProperty(empleadoId, "nombre").setValue("Nuevo");
                
                empleadoList.select(empleadoId);
            }
        });
        
        removerEmpleadoButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Object empleadoId = empleadoList.getValue();
                
                empleadoList.removeItem(empleadoId);
            }
        });
    }
    
    private static IndexedContainer crearDataSource(){
        IndexedContainer ic = new IndexedContainer();
        
        for (String atributo : nombreAtrib) {
            ic.addContainerProperty(atributo, String.class, "");
        }
        
        List<Empleado> e = EmpleadoDAO.listarEmpleados();
        List<Cuenta> c = CuentaDAO.listarCuentas();
        
        for (int i = 0; i < c.size(); i++) {
            Object id = ic.addItem();
           
            for (int j = 0; j < e.size(); j++) {
                if (e.get(j).getId() == c.get(i).getEmpleado()) {
                    ic.getContainerProperty(id, "Nombre").setValue(e.get(j).getNombre());
                }
                if (e.get(j).getId() == c.get(i).getAquien()) {
                    ic.getContainerProperty(id, "A Quien").setValue(e.get(j).getNombre());
                }
            }
            ic.getContainerProperty(id, "Deuda").setValue(c.get(i).getMontoDeuda());
            ic.getContainerProperty(id, "Fecha").setValue(c.get(i).getFecha());
            ic.getContainerProperty(id, "Estado").setValue(c.get(i).getEstado());
        }
        return ic;
    }

}
