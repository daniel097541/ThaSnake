package Client;



import javax.swing.table.DefaultTableModel;
import java.util.Observer;

public class ClientObservable implements Observer{


    ClientObservable() {
    }


    public void paint( int x, int y, String color){
        if(Client.getView() != null)
            Client.getView().paintPoint(x, y, color);
    }


    private void addRow(Object[] row){
        ((DefaultTableModel)Client.getTable().getjTable1().getModel()).addRow(row);
    }

    private void modifyRow(Integer i, Integer points){
        Client.getTable().getjTable1().getModel().setValueAt(points,i,1);
    }

    public void modifyTable(Integer id,Integer points){
        Object row[] = {id,points};
        boolean found = false;
        for(int i = 0; i < Client.getTable().getjTable1().getRowCount(); i++){
            Integer valor = (Integer) Client.getTable().getjTable1().getValueAt(i,0);
            if(valor.equals(id)){
                modifyRow(i,points);
                found = true;
                break;
            }
        }
        if(!found){
            addRow(row);
        }
    }


    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
